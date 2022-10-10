package com.dokl57.springreactive.controller;

import com.dokl57.springreactive.dto.ProductDto;
import com.dokl57.springreactive.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductDto> getAllProducts(){
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProductById(@PathVariable UUID id){
        log.info("get product with id ", id);
        return productService.getProduct(id);
    }

    @GetMapping("/range")
    public Flux<ProductDto> getProductsBetweenRange(@RequestParam("min") double min, @RequestParam("max") double max){
        log.info("get products with price from {} to {}", min, max);
        return productService.getProductsInPriceRange(min, max);
    }

    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        log.info("save product method called");
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono,@PathVariable UUID id){
        log.info("update product with id ", id);
        return productService.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable UUID id){
        log.info("delete product with id", id);
        return productService.deleteProduct(id);
    }

}
