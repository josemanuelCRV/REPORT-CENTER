package com.labs.josemanuel.reportcenter.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sebastian on 05/06/2016.
 */
public class Taxnonomia {
    private static final String[] DATA={"taxonomy_vocabulary","category"};
    private Integer tid;
    private String uuid;
    private String langcode;
    private String name;
    private String[] relationshipsData;
    private String[] relationshipsLinks;
    private String links;

    public Taxnonomia(Integer tid, String uuid, String langcode, String name, String[] relationshipsData, String[] relationshipsLinks, String links) {
        this.tid = tid;
        this.uuid = uuid;
        this.langcode = langcode;
        this.name = name;
        this.relationshipsData = relationshipsData;
        this.relationshipsLinks = relationshipsLinks;
        this.links = links;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getRelationshipsData() {
        return relationshipsData;
    }

    public void setRelationshipsData(String[] relationshipsData) {
        this.relationshipsData = relationshipsData;
    }

    public String[] getRelationshipsLinks() {
        return relationshipsLinks;
    }

    public void setRelationshipsLinks(String[] relationshipsLinks) {
        this.relationshipsLinks = relationshipsLinks;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
    public static ArrayList<Taxnonomia> getAllTaxonomiasArryaList(){
        ArrayList<Taxnonomia> output = new ArrayList<Taxnonomia>();
        output.add(getCleaning());
        output.add(getGreenAreas());
        output.add(getMobility());
        output.add(getNeighborhoodLife());
        output.add(getOther());
        output.add(getUrbanEquipment());
        return output;
    }
    public static HashMap<Integer,Taxnonomia> getAllTaxonomiasHashMap(){
        HashMap<Integer,Taxnonomia> output = new HashMap<>();
        output.put(getCleaning().getTid(),getCleaning());
        output.put(getGreenAreas().getTid(),getGreenAreas());
        output.put(getMobility().getTid(),getMobility());
        output.put(getNeighborhoodLife().getTid(),getNeighborhoodLife());
        output.put(getOther().getTid(),getOther());
        output.put(getUrbanEquipment().getTid(),getUrbanEquipment());

        return output;
    }
    public static Taxnonomia getCleaning(){

        String[] relationshipsData  = {"taxonomy_vocabulary","category"};
        String[] relationshipsLinks = {"http://stag.hackityapp.com/en/api/category/4/relationships/vid?_format=api_json","http://stag.hackityapp.com/en/api/category/4/vid?_format=api_json"};
        Taxnonomia Cleaning = new Taxnonomia(4,"5baef080-f9c1-4abb-9bf6-4de9e7358251","en","Cleaning",DATA,relationshipsLinks,"http://stag.hackityapp.com/en/api/category/4?_format=api_json");
        return Cleaning;
    }
    public static Taxnonomia getGreenAreas(){
        //Integer tid, String uuid, String langcode, String name, String[] relationshipsData, String[] relationshipsLinks, String links
        String[] relationshipsLinks ={"http://stag.hackityapp.com/en/api/category/6/relationships/vid?_format=api_json","http://stag.hackityapp.com/en/api/category/6/vid?_format=api_json"};
        Taxnonomia GreenAreas = new Taxnonomia(6,"fe3bc75a-04fa-464b-beb2-063ad438aa65","en","Green areas",DATA,relationshipsLinks,"http://stag.hackityapp.com/en/api/category/6?_format=api_json");
        return GreenAreas;
    }
    public static Taxnonomia getMobility(){
        String[] relationshipsLinks ={"http://stag.hackityapp.com/en/api/category/5/relationships/vid?_format=api_json","http://stag.hackityapp.com/en/api/category/5/vid?_format=api_json"};
        Taxnonomia Mobility = new Taxnonomia(5,"318ceee3-7894-4b82-a377-d9d1a7e225d4","en","Mobility",DATA,relationshipsLinks,"http://stag.hackityapp.com/en/api/category/5?_format=api_json" );
        return Mobility;
    }

    public static Taxnonomia getNeighborhoodLife(){
        String[] relationshipsLinks ={"http://stag.hackityapp.com/en/api/category/7/relationships/vid?_format=api_json","http://stag.hackityapp.com/en/api/category/7/vid?_format=api_json"};
        Taxnonomia NeighborhoodLife = new Taxnonomia(7,"6fd17cdd-9b46-48b3-ae2e-9185e4b807ff","en","Neighborhood life",DATA,relationshipsLinks,"http://stag.hackityapp.com/en/api/category/7?_format=api_json");
        return NeighborhoodLife;
    }
    public static Taxnonomia getOther(){
        //Integer tid, String uuid, String langcode, String name, String[] relationshipsData, String[] relationshipsLinks, String links
        String[] relationshipsLinks ={"http://stag.hackityapp.com/en/api/category/8/relationships/vid?_format=api_json","http://stag.hackityapp.com/en/api/category/8/vid?_format=api_json"};
        Taxnonomia Other = new Taxnonomia(8,"87579216-04df-4b15-b9f4-facc4a0bb34b","en","Other",DATA,relationshipsLinks,"http://stag.hackityapp.com/en/api/category/8?_format=api_json");
        return Other;
    }
    public static Taxnonomia getUrbanEquipment(){
        String[] relationshipsLinks = {"http://stag.hackityapp.com/en/api/category/3/relationships/vid?_format=api_json","http://stag.hackityapp.com/en/api/category/3/vid?_format=api_json"};
        Taxnonomia UrbanEquipment = new Taxnonomia(3,"239c0953-e898-4ecc-9d23-9414b06bb3a3","en","Urban equipment",DATA,relationshipsLinks,"http://stag.hackityapp.com/en/api/category/3?_format=api_json");
        return null;
    }
}