package com.ecommerce.shop.service.mapper;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Product;
import org.mapstruct.*;

import java.lang.annotation.Target;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToProduct(ProductDto productDto, @MappingTarget Product product);
}
