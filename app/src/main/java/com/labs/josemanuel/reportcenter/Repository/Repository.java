package com.labs.josemanuel.reportcenter.Repository;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertOneOptions;
import com.mongodb.client.model.Projections;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miguel on 22-09-16.
 */
public class Repository implements DBHelper{

    static MongoCollection<Document> coll;
    private static Repository repository;

    public static Repository getRepository(String mongoURIString) {
        if(repository == null) {
            MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURIString));
            MongoDatabase db = mongoClient.getDatabase("cityhack_db");
            coll= db.getCollection("proposals");
            repository = new Repository();
        }

        return repository;


    }

    @Override
    public List<ProposalDTOout> getProposals() {
        List<ProposalDTOout> results= new ArrayList<ProposalDTOout>();


        FindIterable<Document> raw = coll.find();

        for(Document doc : raw)
            results.add(parseResult(doc.toJson()));
        return results;
    }

    @Override
    public int insertProposal(ProposalVOin proposalVOin) {
        try {
            Document doc= Document.parse(new ObjectMapper().writeValueAsString(proposalVOin));
            coll.insertOne(doc);
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private ProposalDTOout parseResult(String jsonResult){
        ObjectMapper objectMapper= new ObjectMapper();
        try {
            return objectMapper.readValue(jsonResult,ProposalDTOout.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String removeCurlies(String withCurlies){

        return withCurlies.substring(0,withCurlies.length());
    }


}
