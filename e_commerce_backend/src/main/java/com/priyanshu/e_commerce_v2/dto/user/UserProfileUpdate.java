package com.priyanshu.e_commerce_v2.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileUpdate {

    @Size(min = 5, max = 20)
    String name;

    @Email(message = "New email cannot be blank")
    String email;

}
