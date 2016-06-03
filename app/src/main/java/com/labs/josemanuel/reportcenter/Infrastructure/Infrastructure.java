package com.labs.josemanuel.reportcenter.Infrastructure;


import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

/**
 * Created by Usuario on 22/05/2016.
 */
public class Infrastructure {


    public static Propuesta propuestaSeleccionada;
    public static Comentario[] comentarioSeleccionada;
    public static CommentWithUser[] comment;

    public static void setComment(CommentWithUser[] comment) {
        Infrastructure.comment = comment;
    }

    public static CommentWithUser[] getComment() {
        return comment;
    }

    public static Propuesta getPropuestaSeleccionada() {
        return propuestaSeleccionada;
    }

    public static void setPropuestaSeleccionada(Propuesta propuestaSeleccionada) {
        Infrastructure.propuestaSeleccionada = propuestaSeleccionada;
    }


    public static Comentario[] getComentarioSeleccionada() {
        return comentarioSeleccionada;
    }

    public static void setComentarioSeleccionada(Comentario[] comentarioSeleccionada) {
        Infrastructure.comentarioSeleccionada = comentarioSeleccionada;
    }






}
