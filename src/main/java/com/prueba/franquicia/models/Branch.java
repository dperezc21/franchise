package com.prueba.franquicia.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "fk_franchise", nullable = false, updatable = false)
    private Franchise franchise;

    @JoinTable(
            name = "rel_branches_products",
            joinColumns = @JoinColumn(name = "fk_branch", nullable = false),
            inverseJoinColumns = @JoinColumn(name="fk_product", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> productList;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
