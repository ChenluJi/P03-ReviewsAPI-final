package com.udacity.course3.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Document("reviews")
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="reviewID")
    private Integer reviewID;

    @Column(name="text")
    private String text;

    @Column(name="createdTime")
    private Timestamp createdTime;

    @Column(name="productID")
    private Integer productID;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="productID", insertable=false, updatable=false)
    private Product product;

//    @OneToMany(mappedBy="review", cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    @Transient
    @Field("comments")
    private List<Comment> comments = new ArrayList<>();

    public Integer getReviewID(){
        return reviewID;
    }
    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }

    public String getText(){
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreatedTime(){
        return createdTime;
    }
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getProductID(){
        return productID;
    }
    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public List<Comment> getComments(){
        return comments;
    }
    public void setComments(List<Comment> comments){
        this.comments = comments;
    }
    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

}
