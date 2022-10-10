package com.dokl57.springreactive.service;

import com.dokl57.springreactive.controller.ProductController;
import com.dokl57.springreactive.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ProductController.class)
class ProductServiceTest {

    private final WebTestClient webTestClient;
    private static final UUID FIRST_ID = UUID.randomUUID();
    private static final UUID SECOND_ID = UUID.randomUUID();

    @Autowired
    ProductServiceTest(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }

    @MockBean
    private ProductService productService;

    @Test
    public void addProductTest(){
        Mono<ProductDto> productDtoMono=Mono.just(new ProductDto(FIRST_ID,"mobile",1,10000));
        when(productService.saveProduct(productDtoMono)).thenReturn(productDtoMono);

        webTestClient.post().uri("/products")
                .body(Mono.just(productDtoMono),ProductDto.class)
                .exchange()
                .expectStatus().isOk();//200

    }


    @Test
    public void getProductsTest(){
        Flux<ProductDto> productDtoFlux=Flux.just(new ProductDto(FIRST_ID,"mobile",1,10000),
                new ProductDto(SECOND_ID,"TV",1,50000));
        when(productService.getProducts()).thenReturn(productDtoFlux);

        Flux<ProductDto> responseBody = webTestClient.get().uri("/products")
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new ProductDto(FIRST_ID,"mobile",1,10000))
                .expectNext(new ProductDto(SECOND_ID,"TV",1,50000))
                .verifyComplete();

    }



    @Test
    public void getProductTest(){
        Mono<ProductDto> productDtoMono=Mono.just(new ProductDto(FIRST_ID,"mobile",1,10000));
        when(productService.getProduct(any())).thenReturn(productDtoMono);

        Flux<ProductDto> responseBody = webTestClient.get().uri(String.format("/products/%s", FIRST_ID))
                .exchange()
                .expectStatus().isOk()
                .returnResult(ProductDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNextMatches(p->p.getName().equals("mobile"))
                .verifyComplete();
    }


    @Test
    public void updateProductTest(){
        Mono<ProductDto> productDtoMono=Mono.just(new ProductDto(FIRST_ID,"mobile",1,10000));
        when(productService.updateProduct(productDtoMono,FIRST_ID)).thenReturn(productDtoMono);

        webTestClient.put().uri(String.format("/products/update/%s",FIRST_ID))
                .body(Mono.just(productDtoMono),ProductDto.class)
                .exchange()
                .expectStatus().isOk();//200
    }

    @Test
    public void deleteProductTest(){
        given(productService.deleteProduct(any())).willReturn(Mono.empty());
        webTestClient.delete().uri(String.format("/products/delete/%s", FIRST_ID))
                .exchange()
                .expectStatus().isOk();//200
    }

}