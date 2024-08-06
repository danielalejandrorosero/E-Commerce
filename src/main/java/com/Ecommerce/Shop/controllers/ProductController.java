package com.Ecommerce.Shop.controllers;


import com.Ecommerce.Shop.dtos.Product.ProductCreateDto;
import com.Ecommerce.Shop.dtos.Product.ProductUpdateDto;
import com.Ecommerce.Shop.services.ProductService;
import com.Ecommerce.Shop.utils.results.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private  final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody ProductCreateDto Dto) {
        var result = productService.Create(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PutMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody ProductUpdateDto Dto) {
        var result = productService.Update(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        var result = productService.GetList();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@Valid @RequestParam @NotNull Integer productId) {
        var result = productService.GetById(productId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer productId) {
        var result = productService.Delete(productId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}