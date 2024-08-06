package com.Ecommerce.Shop.controllers;


import com.Ecommerce.Shop.dtos.Order.OrderCreateDto;
import com.Ecommerce.Shop.dtos.Order.OrderUpdateProductsDto;
import com.Ecommerce.Shop.dtos.Order.OrderUpdateStatusDto;
import com.Ecommerce.Shop.services.OrderService;
import com.Ecommerce.Shop.utils.results.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody OrderCreateDto Dto) {
        var result = orderService.Create(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);

    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody OrderUpdateProductsDto Dto) {
        var result = orderService.UpdateProducts(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<Result> updateStatus(@Valid @RequestBody OrderUpdateStatusDto Dto) {
        var result = orderService.UpdateStatus(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        var result = orderService.GetList();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@Valid @RequestParam @NotNull Integer orderId) {
        var result = orderService.GetById(orderId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer orderId) {
        var result = orderService.Delete(orderId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

}