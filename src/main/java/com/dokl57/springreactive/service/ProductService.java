package com.dokl57.springreactive.service;

import com.dokl57.springreactive.dto.ProductDto;
import com.dokl57.springreactive.entity.Product;
import com.dokl57.springreactive.repository.ProductRepository;
import com.dokl57.springreactive.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
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

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono){
        return productDtoMono
                .map(AppUtils::dtoToEntity)
                .flatMap(productRepository::insert)
                .map(AppUtils::entityToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDtoMono, UUID id){
        return productRepository.findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e -> e.setId(id)))
                .flatMap(productRepository::save)
                .map(AppUtils::entityToDto);
    }

    public Mono<Void> deleteProduct(UUID id){
        return productRepository.deleteById(id);
    }


}
