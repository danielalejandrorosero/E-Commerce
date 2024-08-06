package com.Ecommerce.Shop.dtos.Category;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private String title;
    private String imageURL;


    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
