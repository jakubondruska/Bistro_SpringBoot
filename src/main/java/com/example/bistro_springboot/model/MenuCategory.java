package com.example.bistro_springboot.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MenuCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<MenuItem> itemToCategory = new ArrayList<>();


    public MenuCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MenuCategory() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MenuCategoryComponent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
