package com.Ecommerce.Shop.dtos.Product;

import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductUpdateDto {

    @NotNull
    private Integer id;

    @MyNotBlank
    private String title;

    @NotNull
    private float price;

    @MyNotBlank
    private String imageURL;

    @NotNull
    private Integer categoryId;
}