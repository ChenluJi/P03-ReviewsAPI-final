package com.udacity.course3.reviews.mongorepository;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.mongorepository.ReviewMongoRepository;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// junit5 test
@DataMongoTest
@ExtendWith(SpringExtension.class)
public class MongoRepositoryTests {
//    @Autowired
//    private MongoTemplate mongoTemplate;
    @Autowired
    private ReviewMongoRepository reviewMongoRepository;

    private Review reviewMongo;
    private Comment comment;
    private ArrayList<Comment> comments=new ArrayList<Comment>();

    //     embedded mongodb testing
    private MongodExecutable mongodExecutable;

    @AfterEach
    public void clean() {
//        reviewMongoRepository.deleteAll(); // this line is not executed. why?
        mongodExecutable.stop();
    }

    @BeforeEach
    public void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
//        mongoTemplate = new MongoTemplate(new MongoClient(ip, port), "test");
    }
    @Test
    public void testFindByMongoReviewID(){
        assertNotNull(reviewMongoRepository);

        reviewMongo = new Review();
        reviewMongo.setText("review1");

        reviewMongo.setReviewID(1); // reviewID is not treated as _id because multiple documents are saved with the same reviewID

        comment = new Comment();
        comment.setText("comment1");
        comment.setReviewID(reviewMongo.getReviewID());

        comments.add(comment);
        reviewMongo.setComments(comments);
        reviewMongoRepository.save(reviewMongo);

        Integer reviewMongoID = reviewMongo.getReviewID();
        Review actual = reviewMongoRepository.findFirstByReviewID(reviewMongoID).get();
//        List<Review> actual = reviewMongoRepository.findAll();
//        assertEquals(1, actual.size());
        assertEquals(reviewMongo.getText(), actual.getText());
        assertEquals(reviewMongo.getReviewID(), actual.getReviewID());
        assertEquals(reviewMongo.getComments().get(0).getText(), actual.getComments().get(0).getText());
    }

}
