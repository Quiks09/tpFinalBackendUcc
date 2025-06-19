package com.products.products.Model.Mappers;

import com.products.products.Model.DTO.ProductDTO;
import com.products.products.Model.Entity.Category;
import com.products.products.Model.Entity.Product;
import com.products.products.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsMapper {

    private final CategoryRepository categoryRepository;

    public Product productsDTOToProductsEntity(ProductDTO productDTO){
        Product productEntity = new Product();
        productEntity.setName(productDTO.getName());
        productEntity.setStock(productDTO.getStock());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setStatus(true);
        productEntity.setStock(1);
        productEntity.setDescription("creado por mapper");

        Category categoryEntity = categoryRepository.findOneById(productDTO.getCategoryDTO().getId());
        productEntity.setCategory(categoryEntity);
        return productEntity;
    }

    public ProductDTO ProductsEntityToProductInfoDTO(Product productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        return  productDTO;
    }

}
