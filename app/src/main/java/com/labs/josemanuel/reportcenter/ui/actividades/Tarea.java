package com.labs.josemanuel.reportcenter.ui.actividades;

public class Tarea{

    private String nombre;
    private String valor;
    private String editValor;
    private int editBtn;


    public Tarea(String nombre, String valor, String editValor, int editBtn){
        this.nombre = nombre;
        this.valor = valor;
        this.editValor = editValor;
        this.editBtn = editBtn;

    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setValor(String valor){
        this.valor = valor;
    }

    public void setEditBtn(int editBtn){
        this.editBtn = editBtn;
    }

    public void setEditValor(String editValor){
        this.editValor = editValor;
    }


    public String getNombre(){return nombre;}
    public String getValor(){return valor;}
    public int getEditBtn(){return editBtn;}
    public String getEditValor(){return editValor;}


}