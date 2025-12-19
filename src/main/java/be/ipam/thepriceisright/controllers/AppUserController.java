package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.dto.AppUserDto;
import be.ipam.thepriceisright.services.AppUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appusers")
@Tag(name = "AppUser", description = "AppUser management API")
class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/")
    public AppUserDto addUser(@RequestBody AppUserDto user) {
        return appUserService.addUser(user);
    }

    @PostMapping("/{appUserId}/roles/{appRoleId}")
    public AppUserDto addRoleToUser(@PathVariable Long appUserId,
                              @PathVariable Long appRoleId) {
        return appUserService.addRoleToUser(appUserId, appRoleId);
    }

}
