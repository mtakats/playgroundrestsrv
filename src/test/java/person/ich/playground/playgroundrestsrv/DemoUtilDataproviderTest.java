package person.ich.playground.playgroundrestsrv;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import static junit.framework.TestCase.assertTrue;

@RunWith(DataProviderRunner.class)
@SpringBootTest
@ContextConfiguration
public class DemoUtilDataproviderTest {

    private static final TestContextManager testContextManager = new TestContextManager(DemoUtilParameterizedTest.class);
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoUtil demoUtil;

    @DataProvider
    public static Object[][] dataProvider() {
        return new Object[][]{
                {-10, 0},
                {0, 10},
                {-10, 10}
        };
    }

    @Before
    public void injectDependencies() throws Exception {
        testContextManager.prepareTestInstance(this);
    }

    @Test
    @UseDataProvider("dataProvider")
    public void getRandomIntegerTest(int min, int max) {
        int random = demoUtil.getRandomInteger(min, max);
        log.info("++++++++++ getRandomIntegerTest: random = " + random);
        assertTrue((random >= min) && random <= max);
    }

}
