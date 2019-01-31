package com.csp.spring.data.mongo.demo1;

import com.csp.spring.data.mongo.model.Address;
import com.csp.spring.data.mongo.model.Person;
import com.csp.spring.data.mongo.demo1.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MongoRepository Test
 *
 * @author CSP
 * @since 2019/1/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void savePersonTest() {

        // save: save-or-update
        personRepository.save(Person.builder()
                .idNo("1").name("csp").age(26).address(new Address("China", "Xiamen", "Huli")).build());
        personRepository.save(Person.builder()
                .idNo("2").name("lxy").age(22).address(new Address("China", "Xiamen", "Siming")).build());
        personRepository.save(Person.builder()
                .idNo("3").name("Jack").age(22).address(new Address("US", "LA", "")).build());
        Assert.assertEquals(3, personRepository.count());
    }

    @Test
    public void findPersonTest() {

        Assert.assertEquals(1, personRepository.findByName("csp").size());
        Assert.assertEquals(2, personRepository.findByAge(22).size());
    }
}