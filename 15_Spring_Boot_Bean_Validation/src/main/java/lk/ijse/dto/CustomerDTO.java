package lk.ijse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerDTO {

    @NotBlank(message = "Name is required") // empty fix validation
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name should contain only letters and spaces")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^(\\+94|0)\\d{9}$", message = "Phone number should start with +94 or 0 and have 9 digits")
    private String contact;

}
