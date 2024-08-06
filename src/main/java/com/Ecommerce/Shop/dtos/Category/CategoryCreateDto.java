package com.Ecommerce.Shop.dtos.Category;


import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {
    @MyNotBlank
    private String title;


    @MyNotBlank
    private String imageURL;
}