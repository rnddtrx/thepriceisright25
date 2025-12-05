package be.ipam.thepriceisright.repositories;

import be.ipam.thepriceisright.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // get product by name
    Optional<Product> findByName(String name);

    // check if product exists by name
    boolean existsByName(String name);

    // get product by name jpql
    @Query("SELECT p FROM Product p WHERE p.name = ?1")
    Optional<Product> getProductByName(String name);

    // get product by name native query
    @Query(value = "SELECT * FROM products p WHERE p.name = ?1", nativeQuery = true)
    Optional<Product> getProductByNameNative(String name);

}