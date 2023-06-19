package net.javaguides.springboot.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdminDTO {

    private int adminId;

    @NotBlank(message = "user name is required")
    @Size(min = 4, max = 15, message = "admin user name must be min of 4 and max of 15 characters !!")
    private String userName;

    @Email(message = "Email address is not valid !!")
    @NotBlank(message = "Email is required !!")
    private String emailId;

    @NotBlank
    @Size(min = 3, max = 15, message = "Password must be min of 3 chars and max of 15 chars !!")
    private String password;
}
