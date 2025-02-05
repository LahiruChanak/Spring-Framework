package lk.ijse.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("form")
public class FormDataController {

    @PostMapping
    public String test(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name) {
        return id + " " + name;
    }
}
