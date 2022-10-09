package com.dokl57.springreactive.repository;

import com.dokl57.springreactive.dto.ProductDto;
import com.dokl57.springreactive.entity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ProductRepository extends ReactiveMongoRepository<Product, UUID> {

    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
