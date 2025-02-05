package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("two")
public class WildCardMappingOptionTwo {

//    @GetMapping(path = "test/**/hello")  // any value can be passed to the ** place. (commented this to prevent error. New Spring versions does not support this.)
    public String test(){
        return "Get Mapping for Wild Card Mapping Option Two";
    }
}
