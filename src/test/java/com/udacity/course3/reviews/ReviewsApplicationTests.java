package com.udacity.course3.reviews;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@DataJpaTest
//@DataMongoTest
//@EnableMongoRepositories(basePackages = "com.udacity.course3.reviews.mongorepository")
//@EnableJpaRepositories(basePackages = "com.udacity.course3.reviews.repository")
//@EnableAutoConfiguration
public class ReviewsApplicationTests {

	@Test
	public void contextLoads() {
	}


}