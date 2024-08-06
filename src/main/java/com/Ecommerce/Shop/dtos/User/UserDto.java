package com.Ecommerce.Shop.dtos.User;


import com.Ecommerce.Shop.enums.User.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;
    private String firstName;
    private String LastName;
    private String email;
    private Role role;

}