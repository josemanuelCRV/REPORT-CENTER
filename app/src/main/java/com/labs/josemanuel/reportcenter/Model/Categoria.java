package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by Miguel on 5/22/2016.
 */
public class Categoria {
    private String target_id;
    private String target_type;
    private String target_uuid;
    private String url;

    public Categoria(String target_id, String target_type, String target_uuid, String url) {
        this.target_id = target_id;
        this.target_type = target_type;
        this.target_uuid = target_uuid;
        this.url = url;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    public String getTarget_type() {
        return target_type;
    }

    public void setTarget_type(String target_type) {
        this.target_type = target_type;
    }

    public String getTarget_uuid() {
        return target_uuid;
    }

    public void setTarget_uuid(String target_uuid) {
        this.target_uuid = target_uuid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
