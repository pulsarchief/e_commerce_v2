package com.priyanshu.e_commerce_v2.dto.auth.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationResponse {

    String message;

    String jwtToken;

}
