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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branch")
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

    public Franchise getFranchise() {
        return franchise;
    }

    public void setFranchise(Franchise franchise) {
        this.franchise = franchise;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", franchise=" + franchise +
                ", productList=" + productList +
                '}';
    }
}
