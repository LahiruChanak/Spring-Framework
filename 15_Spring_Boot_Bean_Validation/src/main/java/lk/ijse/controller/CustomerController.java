package lk.ijse.controller;

import jakarta.validation.Valid;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveCustomer(@Valid @RequestBody CustomerDTO dto) {
        ResponseDTO responseDTO = new ResponseDTO("Successfully Saved Customer.", HttpStatus.CREATED.value(), dto);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

}
