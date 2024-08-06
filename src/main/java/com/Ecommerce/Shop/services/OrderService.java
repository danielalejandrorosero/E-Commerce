package com.Ecommerce.Shop.services;

import com.Ecommerce.Shop.dtos.Category.CategoryDto;
import com.Ecommerce.Shop.dtos.Order.OrderCreateDto;
import com.Ecommerce.Shop.dtos.Order.OrderDto;
import com.Ecommerce.Shop.dtos.Order.OrderUpdateProductsDto;
import com.Ecommerce.Shop.dtos.Order.OrderUpdateStatusDto;
import com.Ecommerce.Shop.dtos.Product.ProductDto;
import com.Ecommerce.Shop.dtos.User.UserDto;
import com.Ecommerce.Shop.entities.Order;
import com.Ecommerce.Shop.entities.ProductOrders;
import com.Ecommerce.Shop.enums.Order.OrderStatus;
import com.Ecommerce.Shop.repositories.OrderRepository;
import com.Ecommerce.Shop.repositories.ProductOrdersRepository;
import com.Ecommerce.Shop.repositories.ProductRepository;
import com.Ecommerce.Shop.utils.constants.Messages;
import com.Ecommerce.Shop.utils.functions.GettingUser;
import com.Ecommerce.Shop.utils.results.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductOrdersRepository productOrdersRepository;

    public Result Create(OrderCreateDto Dto) {
        var user = GettingUser.Get();

        List<Order> orderOfUser = orderRepository.findOrdersByUser(user);
        int orderOfUserSize = orderOfUser.size();

        if (orderOfUserSize > 0) {
            var lastOrderOfUser = orderOfUser.get(orderOfUserSize - 1);

            if (lastOrderOfUser.getStatus().equals(OrderStatus.Waiting)) {
                return new ErrorResult(Messages.order_already_exists.toString());
            }
        }

        if (!controlOfProductExistence(Dto.getProductIds())) {
            return new ErrorResult(Messages.product_not_found.toString());
        }

        var order = new Order();
        order.setUser(user);
        orderRepository.save(order);

        for (var productId : Dto.getProductIds()) {
            var productsOrders = new ProductOrders();
            productsOrders.setOrder(order);
            var product = productRepository.findProductById(productId);
            productsOrders.setProduct(product);
            productOrdersRepository.save(productsOrders);
        }

        return new SuccessResult(Messages.success.toString());
    }

    public Result UpdateProducts(OrderUpdateProductsDto Dto) {
        var user = GettingUser.Get();

        var orderFromDb = orderRepository.findById(Dto.getId());

        if (orderFromDb.isEmpty()) {
            return new ErrorResult(Messages.order_not_found.toString());
        }

        if (!orderFromDb.get().getStatus().equals(OrderStatus.Waiting)) {
            return new ErrorResult(Messages.order_cant_update.toString());
        }

        if (!controlOfProductExistence(Dto.getProductIds())) {
            return new ErrorResult(Messages.product_not_found.toString());
        }

        var productOrders = productOrdersRepository.findProductOrdersByProductId(Dto.getId());
        // Aquí deberías hacer algo con `productOrders`, no solo obtenerlos.

        return new SuccessResult(Messages.success.toString());
    }

    public Result UpdateStatus(OrderUpdateStatusDto Dto) {
        var user = GettingUser.Get();

        var orderFromDto = orderRepository.findById(Dto.getId());

        if (orderFromDto.isEmpty()) {
            return new ErrorResult(Messages.order_not_found.toString());
        }

        if (!orderFromDto.get().getStatus().equals(OrderStatus.Waiting)) {
            return new ErrorResult(Messages.order_cant_update.toString());
        }

        orderFromDto.get().setStatus(Dto.getStatus());
        orderRepository.save(orderFromDto.get());

        return new SuccessResult(Messages.success.toString());
    }

    public Result Delete(Integer OrderId) {
        var order = orderRepository.findById(OrderId);

        if (order.isEmpty()) {
            return new ErrorResult(Messages.order_not_found.toString());
        }

        orderRepository.delete(order.get());
        return new SuccessResult(Messages.success.toString());
    }

    public SuccessDataResult<ArrayList<OrderDto>> GetList() {
        var orders = orderRepository.findAll();

        var orderList = new ArrayList<OrderDto>();

        for (var order : orders) {
            var orderDto = new OrderDto();

            var orderUser = order.getUser();
            var userDto = new UserDto();

            userDto.setId(orderUser.getId());
            userDto.setRole(orderUser.getRole());
            userDto.setFirstName(orderUser.getFirstname());
            userDto.setLastName(orderUser.getLastname());
            userDto.setEmail(orderUser.getEmail());

            orderDto.setId(order.getId());
            orderDto.setStatus(order.getStatus());
            orderDto.setUser(userDto);
            orderDto.setCreatedDate(order.getCreatedDate());
            orderDto.setModifiedDate(order.getModifiedDate());

            orderDto.setProducts(mappingProductsOfOrder(order));

            orderList.add(orderDto);
        }
        return new SuccessDataResult<>(orderList, Messages.success.toString());
    }

    public DataResult<OrderDto> GetById(Integer orderId) {
        var order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            return new ErrorDataResult<>(null, Messages.order_not_found.toString());
        }

        var orderDto = new OrderDto();

        var orderUser = order.get().getUser();
        var userDto = new UserDto();

        userDto.setId(orderUser.getId());
        userDto.setRole(orderUser.getRole());
        userDto.setFirstName(orderUser.getFirstname());
        userDto.setLastName(orderUser.getLastname());
        userDto.setEmail(orderUser.getEmail());

        orderDto.setId(order.get().getId());
        orderDto.setStatus(order.get().getStatus());
        orderDto.setUser(userDto);
        orderDto.setCreatedDate(order.get().getCreatedDate());
        orderDto.setModifiedDate(order.get().getModifiedDate());

        orderDto.setProducts(mappingProductsOfOrder(order.get()));

        return new SuccessDataResult<>(orderDto, Messages.success.toString());
    }


    private boolean controlOfProductExistence(List<Integer> productIds) {
        for (var productId : productIds) {
            if (productRepository.findProductById(productId) == null) {
                return false;
            }
        }
        return true;
    }

    private List<ProductDto> mappingProductsOfOrder(Order order) {
        List<ProductDto> productDtos = new ArrayList<>();
        var orderProducts = order.getProductOrders();

        for (var orderProduct : orderProducts) {
            var product = orderProduct.getProduct();

            var productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDto.setImageURL(product.getImageURL());
            productDto.setCreatedDate(product.getCreatedDate());
            productDto.setModifiedDate(product.getModifiedDate());


            if (product.getCategory() != null) {
                var categoryDto = new CategoryDto();
                categoryDto.setId(product.getCategory().getId());
                categoryDto.setTitle(product.getCategory().getTitle());
                categoryDto.setImageURL(product.getCategory().getImageURL());
                categoryDto.setCreatedDate(product.getCategory().getCreatedDate());
                categoryDto.setModifiedDate(product.getCategory().getModifiedDate());
            }

            productDtos.add(productDto);
        }
        return productDtos;
    }

}
