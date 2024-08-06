package com.Ecommerce.Shop.dtos.Auth;


import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginDto {
    @Email
    private String email;

    @MyNotBlank
    private String password;
}