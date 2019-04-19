package person.ich.playground.playgroundrestsrv;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.Collection;

import static junit.framework.TestCase.assertTrue;

@RunWith(Parameterized.class)
@SpringBootTest
public class ParameterizedDemoTests {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"param1", "param1_ext"},
                {"param2", "param2_ext"},
                {"param3", "param3_ext"}
        });
    }

    private String input;
    private String expected;

    public ParameterizedDemoTests(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void parameterizedTest() {
        String result = input + "_ext";
        log.info("++++++++++ parameterized test: result: " + result + " expected: " + expected);
        assertTrue(result.equals(expected));
    }

}
