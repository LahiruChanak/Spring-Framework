package lk.ijse.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HelloController {

    @GetMapping
    public String hello(HttpServletRequest request) {
        return "Hello Spring Security: " + request.getSession().getId();
    }

}
