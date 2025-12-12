package be.ipam.thepriceisright.mappers;

import be.ipam.thepriceisright.dto.ShopDto;
import be.ipam.thepriceisright.models.Shop;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ShopMapper {
    Shop toEntity(ShopDto shopDto);

    ShopDto toDto(Shop shop);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Shop partialUpdate(ShopDto shopDto, @MappingTarget Shop shop);
}