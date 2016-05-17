package com.labs.josemanuel.reportcenter.Model;

import java.util.Date;

/**
 * Created by bufigol on 14/05/16.
 */
public class Comentario {
    private String status;
    private String id;
    private String timestamp;
    private String name;
    private Usuario uid;
    private String count;

    public Comentario(String status, String id, String timestamp, String name, Usuario uid, String count) {
        this.status = status;
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.uid = uid;
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Usuario getUid() {
        return uid;
    }

    public void setUid(Usuario uid) {
        this.uid = uid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "status=" + status +
                ", id=" + id +
                ", timestamp=" + timestamp +
                ", name='" + name + '\'' +
                ", uid=" + uid +
                ", count=" + count +
                '}';
    }
}
