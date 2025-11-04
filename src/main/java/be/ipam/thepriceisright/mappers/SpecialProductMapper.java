package be.ipam.thepriceisright.mappers;

import be.ipam.thepriceisright.dto.SpecialProductDto;
import be.ipam.thepriceisright.models.Product;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialProductMapper {
    Product toEntity(SpecialProductDto specialProductDto);

    @AfterMapping
    default void linkPrices(@MappingTarget Product product) {
        product.getPrices().forEach(price -> price.setProduct(product));
    }

    SpecialProductDto toDto(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Product partialUpdate(SpecialProductDto specialProductDto, @MappingTarget Product product);
}