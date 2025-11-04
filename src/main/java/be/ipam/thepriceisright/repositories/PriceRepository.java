package be.ipam.thepriceisright.repositories;

import be.ipam.thepriceisright.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {
}