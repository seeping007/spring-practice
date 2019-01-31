package com.csp.spring.data.mongo.demo3;

import com.alibaba.fastjson.JSON;
import com.csp.spring.data.mongo.model.Address;
import com.csp.spring.data.mongo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * MongoOperations Test
 *
 * @author CSP
 * @since 2019/1/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MongoServiceTest {

    @Autowired
    private MongoService mongoService;

    @Test
    public void savePersonTest() {

        Person person = Person.builder()
                .idNo("1").name("csp").age(30).address(new Address("China", "Xiamen", "Huli")).build();

        // save-or-update
//        mongoService.savePerson(person);

        mongoService.savePerson("1", JSON.toJSONString(person));
    }

    @Test
    public void queryPersonTest() {

        Object res = mongoService.queryPersonByName("csp");
        System.out.println(res);
    }

}