package com.Ecommerce.Shop.dtos.Product;


import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor



public class ProductDto {

    @MyNotBlank
    private String title;

    @NotNull
    private Float price;

    @MyNotBlank
    private String imageURL;

    @NotNull
    private Integer categoryId;

    public void setCreatedDate(LocalDateTime createdDate) {
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
    }

    public void setId(Integer id) {
    }
}