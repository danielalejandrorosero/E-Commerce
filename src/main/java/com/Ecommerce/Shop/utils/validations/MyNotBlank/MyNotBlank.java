package com.Ecommerce.Shop.utils.validations.MyNotBlank;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = MyNotBlankValidator.class)

@Target({ElementType.FIELD, ElementType.METHOD})
public @interface MyNotBlank {
    String message() default "¡El campo debe ser completado!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}