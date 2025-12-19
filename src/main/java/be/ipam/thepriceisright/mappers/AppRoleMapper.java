package be.ipam.thepriceisright.mappers;

import be.ipam.thepriceisright.dto.AppRoleDto;
import be.ipam.thepriceisright.models.AppRole;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppRoleMapper {
    AppRole toEntity(AppRoleDto appRoleDto);

    AppRoleDto toDto(AppRole appRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AppRole partialUpdate(AppRoleDto appRoleDto, @MappingTarget AppRole appRole);
}