package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("one")
public class WildCardMappingOptionOne {

    @GetMapping(path = "test/*/hello")  // any value can be passed to the * place.
    public String test(){
        return "Get Mapping for Wild Card Mapping Option One";
    }
}
