package com.labs.josemanuel.reportcenter.Model;

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

}
