package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by Miguel on 5/27/2016.
 */
public class Comment {
    private String cid;
    private String uuid;
    private String pid;
    private Propuesta entity_id; //Proposal al que pertenece
    private String langcode;
    private String subject;
    private Usuario uid; //Usuario al que pertenece
    private String name;
    private String mail;
    private String homepage;
    private String created;
    private String changed;
    private String status;
    private String thread;
    private String entity_type;
    private String comment_type; //Type al que pertence
    private String field_name;
    private String default_langcode;
    private Body comment_body;

    public Comment(String cid, String uuid, String pid, Propuesta entity_id, String langcode, String subject, Usuario uid, String name, String mail, String homepage, String created, String changed, String status, String thread, String entity_type, String comment_type, String field_name, String default_langcode, Body comment_body) {

    }


}
