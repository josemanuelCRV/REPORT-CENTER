package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by bufigol on 14/05/16.
 */
public class Imagen {
    private int target_id;
    private String alt;
    private String title;
    private int width;
    private int height;
    private String target_type;
    private String target_uuid;
    private String url;

    public Imagen(int target_id, String alt, String title, int width, int height, String target_type, String target_uuid, String url) {
        this.target_id = target_id;
        this.alt = alt;
        this.title = title;
        this.width = width;
        this.height = height;
        this.target_type = target_type;
        this.target_uuid = target_uuid;
        this.url = url;
    }

    public int getTarget_id() {
        return target_id;
    }

    public void setTarget_id(int target_id) {
        this.target_id = target_id;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    @Override
    public String toString() {
        return "Imagen{" +
                "target_id=" + target_id +
                ", alt='" + alt + '\'' +
                ", title='" + title + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", target_type='" + target_type + '\'' +
                ", target_uuid='" + target_uuid + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
