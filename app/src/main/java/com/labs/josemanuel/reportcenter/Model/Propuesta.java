package com.labs.josemanuel.reportcenter.Model;

import java.util.Arrays;

/**
 * Created by bufigol on 14/05/16.
 */
public class Propuesta {
    private String title;
    private String langcode;
    private int nid;
    private String uuid;
    private long created;
    private long changed;

    private Body body;
    private Usuario uid;
    private Comentario[] com;
    private Localizacion loc;
    private Imagen[] image;
    private Type type;

    public Propuesta(String title,
                     String langcode,
                     int nid, String uuid,
                     long created,
                     long changed,
                     Body body,
                     Usuario uid,
                     Comentario[] com,
                     Localizacion loc,
                     Imagen[] image,
                     Type type) {
        this.title = title;
        this.langcode = langcode;
        this.nid = nid;
        this.uuid = uuid;
        this.created = created;
        this.changed = changed;
        this.body = body;
        this.uid = uid;
        this.com = com;
        this.loc = loc;
        this.image = image;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getChanged() {
        return changed;
    }

    public void setChanged(long changed) {
        this.changed = changed;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Usuario getUid() {
        return uid;
    }

    public void setUid(Usuario uid) {
        this.uid = uid;
    }

    public Comentario[] getCom() {
        return com;
    }

    public void setCom(Comentario[] com) {
        this.com = com;
    }

    public Localizacion getLoc() {
        return loc;
    }

    public void setLoc(Localizacion loc) {
        this.loc = loc;
    }

    public Imagen[] getImage() {
        return image;
    }

    public void setImage(Imagen[] image) {
        this.image = image;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Propuesta{" +
                "title='" + title + '\'' +
                ", langcode='" + langcode + '\'' +
                ", nid=" + nid +
                ", uuid='" + uuid + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", body=" + body +
                ", uid=" + uid +
                ", com=" + Arrays.toString(com) +
                ", loc=" + loc +
                ", image=" + Arrays.toString(image) +
                ", type=" + type +
                '}';
    }
}
