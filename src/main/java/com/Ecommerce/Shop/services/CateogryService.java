package com.Ecommerce.Shop.services;

import com.Ecommerce.Shop.dtos.Category.CategoryUpdateDto;
import com.Ecommerce.Shop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.Ecommerce.Shop.utils.results.*;
import com.Ecommerce.Shop.dtos.Category.CategoryCreateDto;
import com.Ecommerce.Shop.dtos.Category.CategoryDto;
import com.Ecommerce.Shop.entities.Category;
import com.Ecommerce.Shop.utils.constants.Messages;
import com.Ecommerce.Shop.repositories.CategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CateogryService {
    // Constantes
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    // Create
    public Result Create(CategoryCreateDto Dto) {
        var category = new Category();
        category.setTitle(Dto.getTitle());
        category.setImageURL(Dto.getImageURL());
        var titleExists = categoryRepository.findCategoryByTitle(category.getTitle());
        if (titleExists != null) {
            return new ErrorResult(Messages.category_already_exists.toString());
        }
        categoryRepository.save(category);
        return new SuccessResult(Messages.success.toString());
    }

    // Update
    public Result Update(CategoryUpdateDto Dto) {
        var category = categoryRepository.findById(Dto.getCategoryId());
        if (category.isEmpty()) {
            return new ErrorResult(Messages.category_not_found.toString());
        }

        var titleExists = categoryRepository.findCategoryByTitle(Dto.getTitle());
        if (titleExists != null) {
            return new ErrorResult(Messages.category_already_exists.toString());
        }

        category.get().setTitle(Dto.getTitle());
        category.get().setImageURL(Dto.getImageURL());
        category.get().setModifiedDate(LocalDateTime.now());
        categoryRepository.save(category.get());
        return new SuccessResult(Messages.success.toString());
    }

    // Delete
    public Result Delete(Integer categoryId) {
        var category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            return new ErrorResult(Messages.category_not_found.toString());
        }

        var productOfTheCategory = productRepository.findProductsByCategory(category.get());
        for (var product : productOfTheCategory) {
            product.setCategory(null);
            productRepository.save(product);
        }

        categoryRepository.delete(category.get());
        return new SuccessResult(Messages.success.toString());
    }

    // Get List
    public DataResult<List<CategoryDto>> GetList() {
        var categories = categoryRepository.findAll();

        var categoryList = new ArrayList<CategoryDto>();
        for (var category : categories) {
            var categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setTitle(category.getTitle());
            categoryDto.setImageURL(category.getImageURL());
            categoryDto.setCreatedDate(category.getCreatedDate());
            categoryDto.setModifiedDate(category.getModifiedDate());
            categoryList.add(categoryDto);
        }
        return new SuccessDataResult<List<CategoryDto>>(categoryList, Messages.success.toString());
    }

    // Get By Id
    public DataResult<CategoryDto> GetById(Integer categoryId) {
        var category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            return new ErrorDataResult<CategoryDto>(null, Messages.category_not_found.toString());
        }

        var categoryDto = new CategoryDto();
        categoryDto.setId(category.get().getId());
        categoryDto.setTitle(category.get().getTitle());
        categoryDto.setImageURL(category.get().getImageURL());
        categoryDto.setCreatedDate(category.get().getCreatedDate());
        categoryDto.setModifiedDate(category.get().getModifiedDate());
        return new SuccessDataResult<CategoryDto>(categoryDto, Messages.success.toString());
    }
}
