package com.products.products.Controller;

import com.products.products.Model.DTO.InfoProductDTO;
import com.products.products.Model.DTO.ProductDTO;
import com.products.products.Model.Entity.Product;
import com.products.products.Service.ProductServicie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductServicie productService;

    @GetMapping("api/products")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("api/products")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> newProducts(@RequestBody Product product){
        return productService.newProduct(product);
    }


    @GetMapping("api/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PatchMapping("/api/products/{id}")
    public ResponseEntity<Product>modifyProduct(@PathVariable Long id, @RequestBody Product product){
        return productService.modifyProduct(id, product);
    }

    @DeleteMapping("/api/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }


    @GetMapping("api/product/info")
    @ResponseStatus(HttpStatus.OK)
    public  List<InfoProductDTO>getProductDTO(){
        return productService.getAllInfoProducts();
    }

    @PostMapping("api/product")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object>newProductFromDTO(@RequestBody ProductDTO productDTO){
        return productService.newProductFromDTO(productDTO);
    }
}
