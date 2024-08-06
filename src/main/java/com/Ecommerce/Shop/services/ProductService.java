package com.Ecommerce.Shop.services;


import com.Ecommerce.Shop.dtos.Category.CategoryDto;
import com.Ecommerce.Shop.dtos.Product.ProductCreateDto;
import com.Ecommerce.Shop.dtos.Product.ProductDto;
import com.Ecommerce.Shop.dtos.Product.ProductUpdateDto;
import com.Ecommerce.Shop.entities.Product;
import com.Ecommerce.Shop.repositories.CategoryRepository;
import com.Ecommerce.Shop.repositories.ProductRepository;
import com.Ecommerce.Shop.utils.results.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.Ecommerce.Shop.utils.constants.Messages;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor


public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;


    public Result Create(ProductCreateDto Dto) {
        var titleExists = productRepository.findProductByTitle(Dto.getTitle());
        if (titleExists != null)
            return new ErrorResult(Messages.product_already_exists.toString());

        var category = categoryRepository.findById(Dto.getCategoryId());
        if (category.isEmpty())
            return new ErrorResult(Messages.category_not_found.toString());

        var product = new Product();
        product.setTitle(Dto.getTitle());
        product.setImageURL(Dto.getImageURL());
        product.setPrice(Dto.getPrice());
        product.setCategory(category.get());
        productRepository.save(product);
        return new SuccessResult(Messages.success.toString());
    }

    public Result Update(ProductUpdateDto Dto) {
        var product = productRepository.findById(Dto.getId());
        if (product.isEmpty())
            return new ErrorResult(Messages.product_not_found.toString());

        var titleExists = productRepository.findProductByTitle(Dto.getTitle());
        if (titleExists != null)
            return new ErrorResult(Messages.product_already_exists.toString());


        product.get().setTitle(Dto.getTitle());
        product.get().setImageURL(Dto.getImageURL());
        product.get().setPrice(Dto.getPrice());

        productRepository.save(product.get());
        return new SuccessResult(Messages.success.toString());
    }

    public Result Delete(Integer ProductId) {
        var product = productRepository.findById(ProductId);

        if (product.isEmpty())
            return new ErrorResult(Messages.product_not_found.toString());

        productRepository.delete(product.get());
        return new SuccessResult(Messages.success.toString());
    }

    public DataResult<List<ProductDto>> GetList() {

        var products = productRepository.findAll();

        var productList = new ArrayList<ProductDto>();

        for (var product : products) {

            var categoryOfProduct = product.getCategory();

            var productDto = new ProductDto();

            if (categoryOfProduct != null) {
                var categoryDto = new CategoryDto();

                categoryDto.setId(categoryOfProduct.getId());
                categoryDto.setTitle(categoryOfProduct.getTitle());
                categoryDto.setImageURL(categoryOfProduct.getImageURL());
                categoryDto.setCreatedDate(categoryOfProduct.getCreatedDate());
                categoryDto.setModifiedDate(categoryOfProduct.getModifiedDate());

            }
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setImageURL(product.getImageURL());
            productDto.setPrice(product.getPrice());
            productDto.setCreatedDate(product.getCreatedDate());
            productDto.setModifiedDate(product.getModifiedDate());

            productList.add(productDto);
        }

        return new SuccessDataResult<List<ProductDto>>(productList, Messages.success.toString());
    }

    public DataResult<ProductDto> GetById(Integer ProductId) {

        var product = productRepository.findById(ProductId);

        if (product.isEmpty())
            return new ErrorDataResult<ProductDto>(null, Messages.product_not_found.toString());
        var categoryOfProduct = product.get().getCategory();
        var productDto = new ProductDto();

        if (categoryOfProduct != null) {
            var categoryDto = new CategoryDto();
            categoryDto.setId(categoryOfProduct.getId());
            categoryDto.setTitle(categoryOfProduct.getTitle());
            categoryDto.setImageURL(categoryOfProduct.getImageURL());
            categoryDto.setCreatedDate(categoryOfProduct.getCreatedDate());
            categoryDto.setModifiedDate(categoryOfProduct.getModifiedDate());
        }
        productDto.setId(product.get().getId());
        productDto.setTitle(product.get().getTitle());
        productDto.setImageURL(product.get().getImageURL());
        productDto.setPrice(product.get().getPrice());
        productDto.setCreatedDate(product.get().getCreatedDate());
        productDto.setModifiedDate(product.get().getModifiedDate());
        return new SuccessDataResult<ProductDto>(productDto, Messages.success.toString());
    }
}