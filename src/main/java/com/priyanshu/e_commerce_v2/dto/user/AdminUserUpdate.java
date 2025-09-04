package com.priyanshu.e_commerce_v2.dto.user;

import com.priyanshu.e_commerce_v2.entity.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserUpdate {

    UserRole role;

    Boolean enabled;

}
