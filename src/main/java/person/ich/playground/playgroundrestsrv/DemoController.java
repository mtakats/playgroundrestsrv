package person.ich.playground.playgroundrestsrv;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Api(tags = "Demo controller", value = "DemoQueries", description = "Supported requests")
public class DemoController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final static String s1 = "Greetings from PlaygroundRestsrv endpoint!";
    private final static String s2 = "Hello %s! ";

    @GetMapping(value = {"/ping"}, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Ping me", notes = "Some notes to ping")
    public String ping() {
        return "pong";
    }

    @GetMapping(value = {"/hello"}, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Say hello with name in query parameter", notes = "Some notes to hello with name in query parameter \"?name={name}\"")
    public String hello(
            @ApiParam(name = "name", value = "name", defaultValue = "Query Ballo")
            @RequestParam(value = "name", required = false, defaultValue = " Query Ballo") String name) {
        log.info("/hello called with: " + name);
        return name.equals("") ? s1 : (String.format(s2, name) + s1);
    }

    @GetMapping(value = {"/hello/{name}"}, produces = MediaType.TEXT_PLAIN_VALUE)
    @ApiOperation(value = "Say hello with name in path", notes = "Some notes to hello with name in path")
    public String helloPathVariable(
            @ApiParam(name = "name", value = "name", defaultValue = "Path Ballo")
            @PathVariable String name) {
        log.info("/hello/{name} called with: " + name);
        return String.format(s2, name) + s1;
    }

}
