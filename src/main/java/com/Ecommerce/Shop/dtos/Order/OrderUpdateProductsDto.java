package com.Ecommerce.Shop.dtos.Order;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateProductsDto {
    @NotNull
    private Integer id;

    @NotEmpty
    private List<Integer> productIds;
}