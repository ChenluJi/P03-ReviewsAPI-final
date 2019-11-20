package com.udacity.course3.reviews.mongorepository;

//ref: https://lankydan.dev/2017/05/29/embedded-documents-with-spring-data-and-mongodb

import com.udacity.course3.reviews.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewMongoRepository extends MongoRepository<Review, Integer> {
    Optional<Review> findFirstByReviewID(Integer reviewID);

}


