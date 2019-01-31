package com.csp.spring.data.mongo.demo3;

import com.csp.spring.data.mongo.model.MongoCollection;
import com.csp.spring.data.mongo.model.Person;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Operate mongo by MongoOperations
 *
 * @author CSP
 * @since 2019/1/31
 */
@Service
public class MongoService {

    private final MongoOperations mongoOperations;

    @Autowired
    public MongoService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public void savePerson(Person person) {
        // save-or-update
        mongoOperations.save(person);
    }

    public void savePerson(String idNo, String personJson) {

        // remove by idNo
        mongoOperations.remove(Query.query(Criteria.where("idNo").is(idNo)), MongoCollection.PERSON_INFO.getName());
        // save an document with an auto ObjectId
        mongoOperations.save(Document.parse(personJson), MongoCollection.PERSON_INFO.getName());
    }

    public Object queryPersonByName(String name) {

        StringBuilder sb = new StringBuilder();

        Query query = Query.query(Criteria.where("name").is(name));
        query.fields().exclude("_id").include("idNo").include("name").include("age").include("address.city");

        mongoOperations.executeQuery(query, MongoCollection.PERSON_INFO.getName(),
                dbObject -> sb.append(dbObject.toString()));

        return sb.toString();
    }
}
