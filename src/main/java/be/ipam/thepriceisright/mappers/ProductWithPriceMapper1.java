package be.ipam.thepriceisright.mappers;

import be.ipam.thepriceisright.dto.ProductWithPriceDto;
import be.ipam.thepriceisright.models.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductWithPriceMapper1 {
    Product toEntity(ProductWithPriceDto productWithPriceDto);

    @AfterMapping
    default void linkPrices(@MappingTarget Product product) {
        product.getPrices().forEach(price -> price.setProduct(product));
    }

    ProductWithPriceDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(ProductWithPriceDto productWithPriceDto, @MappingTarget Product product);
}