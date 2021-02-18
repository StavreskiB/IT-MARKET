package com.example.it_market.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_subcategory;

    private String name_subcategory;

    public Long getId_subcategory() {
        return id_subcategory;
    }

    public void setId_subcategory(Long id_subcategory) {
        this.id_subcategory = id_subcategory;
    }

    public String getName_subcategory() {
        return name_subcategory;
    }

    public void setName_subcategory(String name_subcategory) {
        this.name_subcategory = name_subcategory;
    }
}
