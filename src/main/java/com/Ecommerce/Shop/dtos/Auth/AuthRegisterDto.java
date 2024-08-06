package com.Ecommerce.Shop.dtos.Auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;

@Data
@Builder
@AllArgsConstructor

public class AuthRegisterDto {

    @MyNotBlank
    private String name;

    @MyNotBlank
    private String firstName;

    @MyNotBlank
    private String lastName;

    @MyNotBlank
    private String email;

    @MyNotBlank
    private String password;
}