package com.Ecommerce.Shop.dtos.Order;


import com.Ecommerce.Shop.enums.Order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.time.LocalDateTime;
import com.Ecommerce.Shop.dtos.Product.ProductDto;
import com.Ecommerce.Shop.dtos.User.UserDto;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private OrderStatus status;
    private List<ProductDto> products;
    private UserDto user;



    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}