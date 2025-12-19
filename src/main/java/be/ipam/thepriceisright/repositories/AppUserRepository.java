package be.ipam.thepriceisright.repositories;

import be.ipam.thepriceisright.models.AppUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    @EntityGraph(attributePaths = "appRoles")
    Optional<AppUser> findByEmail(String email);
}