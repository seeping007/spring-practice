package com.csp.spring.data.mongo.demo2;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Collections;

/**
 * Configuration for MongoTemplate
 * <p>
 * Not necessary
 *
 * @author CSP
 * @since 2019/1/31
 */
//@Configuration
public class MongoConfig2 extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String database;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public Mongo mongo() {
        return new MongoClient(new ServerAddress(host), Collections.singletonList(
                MongoCredential.createCredential(username, database, password.toCharArray())));
    }
}
