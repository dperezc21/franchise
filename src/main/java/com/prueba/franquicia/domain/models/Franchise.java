package com.prueba.franquicia.domain.models;

import com.prueba.franquicia.models.Branch;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "franchise")
public class Franchise {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "franchise")
    private List<Branch> branchList;

    public Franchise() {}
    public Franchise(String franchiseName) {
        this.name = franchiseName;
    }

    public Franchise(String franchiseName, List<Branch> branchList) {
        this.name = franchiseName;
        this.branchList = branchList;
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

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    @Override
    public String toString() {
        return "Franchise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branchList=" + branchList +
                '}';
    }
}
