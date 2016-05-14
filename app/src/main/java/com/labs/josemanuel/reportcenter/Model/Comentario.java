package com.labs.josemanuel.reportcenter.Model;

import java.util.Date;

/**
 * Created by bufigol on 14/05/16.
 */
public class Comentario {
    private int status;
    private int id;
    private Date timestamp;
    private String name;
    private Usuario uid;
    private int count;

    public Comentario(int status, int id, Date timestamp, String name, Usuario uid, int count) {
        this.status = status;
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.uid = uid;
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
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
