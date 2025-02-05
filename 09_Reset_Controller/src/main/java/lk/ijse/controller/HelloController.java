package lk.ijse.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class HelloController {

    public HelloController() {
        System.out.println("HelloController Instantiated....");
    }

    @GetMapping
    public String sayHello(){
        return "GET Method Called!";
    }

    @PutMapping
    public String sayPut(){
        return "Put Method Called!";
    }

    @DeleteMapping
    public String sayDelete(){
        return "Delete Method Called!";
    }

    @PostMapping
    public String sayPost(){
        return "Post Method Called!";
    }

    @PatchMapping
    public String sayPatch(){
        return "Patch Method Called!";
    }
}
