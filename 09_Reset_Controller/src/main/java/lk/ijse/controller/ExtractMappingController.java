package lk.ijse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("extract")
public class ExtractMappingController {

    @GetMapping(path = "test1")
    public String extractMapping(){
        return "Get Mapping for Extract Mapping Test 1";
    }

    @GetMapping(path = "test2")
    public String extractMapping2(){
        return "Get Mapping for Extract Mapping Test 2";
    }

    @GetMapping(path = "test3/example/123") // This is a custom path. Give this path from postman to test this.
    public String extractMapping3(){
        return "Get Mapping for Extract Mapping Test 3";
    }

}
