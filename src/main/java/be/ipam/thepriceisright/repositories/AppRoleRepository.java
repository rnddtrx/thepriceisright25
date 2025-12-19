package be.ipam.thepriceisright.repositories;

import be.ipam.thepriceisright.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
}