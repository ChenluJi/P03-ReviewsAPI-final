package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongorepository.ReviewMongoRepository;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
    // @Autowired
    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final ReviewMongoRepository reviewMongoRepository;

    public CommentsController(ReviewRepository reviewRepository, CommentRepository commentRepository, ReviewMongoRepository reviewMongoRepository) {
        this.reviewRepository = reviewRepository;
        this.commentRepository = commentRepository;
        this.reviewMongoRepository = reviewMongoRepository;
    }


    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<?> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {
        Optional<Review> optional = reviewRepository.findById(reviewId);
        if (optional.isPresent()) {
            comment.setReviewID(reviewId);
            commentRepository.save(comment);
            ArrayList<Comment> comments=new ArrayList<Comment>();
            comments.add(comment);
            Review review = optional.get();
            review.addComment(comment);
            reviewMongoRepository.save(review);
            return ResponseEntity.status(HttpStatus.FOUND).body(review);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review Not Found");
        }

//        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public List<?> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review> optional = reviewRepository.findById(reviewId);
        Review review = optional.get();
        List<Comment> comments = review.getComments();
        return comments;
//        throw new HttpServerErrorException(HttpStatus.NOT_IMPLEMENTED);
    }
}