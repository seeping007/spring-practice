package com.csp.spring.data.mongo.demo1.repository;

import com.csp.spring.data.mongo.model.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository extending MongoRepository interface,
 * which can be autowired in other components.
 *
 * @author CSP
 * @since 2019/1/31
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    List<Person> findByName(String name);

    List<Person> findByAge(Integer age);
}
