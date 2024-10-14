package com.incognito.joblisting.repo;

import com.incognito.joblisting.model.Post;
import com.mongodb.client.MongoClient;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.AggregateIterable;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchRepoImpl implements SearchRepo {

    private final MongoClient mongoClient;
    private final MongoConverter mongoConverter;

    public SearchRepoImpl(MongoClient mongoClient, MongoConverter mongoConverter) {
        this.mongoClient = mongoClient;
        this.mongoConverter = mongoConverter;
    }


    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("incognito");
        MongoCollection<Document> collection = database.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("desc", "techs")))),
                new Document("$sort",
                        new Document("exp", -1L)),
                new Document("$limit", 5L)));

        result.forEach(doc -> posts.add(mongoConverter.read(Post.class, doc)));

        return posts;
    }
}
