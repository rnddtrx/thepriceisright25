package be.ipam.thepriceisright.repositories;

import be.ipam.thepriceisright.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}