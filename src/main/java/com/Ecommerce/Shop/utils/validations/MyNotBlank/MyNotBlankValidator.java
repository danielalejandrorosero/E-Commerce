package com.Ecommerce.Shop.utils.validations.MyNotBlank;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;


public class MyNotBlankValidator implements ConstraintValidator<MyNotBlank, String> {

    @Override
    public void initialize(MyNotBlank constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !value.trim().isEmpty();
    }
}