package person.ich.playground.playgroundrestsrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Random;
import java.util.concurrent.Future;

@EnableCaching
@EnableAsync
@EnableRetry
@SpringBootApplication
public class PlaygroundRestsrvApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    //@Autowired // field injection
    private DemoUtil demoUtil;
    private ApplicationContext ctx;
    private Environment env;
    private String injectEnvValue;


    //@Autowired // constructor injection, not needed, done by SPRING automatically
    public PlaygroundRestsrvApplication(
            Environment env,
            ApplicationContext ctx,
            DemoUtil demoUtil,
            @Value("prop2") String injectEnvValue) {
        this.env = env;
        this.ctx = ctx;
        this.demoUtil = demoUtil;
        this.injectEnvValue = injectEnvValue;
    }

    // @Autowired // setter injection
    public void setDemoUtil(DemoUtil demoUtil) {
        this.demoUtil = demoUtil;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(PlaygroundRestsrvApplication.class, args);
        //Arrays.stream(ctx.getBeanDefinitionNames())
        //        .forEach(System.out::println);
    }

    // is executed in the main thread before the rest of main
    @Override
    public void run(String... args) throws Exception {
        log.info("----------------------------------------------------------------------");
        log.info("+++++++++++ " + "Hello from CommandLineRunner!");
        log.info("----------------------------------------------------------------------");
        log.error("++++++++++ test error logging");
        log.info("----------------------------------------------------------------------");
        // types of injections
        log.info("++++++++++ injected random integer: " + demoUtil.getRandomInteger(0, 100) );
        log.info("----------------------------------------------------------------------");
        String s = ctx.getBean(String.class);
        log.info("++++++++++ Singleton Bean values - first call: " + s);
        s = ctx.getBean(String.class);
        log.info("++++++++++ Singleton Bean values - second call: " + s);
        log.info("----------------------------------------------------------------------");
        log.info("++++++++++ get environment and environment injection ");
        log.info(env.getProperty("somePropertyValue"));
        log.info(this.injectEnvValue);
        log.info("----------------------------------------------------------------------");
        log.info("++++++++++ caching ");
        byte[] md5_1 = demoUtil.md5("blablabliblibloblo");
        log.info("++++++++++ caching - second call is missing here due to caching ");
        byte[] md5_2 = demoUtil.md5("blablabliblibloblo");
        log.info("++++++++++ caching - both objects are identical: " + (md5_1 == md5_2) ); // true
        log.info( demoUtil.getClass().toString() ); // extended class name to indicate cash usage
        log.info("----------------------------------------------------------------------");
        log.info("++++++++++ asnyc calls");
        log.info("Before 'waitSomeTime'");
        demoUtil.waitSomeTime();
        log.info("After 'waitSomeTime'");
        log.info("Before 'waitForTheFuture'");
        Future<String> future = demoUtil.waitForTheFuture();
        log.info("After 'waitForTheFuture'");
        log.info("Future result: " + future.get());
        log.info("----------------------------------------------------------------------");
        log.info("++++++++++ retry");
        String validUrl = demoUtil.loadContentFromUrl();
        log.info( "The first valid URL is the third one: " + validUrl);
        log.info("----------------------------------------------------------------------");
    }

    @Bean
    public String getRandomString() {
        Random random = new Random();
        int i = random.nextInt();
        return "Random" + i;
    }
}
