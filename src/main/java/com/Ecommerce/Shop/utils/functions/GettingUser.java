package com.Ecommerce.Shop.utils.functions;


import org.springframework.security.core.context.SecurityContextHolder;
import com.Ecommerce.Shop.entities.User;

public class GettingUser {
    public static User Get() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}