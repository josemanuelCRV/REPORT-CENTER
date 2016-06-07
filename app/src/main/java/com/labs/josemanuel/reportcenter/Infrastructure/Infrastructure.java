package com.labs.josemanuel.reportcenter.Infrastructure;


import android.os.Build;

import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Propuesta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Usuario on 22/05/2016.
 */
public class Infrastructure {


    public static Propuesta propuestaSeleccionada;
    public static Comentario[] comentarioSeleccionada;
    public static CommentWithUser[] comment;
    public static JSONObject dummyProposal= new JSONObject();

    public static JSONObject getDummyProposal() {
        return dummyProposal;
    }

    public static void setDummyProposal() {
        try {
            /*
            * _links es un objeto que se integra en el formato hal_json.
            * Gracias a este objeto, el sistema permite relacionar este objeto JSON con otras entidades.
            * Es una versión relacional del json (HAL => Hypertext Application Language)
            * */
            JSONHandler.setJsonObjectNode(dummyProposal,"_links");
            JSONHandler.setValueJsonObject(dummyProposal,"_links","type",new JSONObject().put("href","http://stag.hackityapp.com/rest/type/node/proposal"));

            /*
            * El cuerpo del mensaje, texto del textbox
            * */
            JSONHandler.setJsonObjectNode(dummyProposal,"body");
            JSONHandler.setValueJsonObject(dummyProposal,"body","value","Cuerpo de la propuesta en post");
            /*
            * Se especifica en el uid la referencia al usuario que envía la propuesta,
            * en este momento no tenemos forma de rescatar el uid del usuario que se logea,
            * porque no tenemos un sistema de login propiamente dicho.
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"uid");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","target_id","105");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","target_type","user");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","url","/en/user/105");
            /*
            * La categoría cambiará en función de lo que se elija en el combobox a la hora de la inserción.
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_category");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_category","target_id","3");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_location");
            /*
             *No hace especificar la dirección con una dirección,
             * al enviar la posición en lat y lng el sistema las procesa
             * y devuelve una dirección formateada.
              *  */
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lat","40.373212");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lng","-3.660263");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lat_sin","0.64776378153254");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lat_cos","0.76184124549322");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lng_rad","-0.063883640838925");
            dummyProposal.getJSONArray("field_proposal_location").getJSONObject(0).put("data",new JSONArray());

            /*
            * En la página web está marcado como un checkbox, podemos darle el mismo sentido en la aplicación
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_notify_council");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_notify_council","value","0");
            /*
            * Las propuestas por defecto están abiertas nada más ser añadidas.
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_status");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_status","target_id","1");
            /*
            * Idioma en el que se encuentra la aplicación. (MULTIIDIOMAS!)
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"langcode");
            JSONHandler.setValueJsonArray(dummyProposal,"langcode","value","es");
            /*
            * Titulo de la propuesta
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"title");
            JSONHandler.setValueJsonArray(dummyProposal,"title","value","Creación de propuesta desde POST Android");

            /*
            * Indica el tipo del recurso que se va a añadir. En este momento sólo tenemos capacidad de añadir propuestas.
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"type");
            JSONHandler.setValueJsonArray(dummyProposal,"type","target_id","proposal");
            /*
            * Añadir las imagenes a la propuesta.
            * */
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_images");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                dummyProposal.getJSONArray("field_proposal_images").remove(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

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
