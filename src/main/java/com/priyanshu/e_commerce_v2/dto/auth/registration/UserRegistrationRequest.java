package com.priyanshu.e_commerce_v2.dto.auth.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {

    @Size(min = 4, max = 16, message = "Username size must be between 4 and 16")
    @NotBlank(message = "Username cannot be blank")
    String username;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Please give a valid email")
    String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, max = 16, message = "Password size must be between 4 and 16 ")
    String password;
}
