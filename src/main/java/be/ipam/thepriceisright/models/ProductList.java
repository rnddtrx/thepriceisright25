package be.ipam.thepriceisright.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product_list")
public class ProductList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String type;
    @OneToOne(cascade = CascadeType.ALL)
    private AppUser user;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Product> products;
}
