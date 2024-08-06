package com.Ecommerce.Shop.dtos.Category;







import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateDto {
    @MyNotBlank
    private Integer categoryId;
    @NotBlank
    private String title;
    @MyNotBlank
    private String imageURL;
}