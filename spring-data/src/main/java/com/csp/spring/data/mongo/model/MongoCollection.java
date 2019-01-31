package com.csp.spring.data.mongo.model;

/**
 * Mongo collection enum
 *
 * @author CSP
 * @since 2019/1/31
 */
public enum MongoCollection {

    PERSON_INFO("person_info");

    private String name;

    MongoCollection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
