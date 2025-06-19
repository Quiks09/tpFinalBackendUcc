package com.products.products.Controller;



import com.products.products.Model.Entity.Category;
import com.products.products.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class CategoryController {
    private  final CategoryService categoryService;

    @GetMapping("/api/categories")
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/api/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> newCategories(@RequestBody Category category) {
        return categoryService.newCategory(category);
    }
}
