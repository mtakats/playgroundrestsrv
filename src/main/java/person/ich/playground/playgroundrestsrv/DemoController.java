package person.ich.playground.playgroundrestsrv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DemoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String s1 = "Greetings from PlaygroundRestsrv endpoint!";
    private final static String s2 = "Hello %s! ";

    @GetMapping(value = {"/ping"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String version() {
        return "pong";
    }

    @GetMapping(value = {"/hello"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        log.info("/hello called with: " + name);
        return name.equals("") ? s1 : (String.format(s2, name) + s1);
    }

    @GetMapping(value = {"/hello/{name}"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloPathVariable(@PathVariable String name) {
        log.info("/hello/{name} called with: " + name);
        return String.format(s2, name) + s1;
    }

}
