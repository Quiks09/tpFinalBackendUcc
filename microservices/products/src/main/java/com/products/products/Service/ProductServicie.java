package com.products.products.Service;

import com.products.products.Model.DTO.InfoProductDTO;
import com.products.products.Model.DTO.ProductDTO;
import com.products.products.Model.Entity.Product;
import com.products.products.Model.Mappers.ProductsMapper;
import com.products.products.Repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServicie {

    private final ProductRepository productRepository;
    private final ProductsMapper productsMapper;

    public ProductServicie(ProductRepository productRepository, ProductsMapper productsMapper){
        this.productRepository = productRepository;
        this.productsMapper = productsMapper;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public ResponseEntity<Product> newProduct(Product product){
        try {
            productRepository.save(product);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<Product> getProductById(Long id){
        try {
            Product product = productRepository.findById(id).orElse(null);
            if (product == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Product> deleteProduct(Long id){
        try {
            Product productDelete = productRepository.findById(id).orElse(null);
            if (productDelete == null){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                productRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<Product> modifyProduct(Long id, Product product){
        try {
            Product modifiedProduct = productRepository.findById(id).orElse(null);
            if (modifiedProduct == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else{
                modifiedProduct.setDescription(product.getDescription());
                modifiedProduct.setName(product.getName());
                modifiedProduct.setPrice(product.getPrice());
                modifiedProduct.setStatus(product.getStatus());
                modifiedProduct.setStock(product.getStock());
                productRepository.save(modifiedProduct);
                return new ResponseEntity<>(modifiedProduct, HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    };



    public List<InfoProductDTO> getAllInfoProducts() {
        return productRepository.findAll()
                .stream()
                .map(productEntity -> new InfoProductDTO(productEntity.getId(), productEntity.getName(), productEntity.getDescription(),productEntity.getCategory(),productEntity.getStock()))
                .collect(Collectors.toList());

    }

    public ResponseEntity<Object> newProductFromDTO(ProductDTO productDTO){
        try {
            Product productEntity = productsMapper.productsDTOToProductsEntity(productDTO);
            productRepository.save(productEntity);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
