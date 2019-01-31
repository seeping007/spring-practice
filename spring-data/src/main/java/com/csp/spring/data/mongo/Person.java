package com.csp.spring.data.mongo;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Document annotation: marks a class as being a domain object that needs to be persisted to the database.
 *
 * @author CSP
 * @since 2019/1/31
 */
@Data
@Builder
@Document(collection = "person_info")
public class Person {

    /**
     * If the value of the @Id field is not null, it’s stored in the database as-is;
     * otherwise, the converter will assume you want to store an ObjectId in the database.
     */
    @Id
    private String id;

    private String name;

    private Integer age;

    private Address address;
}
