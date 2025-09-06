package com.priyanshu.e_commerce_v2.dto.auth.passwordReset;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordResetRequest {

    @NotBlank(message = "Old Password cannot be blank")
    @Size(min = 4, max = 16, message = "Password size must be between 4 and 16 ")
    String oldPassword;

    @NotBlank(message = "New Password cannot be blank")
    @Size(min = 4, max = 16, message = "Password size must be between 4 and 16 ")
    String newPassword;

}
