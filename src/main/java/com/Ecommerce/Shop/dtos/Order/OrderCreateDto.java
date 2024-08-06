package com.Ecommerce.Shop.dtos.Order;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDto {
     @NotEmpty
     private List<Integer> productIds;
}