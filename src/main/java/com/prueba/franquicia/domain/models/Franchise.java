package com.prueba.franquicia.domain.models;

import jakarta.persistence.*;

@Entity
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Franchise() {}
    public Franchise(String franchiseName) {
        this.name = franchiseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Franchise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
