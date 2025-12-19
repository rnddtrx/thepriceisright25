package be.ipam.thepriceisright.services;

import be.ipam.thepriceisright.dto.AppUserDto;
import be.ipam.thepriceisright.mappers.AppUserMapper;
import be.ipam.thepriceisright.models.AppRole;
import be.ipam.thepriceisright.models.AppUser;
import be.ipam.thepriceisright.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;
    private final AppRoleService appRoleService;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper, AppRoleService appRoleService, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appUserMapper = appUserMapper;
        this.appRoleService = appRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<AppUser> findByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public AppUserDto addUser(AppUserDto user) {
        AppUser appUser = appUserMapper.toEntity(user);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        AppUser savedUser = appUserRepository.save(appUser);
        return appUserMapper.toDto(savedUser);
    }

    @Transactional
    public AppUserDto addRoleToUser(Long appUserId, Long appRoleId) {

        AppUser appUser = appUserRepository.findById(appUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        AppRole role = appRoleService.findById(appRoleId);

        appUser.getAppRoles().add(role);

        return appUserMapper.toDto(appUser);
    }

}
