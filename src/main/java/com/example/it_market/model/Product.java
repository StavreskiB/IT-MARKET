package com.example.it_market.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @NotNull
    @Min(value = 0,message = "Price must be bigger than 2")
    private Float price;
    private float disc_price;

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    private List<ShoppingCart> shoppingCarts;


    @Column(name = "image")
    @Lob
    private String imageBase64;


    @JoinColumn(name = "id_category")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "id_subcategory")
    @ManyToOne
    private Subcategory subcategory;

    private String published;


    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;


    public Product(Object o, String name, Float price, Float disc_price, String description, String published) {}

    public Product() {
        this.id = id;
        this.name = name;
        this.price = price;
        this.disc_price = disc_price;
        this.description = description;
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public float getDisc_price() {
        return disc_price;
    }

    public void setDisc_price(float disc_price) {
        this.disc_price = disc_price;
    }

    //    public MultipartFile getImage() {
//        return image;
//    }
//
//    public void setImage(MultipartFile image) {
//        this.image = image;
//    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
