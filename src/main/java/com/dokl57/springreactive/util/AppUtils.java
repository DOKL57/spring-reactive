package com.dokl57.springreactive.util;

import com.dokl57.springreactive.dto.ProductDto;
import com.dokl57.springreactive.entity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {


    public static Product dtoToEntity(ProductDto productDto){
        Product productEntity = new Product();
        BeanUtils.copyProperties(productDto, productEntity);
        return productEntity;
    }

    public static ProductDto entityToDto(Product productEntity){
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productEntity, productDto);
        return productDto;
    }
}
