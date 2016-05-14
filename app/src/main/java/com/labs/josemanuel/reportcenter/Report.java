package com.labs.josemanuel.reportcenter;

/**
 * Created by BravoBr3 on 14/05/2016.
 */
public class Report {
    private String titulo;
    private String propuesto;
    private int photo;

    public Report(){}

    public Report(String t, String p, int ph){
        titulo = t;
        propuesto = p;
        photo = ph;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPropuesto() {
        return propuesto;
    }

    public void setPropuesto(String propuesto) {
        this.propuesto = propuesto;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }



}
