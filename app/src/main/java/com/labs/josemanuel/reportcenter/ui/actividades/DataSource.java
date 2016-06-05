package com.labs.josemanuel.reportcenter.ui.actividades;

import com.labs.josemanuel.reportcenter.R;

import java.util.ArrayList;
import java.util.List;


public class DataSource {

    static List<Tarea> TAREAS=new ArrayList<Tarea>();

static{

    TAREAS.add(new Tarea("Tu nombre de usuario","Manu","", R.mipmap.ic_edit));
    TAREAS.add(new Tarea("E-Mail","a@a.com","",R.mipmap.ic_edit));
    TAREAS.add(new Tarea("Nueva contraseña","contraseña","",R.mipmap.ic_edit));
    TAREAS.add(new Tarea("Tu foto","","",R.mipmap.ic_edit));
    TAREAS.add(new Tarea("Estoy en","","",R.mipmap.ic_edit));
    TAREAS.add(new Tarea("Borrar cuenta","Sus datos se borrarán para siempre","",R.mipmap.ic_close));

    }

}
