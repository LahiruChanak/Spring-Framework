package lk.ijse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/demo")
public class DemoController {

    // Trace > Debug > Info > Warn > Error
    private static final Logger log = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/log-demo")
    public String logDemo(){
        log.trace("Trace Message");
        log.debug("Debug Message");
        log.info("Info Message");
        log.warn("Warn Message");
        log.error("Error Message");

        return "Logging Demo Called...";
    }

}
