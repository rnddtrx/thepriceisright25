package be.ipam.thepriceisright.services;

import be.ipam.thepriceisright.dto.AppRoleDto;
import be.ipam.thepriceisright.mappers.AppRoleMapper;
import be.ipam.thepriceisright.models.AppRole;
import be.ipam.thepriceisright.repositories.AppRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class AppRoleService {
    private final AppRoleRepository appRoleRepository;
    private final AppRoleMapper appRoleMapper;

    public AppRoleService(AppRoleRepository appRoleRepository, AppRoleMapper appRoleMapper) {
        this.appRoleRepository = appRoleRepository;
        this.appRoleMapper = appRoleMapper;
    }

    public AppRoleDto addRole(AppRoleDto role) {
        AppRole appRole = appRoleMapper.toEntity(role);
        AppRole savedRole = appRoleRepository.save(appRole);
        return appRoleMapper.toDto(savedRole);
    }

    public AppRole findById(Long id) {
        return appRoleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
}
