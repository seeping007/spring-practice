package com.csp.spring.data.mongo.demo2;

import com.csp.spring.data.mongo.Address;
import com.csp.spring.data.mongo.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MongoTemplate Test
 *
 * @author CSP
 * @since 2019/1/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void savePersonTest() {

        // save-or-update
        mongoTemplate.save(Person.builder()
                .id("3").name("Jack").age(35).address(new Address("US", "LA", "")).build(), "person_info");
        Assert.assertEquals(1, mongoTemplate.find(Query.query(Criteria.where("name").is("Jack")), Person.class).size());
    }

    @Test
    public void findPersonTest() {

        Person person = mongoTemplate.findOne(Query.query(Criteria.where("name").is("Jack")), Person.class);
        System.out.println("Jack: age = " + person.getAge());
    }

}