package person.ich.playground.playgroundrestsrv;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DemoController {

    private final static String s1 = "Greetings from PlaygroundRestsrv!";
    private final static String s2 = "Hello %s! ";

    @GetMapping(value = {"/ping"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String version() {
        return "pong";
    }

    @GetMapping(value = {"/hello"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "") String name) {
        return name.equals("") ? s1 : (String.format(s2, name) + s1);
    }

    @GetMapping(value = {"/hello/{name}"}, produces = MediaType.TEXT_PLAIN_VALUE)
    public String helloPathVariable(@PathVariable String name) {
        return String.format(s2, name) + s1;
    }

}
