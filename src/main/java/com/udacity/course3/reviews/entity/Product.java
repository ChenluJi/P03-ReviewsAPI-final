package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="productID")
    private Integer productID;

    @Column(name="productName")
    private String productName;

    @JsonIgnore
    @OneToMany(mappedBy="product", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER) // to avoid loop
    private List<Review> reviews = new ArrayList<>();

    public Integer getProductID(){
        return productID;
    }
    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Review> getReviews(){
        return reviews;
    }
    public void setReviews(List<Review> reviews){
        this.reviews = reviews;
    }
}
