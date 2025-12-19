package be.ipam.thepriceisright.controllers;

import be.ipam.thepriceisright.dto.AppRoleDto;
import be.ipam.thepriceisright.services.AppRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/approle")
@Tag(name = "AppRole", description = "AppRole management API")
class AppRoleController {
    private final AppRoleService appRoleService;

    public AppRoleController(AppRoleService appRoleService) {
        this.appRoleService = appRoleService;
    }

    //Add role
    @PostMapping
    public AppRoleDto addRole(@RequestBody AppRoleDto appRole) {
        return appRoleService.addRole(appRole);
    }


}
