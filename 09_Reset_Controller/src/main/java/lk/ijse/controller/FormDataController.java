package lk.ijse.controller;

import lk.ijse.dto.CustomerDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("form")
public class FormDataController {

//    commented this method to get data using DTOs
//    @PostMapping
//    public String test(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name) {
//        return id + " " + name;
//    }

    @PostMapping
    public String test2(@ModelAttribute CustomerDTO customerDTO) {  // use @ModelAttribute to get data from large objects.This prevents data corruption.
        System.out.println(customerDTO.getFirstName());
        return customerDTO.toString();
    }
}
