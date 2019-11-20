package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="commentID")
    private Integer commentID;

    @Column(name="text")
    private String text;

    @Column(name="createdTime")
    private Timestamp createdTime;

    @Column(name="reviewID")
    private Integer reviewID;

//    @ManyToOne(fetch=FetchType.LAZY)
//    @JoinColumn(name="reviewID", insertable=false, updatable=false)
//    private Review review;

    public Integer getCommentID(){
        return commentID;
    }
    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getText(){
        return text;
    }
    public void setText(String Text) {
        this.text = text;
    }

    public Timestamp getCreatedTime(){
        return createdTime;
    }
    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getReviewID(){
        return reviewID;
    }
    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
    }


}
