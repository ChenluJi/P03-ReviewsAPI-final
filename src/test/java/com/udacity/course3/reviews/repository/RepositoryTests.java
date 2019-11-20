package com.udacity.course3.reviews.repository;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

    @Autowired private EntityManager entityManager;
    @Autowired private ProductRepository productRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private CommentRepository commentRepository;
    private Product product;
    private Review review;
    private Comment comment;

    @Before
    public void setup() {
        product = new Product();
        product.setProductName("product1");
        review = new Review();
        review.setText("review1");
        review.setProductID(product.getProductID());
        comment = new Comment();
        comment.setText("comment1");
        comment.setReviewID(review.getReviewID());
        productRepository.save(product);
//        entityManager.persist(product);
        reviewRepository.save(review);
//        entityManager.persist(review);
        commentRepository.save(comment);
//        entityManager.persist(comment);

    }

    @Test
    public void testFindByProductName(){
        Product actual = productRepository.findByProductName("product1").get();
        assertEquals(product.getProductID(), actual.getProductID());
        assertEquals(product, actual);
    }

    @Test
    public void testFindAllProducts(){
        List<Product> actual = productRepository.findAll();
        assertEquals(product, actual.get(0));
    }

    @Test
    public void testFindByReviewID(){
        Integer reviewID = review.getReviewID();

        Review actual = reviewRepository.findById(reviewID).get();
        assertEquals(review, actual);
    }

    @Test
    public void testFindByCommentID(){
        Integer commentID = comment.getCommentID();

        Comment actual = commentRepository.findById(commentID).get();
        assertEquals(comment, actual);
    }
}
