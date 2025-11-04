package be.ipam.thepriceisright.repositories;

import be.ipam.thepriceisright.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}