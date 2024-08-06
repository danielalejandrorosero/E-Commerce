package com.Ecommerce.Shop.dtos.Order;


import com.Ecommerce.Shop.enums.Order.OrderStatus;
import com.Ecommerce.Shop.utils.validations.MyNotBlank.MyNotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdateStatusDto {
    @NotNull
    private Integer id;

    @MyNotBlank
    private OrderStatus status;
}