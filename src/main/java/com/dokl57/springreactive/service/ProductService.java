package com.dokl57.springreactive.service;

import com.dokl57.springreactive.dto.ProductDto;
import com.dokl57.springreactive.entity.Product;
import com.dokl57.springreactive.repository.ProductRepository;
import com.dokl57.springreactive.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<ProductDto> getProducts(){
        return productRepository.findAll().map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> getProduct(UUID id){
        return productRepository.findById(id).map(AppUtils::entityToDto);
    }

    public Flux<ProductDto> getProductsInPriceRange(double min, double max){
        return productRepository.findByPriceBetween(Range.closed(min, max));
    }


}
