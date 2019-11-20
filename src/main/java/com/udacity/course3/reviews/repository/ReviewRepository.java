package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    @Query("SELECT r.productID FROM Review r where r.productID = :productID ORDER BY r.ReviewID")
    @Query(
            value="SELECT r.productID FROM reviews r where r.productID = :productID ORDER BY r.ReviewID",
            nativeQuery = true)
    List<Integer> findReviewIDByProductID(@Param("productID") Integer productID);
}
