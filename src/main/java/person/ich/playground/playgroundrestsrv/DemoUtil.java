package person.ich.playground.playgroundrestsrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Component
public class DemoUtil {

    private final Logger log = LoggerFactory.getLogger(getClass());

    int getRandomInteger(int min, int max) {
        int randomInt;
        Random random = new Random();
        randomInt = random.nextInt(max - min) + min;
        return randomInt;
    }

    @Cacheable( "md5" )
    public byte[] md5( String text ) {
        log.info("hash: " + text);
        try {
            MessageDigest md = MessageDigest.getInstance( "MD5" );
            return md.digest( text.getBytes( StandardCharsets.UTF_8 ) );
        }
        catch ( NoSuchAlgorithmException e ) {
            return null;
        }
    }

    @Async
    public void waitSomeTime() throws InterruptedException {
        log.info("wait time: start");
        TimeUnit.SECONDS.sleep(1);
        log.info("wait time: end");
    }

    @Async
    public Future<String> waitForTheFuture() throws InterruptedException {
        log.info("waitForTheFuture: start");
        TimeUnit.SECONDS.sleep(2);
        log.info("waitForTheFuture: end");
        return new AsyncResult<>("The future is now!");
    }

    private final String[] urlString = {"123.abc", "httpx://456789", "http://www.google.de"};
    private int i = 0;

    @Retryable
    String loadContentFromUrl() throws IOException {
        URL url = new URL(urlString[i++]);
        StreamUtils.copyToString((url.openStream()), StandardCharsets.UTF_8);
        return url.toString();
    }
}
