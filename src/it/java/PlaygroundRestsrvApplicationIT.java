import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import person.ich.playground.playgroundrestsrv.PlaygroundRestsrvApplication;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlaygroundRestsrvApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaygroundRestsrvApplicationIT {

    @LocalServerPort
    private int port;

    private final String baseUrl = "http://localhost:";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("EmptyMethod")
    @Ignore
    @Test
    public void toBeIgnoredIT() {
        // The world is bad. Everybody ignores me!
    }

    @Test
    public void pingTest() {
        log.info("++++++++++ Ping test");
        when()
                .get(baseUrl + port + "/v1/ping")
                .then()
                .statusCode(200)
                .body(comparesEqualTo("pong"));
    }

    @Test
    public void helloQueryDefaultTest() {
        log.info("++++++++++ Hello query default test");
        when()
                .get(baseUrl + port + "/v1/hello")
                .then()
                .statusCode(200)
                .body(comparesEqualTo("Hello  Query Ballo! Greetings from PlaygroundRestsrv endpoint!"));
    }

    @Test
    public void helloQueryTest() {
        log.info("++++++++++ Hello query test");
        when()
                .get(baseUrl + port + "/v1/hello?name=tester")
                .then()
                .statusCode(200)
                .body(comparesEqualTo("Hello tester! Greetings from PlaygroundRestsrv endpoint!"));
    }

    @Test
    public void helloPathTest() {
        log.info("++++++++++ Hello path test");
        when()
                .get(baseUrl + port + "/v1/hello/tester")
                .then()
                .statusCode(200)
                .body(comparesEqualTo("Hello tester! Greetings from PlaygroundRestsrv endpoint!"));
    }

    @Test
    public void wrongUrlTest() {
        log.info("++++++++++ Wrong URL test");
        when()
                .get(baseUrl + port + "/v1/hellox")
                .then()
                .statusCode(404)
                .body(containsString("Not Found"));
    }

}
