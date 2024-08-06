package com.Ecommerce.Shop.controllers;


import com.Ecommerce.Shop.dtos.Category.CategoryCreateDto;
import com.Ecommerce.Shop.dtos.Category.CategoryUpdateDto;
import com.Ecommerce.Shop.services.CateogryService;
import com.Ecommerce.Shop.utils.results.Result;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CateogryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Result> create(@Valid @RequestBody CategoryCreateDto Dto) {
        var result = categoryService.Create(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @PostMapping("/update")
    public ResponseEntity<Result> update(@Valid @RequestBody CategoryUpdateDto Dto) {
        var result = categoryService.Update(Dto);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getList")
    public ResponseEntity<Result> getList() {
        var result = categoryService.GetList();
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @GetMapping("/getById")
    public ResponseEntity<Result> getById(@Valid @RequestParam @NotNull Integer categoryId) {
        var result = categoryService.GetById(categoryId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Result> delete(@Valid @RequestParam @NotNull Integer categoryId) {
        var result = categoryService.Delete(categoryId);
        return ResponseEntity.status(result.isSuccess() ? 200 : 400).body(result);
    }
}