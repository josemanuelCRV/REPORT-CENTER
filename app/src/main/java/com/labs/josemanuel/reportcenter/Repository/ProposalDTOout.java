package com.labs.josemanuel.reportcenter.Repository;

import org.bson.types.ObjectId;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by Miguel on 22-09-16.
 */

@JsonIgnoreProperties

public class ProposalDTOout {

    @JsonProperty(value = "_id")
    private String _id;
    @JsonProperty(value = "title")
    private String titulo;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
