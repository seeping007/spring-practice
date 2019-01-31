package com.csp.spring.data.mongo.demo1;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration for MongoRepository
 * <p>
 * The MongoRepository follows the Spring Data-centric approach
 * and comes with more flexible and complex API operations,
 * based on the well-known access patterns in all Spring Data projects.
 *
 * @author CSP
 * @since 2019/1/31
 */
@Configuration
@EnableMongoRepositories(basePackages = {"com.csp.spring.data.mongo.demo1.repository"})
public class MongoConfig1 {
}
