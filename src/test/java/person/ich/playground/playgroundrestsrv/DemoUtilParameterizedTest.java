package person.ich.playground.playgroundrestsrv;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
@SpringBootTest
@ContextConfiguration
public class DemoUtilParameterizedTest {

    private static final TestContextManager testContextManager = new TestContextManager(DemoUtilParameterizedTest.class);
    private final Logger log = LoggerFactory.getLogger(getClass());
    private int min;
    private int max;

    @Autowired
    private DemoUtil demoUtil;

    public DemoUtilParameterizedTest(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { -10, 0 },
                { 0, 10 },
                { -10, 10}
        });
    }

    @Before
    public void injectDependencies() throws Exception {
        testContextManager.prepareTestInstance(this);
    }

    @Test
    public void getRandomIntegerTest() {
        int random = demoUtil.getRandomInteger(min, max);
        log.info("++++++++++ getRandomIntegerTest: random = " + random);
        assertTrue((random >= min) && random <= max);
    }
}
