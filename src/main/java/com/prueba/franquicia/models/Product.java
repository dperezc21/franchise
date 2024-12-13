package com.prueba.franquicia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    private Long id;
    private String name;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "fk_branch", nullable = false, updatable = false)
    private Branch branch;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}
