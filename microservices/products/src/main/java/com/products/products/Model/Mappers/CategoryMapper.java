package com.products.products.Model.Mappers;

import com.products.products.Model.DTO.CategoryDTO;
import com.products.products.Model.Entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category CategoryDTOToCategoryEntity(CategoryDTO categoryDTO) {
        Category CategoryEntity = new Category();
        CategoryEntity.setId(categoryDTO.getId());
        return CategoryEntity;
    }

    public CategoryDTO CategoryEntityToCategoryDTO(Category categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(categoryEntity.getId());
        return  categoryDTO;
    }
}
