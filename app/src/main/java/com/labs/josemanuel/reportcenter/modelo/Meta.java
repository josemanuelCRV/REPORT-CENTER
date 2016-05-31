package com.labs.josemanuel.reportcenter.modelo;

/**
 * Reflejo de la tabla 'meta' en la base de datos
 */


public class Meta {

    private static final String TAG = Meta.class.getSimpleName();


    // Atributos
    private String idMeta;
    private String titulo;
    private String descripcion;
    private String prioridad;
    private String fechaLim;
    private String categoria;

    public Meta(String idMeta,
                String titulo,
                String descripcion,
                String fechaLim,
                String categoria,
                String prioridad) {
        this.idMeta = idMeta;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.fechaLim = fechaLim;
        this.categoria = categoria;
    }

    public String getIdMeta() {
        return idMeta;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getFechaLim() {
        return fechaLim;
    }

    public String getCategoria() {
        return categoria;
    }


    /**
     * Compara los atributos de dos metas
     *
     * @param meta Meta externa
     * @return true si son iguales, false si hay cambios
     */


    public boolean compararCon(Meta meta) {
        return this.titulo.compareTo(meta.titulo) == 0 &&
                this.descripcion.compareTo(meta.descripcion) == 0 &&
                this.fechaLim.compareTo(meta.fechaLim) == 0 &&
                this.categoria.compareTo(meta.categoria) == 0 &&
                this.prioridad.compareTo(meta.prioridad) == 0;
    }
}

