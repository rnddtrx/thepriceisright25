package be.ipam.thepriceisright.mappers;

import be.ipam.thepriceisright.dto.PriceDto;
import be.ipam.thepriceisright.models.Price;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {
    Price toEntity(PriceDto priceDto);

    PriceDto toDto(Price price);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Price partialUpdate(PriceDto priceDto, @MappingTarget Price price);
}