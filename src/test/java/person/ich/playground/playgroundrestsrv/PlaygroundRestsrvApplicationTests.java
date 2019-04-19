package person.ich.playground.playgroundrestsrv;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaygroundRestsrvApplicationTests {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Ignore
    @Test
    public void toBeIgnored() {
        // The world is bad. Everybody ignores me!
    }

    @Test
    public void contextLoads() {
        log.info("++++++++++ Demo unit test");
        assertTrue(true);
    }

}
