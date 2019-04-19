import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static junit.framework.TestCase.assertTrue;

public class PlaygroundRestsrvApplicationIT {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Ignore
    @Test
    public void toBeIgnoredIT() {
        // The world is bad. Everybody ignores me!
    }

    @Test
    public void demoTestIT() {
        log.info("++++++++++ Demo integration test");
        assertTrue(true);
    }
}
