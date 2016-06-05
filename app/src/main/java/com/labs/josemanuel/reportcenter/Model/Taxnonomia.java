package com.labs.josemanuel.reportcenter.Model;

import java.util.ArrayList;

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
    public static ArrayList<Taxnonomia> getAllTaxonomias(){
        ArrayList<Taxnonomia> output = new ArrayList<Taxnonomia>();
        output.add(getCleaning());
        output.add(getGreenAreas());
        output.add(getMobility());
        output.add(getNeighborhoodLife());
        output.add(getOther());
        output.add(getUrbanEquipment());
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
        return null;
    }
    public static Taxnonomia getNeighborhoodLife(){
        return null;
    }
    public static Taxnonomia getOther(){
        return null;
    }
    public static Taxnonomia getUrbanEquipment(){
        return null;
    }
}

