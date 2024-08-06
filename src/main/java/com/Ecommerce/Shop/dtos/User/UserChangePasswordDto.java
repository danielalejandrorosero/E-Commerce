package com.Ecommerce.Shop.dtos.User;


import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordDto {
    @MyNotBlank
    private String oldPassword;
    @MyNotBlank
    private String newPassword;
}