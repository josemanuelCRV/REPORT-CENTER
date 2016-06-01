package com.labs.josemanuel.reportcenter.Model;

import org.json.JSONObject;

/**
 * Created by Miguel on 5/27/2016.
 */
/*public class Comment {
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

    public Comment(String cid, String uuid, String pid, Propuesta entity_id, String langcode, String subject, Usuario uid, String name, String mail, String homepage, String created, String changed, String status, String thread, String entity_type, String comment_type, String field_name, String default_langcode, Body comment_body) {}
    public Comment(String cid, String uuid, String pid, Propuesta entity_id, String langcode, String subject, Usuario uid, String name, String mail, String homepage, String created, String changed, String status, String thread, String entity_type, String comment_type, String field_name, String default_langcode, Body comment_body) {}


}*/

public class Comment {
    String type_data;
    String id_data;
    String cid_attributes;
    String uuid_attributes;
    String langcode_attributes;
    String subject_attributes;
    String name_attributes;
    String homepage_attributes;
    String created_attributes;
    String changed_attributes;
    String status_attributes;
    String thread_attributes;
    String entity_type_attributes;
    String field_name_attributes;
    String default_langcode_attributes;
    String value_comment_body_attributes;
    String format_comment_body_attributes;
    String data_pid_relationships;
    String type_data_entity_id_relationships;
    String id_data_entity_id_relationships;
    String self_links_entity_id_relationships;
    String related_links_entity_id_relationships;
    String type_data_uid_relationships;
    String id_data_uid_relationships;
    String self_links_uid_relationships;
    String related_links_uid_relationships;
    String type_data_comment_type_relationships;
    String id_data_comment_type_relationships;
    String self_links_comment_type_relationships;
    String related_links_comment_type_relationships;
    String self_links_data;
    String self_links;

    public Comment(String type_data, String id_data, String cid_attributes, String uuid_attributes, String langcode_attributes, String subject_attributes, String name_attributes, String homepage_attributes, String created_attributes, String changed_attributes, String status_attributes, String thread_attributes, String entity_type_attributes, String field_name_attributes, String default_langcode_attributes, String value_comment_body_attributes, String format_comment_body_attributes, String data_pid_relationships, String type_data_entity_id_relationships, String id_data_entity_id_relationships, String self_links_entity_id_relationships, String related_links_entity_id_relationships, String type_data_uid_relationships, String id_data_uid_relationships, String self_links_uid_relationships, String related_links_uid_relationships, String type_data_comment_type_relationships, String id_data_comment_type_relationships, String self_links_comment_type_relationships, String related_links_comment_type_relationships, String self_links_data, String self_links) {
        this.type_data = type_data;
        this.id_data = id_data;
        this.cid_attributes = cid_attributes;
        this.uuid_attributes = uuid_attributes;
        this.langcode_attributes = langcode_attributes;
        this.subject_attributes = subject_attributes;
        this.name_attributes = name_attributes;
        this.homepage_attributes = homepage_attributes;
        this.created_attributes = created_attributes;
        this.changed_attributes = changed_attributes;
        this.status_attributes = status_attributes;
        this.thread_attributes = thread_attributes;
        this.entity_type_attributes = entity_type_attributes;
        this.field_name_attributes = field_name_attributes;
        this.default_langcode_attributes = default_langcode_attributes;
        this.value_comment_body_attributes = value_comment_body_attributes;
        this.format_comment_body_attributes = format_comment_body_attributes;
        this.data_pid_relationships = data_pid_relationships;
        this.type_data_entity_id_relationships = type_data_entity_id_relationships;
        this.id_data_entity_id_relationships = id_data_entity_id_relationships;
        this.self_links_entity_id_relationships = self_links_entity_id_relationships;
        this.related_links_entity_id_relationships = related_links_entity_id_relationships;
        this.type_data_uid_relationships = type_data_uid_relationships;
        this.id_data_uid_relationships = id_data_uid_relationships;
        this.self_links_uid_relationships = self_links_uid_relationships;
        this.related_links_uid_relationships = related_links_uid_relationships;
        this.type_data_comment_type_relationships = type_data_comment_type_relationships;
        this.id_data_comment_type_relationships = id_data_comment_type_relationships;
        this.self_links_comment_type_relationships = self_links_comment_type_relationships;
        this.related_links_comment_type_relationships = related_links_comment_type_relationships;
        this.self_links_data = self_links_data;
        this.self_links = self_links;
    }

    public Comment(String cid_attributes, String id_data_uid_relationships, String created_attributes, String changed_attributes, String value_comment_body_attributes, String format_comment_body_attributes) {
        this.cid_attributes = cid_attributes;
        this.id_data_uid_relationships = id_data_uid_relationships;
        this.created_attributes = created_attributes;
        this.changed_attributes = changed_attributes;
        this.value_comment_body_attributes = value_comment_body_attributes;
        this.format_comment_body_attributes = format_comment_body_attributes;
    }

    public String getType_data() {
        return type_data;
    }

    public void setType_data(String type_data) {
        this.type_data = type_data;
    }

    public String getId_data() {
        return id_data;
    }

    public void setId_data(String id_data) {
        this.id_data = id_data;
    }

    public String getCid_attributes() {
        return cid_attributes;
    }

    public void setCid_attributes(String cid_attributes) {
        this.cid_attributes = cid_attributes;
    }

    public String getUuid_attributes() {
        return uuid_attributes;
    }

    public void setUuid_attributes(String uuid_attributes) {
        this.uuid_attributes = uuid_attributes;
    }

    public String getLangcode_attributes() {
        return langcode_attributes;
    }

    public void setLangcode_attributes(String langcode_attributes) {
        this.langcode_attributes = langcode_attributes;
    }

    public String getSubject_attributes() {
        return subject_attributes;
    }

    public void setSubject_attributes(String subject_attributes) {
        this.subject_attributes = subject_attributes;
    }

    public String getName_attributes() {
        return name_attributes;
    }

    public void setName_attributes(String name_attributes) {
        this.name_attributes = name_attributes;
    }

    public String getHomepage_attributes() {
        return homepage_attributes;
    }

    public void setHomepage_attributes(String homepage_attributes) {
        this.homepage_attributes = homepage_attributes;
    }

    public String getCreated_attributes() {
        return created_attributes;
    }

    public void setCreated_attributes(String created_attributes) {
        this.created_attributes = created_attributes;
    }

    public String getChanged_attributes() {
        return changed_attributes;
    }

    public void setChanged_attributes(String changed_attributes) {
        this.changed_attributes = changed_attributes;
    }

    public String getStatus_attributes() {
        return status_attributes;
    }

    public void setStatus_attributes(String status_attributes) {
        this.status_attributes = status_attributes;
    }

    public String getThread_attributes() {
        return thread_attributes;
    }

    public void setThread_attributes(String thread_attributes) {
        this.thread_attributes = thread_attributes;
    }

    public String getEntity_type_attributes() {
        return entity_type_attributes;
    }

    public void setEntity_type_attributes(String entity_type_attributes) {
        this.entity_type_attributes = entity_type_attributes;
    }

    public String getField_name_attributes() {
        return field_name_attributes;
    }

    public void setField_name_attributes(String field_name_attributes) {
        this.field_name_attributes = field_name_attributes;
    }

    public String getDefault_langcode_attributes() {
        return default_langcode_attributes;
    }

    public void setDefault_langcode_attributes(String default_langcode_attributes) {
        this.default_langcode_attributes = default_langcode_attributes;
    }

    public String getValue_comment_body_attributes() {
        return value_comment_body_attributes;
    }

    public void setValue_comment_body_attributes(String value_comment_body_attributes) {
        this.value_comment_body_attributes = value_comment_body_attributes;
    }

    public String getFormat_comment_body_attributes() {
        return format_comment_body_attributes;
    }

    public void setFormat_comment_body_attributes(String format_comment_body_attributes) {
        this.format_comment_body_attributes = format_comment_body_attributes;
    }

    public String getData_pid_relationships() {
        return data_pid_relationships;
    }

    public void setData_pid_relationships(String data_pid_relationships) {
        this.data_pid_relationships = data_pid_relationships;
    }

    public String getType_data_entity_id_relationships() {
        return type_data_entity_id_relationships;
    }

    public void setType_data_entity_id_relationships(String type_data_entity_id_relationships) {
        this.type_data_entity_id_relationships = type_data_entity_id_relationships;
    }

    public String getId_data_entity_id_relationships() {
        return id_data_entity_id_relationships;
    }

    public void setId_data_entity_id_relationships(String id_data_entity_id_relationships) {
        this.id_data_entity_id_relationships = id_data_entity_id_relationships;
    }

    public String getSelf_links_entity_id_relationships() {
        return self_links_entity_id_relationships;
    }

    public void setSelf_links_entity_id_relationships(String self_links_entity_id_relationships) {
        this.self_links_entity_id_relationships = self_links_entity_id_relationships;
    }

    public String getRelated_links_entity_id_relationships() {
        return related_links_entity_id_relationships;
    }

    public void setRelated_links_entity_id_relationships(String related_links_entity_id_relationships) {
        this.related_links_entity_id_relationships = related_links_entity_id_relationships;
    }

    public String getType_data_uid_relationships() {
        return type_data_uid_relationships;
    }

    public void setType_data_uid_relationships(String type_data_uid_relationships) {
        this.type_data_uid_relationships = type_data_uid_relationships;
    }

    public String getId_data_uid_relationships() {
        return id_data_uid_relationships;
    }

    public void setId_data_uid_relationships(String id_data_uid_relationships) {
        this.id_data_uid_relationships = id_data_uid_relationships;
    }

    public String getSelf_links_uid_relationships() {
        return self_links_uid_relationships;
    }

    public void setSelf_links_uid_relationships(String self_links_uid_relationships) {
        this.self_links_uid_relationships = self_links_uid_relationships;
    }

    public String getRelated_links_uid_relationships() {
        return related_links_uid_relationships;
    }

    public void setRelated_links_uid_relationships(String related_links_uid_relationships) {
        this.related_links_uid_relationships = related_links_uid_relationships;
    }

    public String getType_data_comment_type_relationships() {
        return type_data_comment_type_relationships;
    }

    public void setType_data_comment_type_relationships(String type_data_comment_type_relationships) {
        this.type_data_comment_type_relationships = type_data_comment_type_relationships;
    }

    public String getId_data_comment_type_relationships() {
        return id_data_comment_type_relationships;
    }

    public void setId_data_comment_type_relationships(String id_data_comment_type_relationships) {
        this.id_data_comment_type_relationships = id_data_comment_type_relationships;
    }

    public String getSelf_links_comment_type_relationships() {
        return self_links_comment_type_relationships;
    }

    public void setSelf_links_comment_type_relationships(String self_links_comment_type_relationships) {
        this.self_links_comment_type_relationships = self_links_comment_type_relationships;
    }

    public String getRelated_links_comment_type_relationships() {
        return related_links_comment_type_relationships;
    }

    public void setRelated_links_comment_type_relationships(String related_links_comment_type_relationships) {
        this.related_links_comment_type_relationships = related_links_comment_type_relationships;
    }

    public String getSelf_links_data() {
        return self_links_data;
    }

    public void setSelf_links_data(String self_links_data) {
        this.self_links_data = self_links_data;
    }

    public String getSelf_links() {
        return self_links;
    }

    public void setSelf_links(String self_links) {
        this.self_links = self_links;
    }
}
