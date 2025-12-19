package be.ipam.thepriceisright.mappers;

import be.ipam.thepriceisright.dto.AppUserDto;
import be.ipam.thepriceisright.models.AppUser;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppUserMapper {
    AppUser toEntity(AppUserDto appUserDto);

    AppUserDto toDto(AppUser appUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AppUser partialUpdate(AppUserDto appUserDto, @MappingTarget AppUser appUser);
}