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
            JSONHandler.setJsonObjectNode(dummyProposal,"_links");
            JSONHandler.setValueJsonObject(dummyProposal,"_links","type",new JSONObject().put("href","http://stag.hackityapp.com/rest/type/node/proposal"));
            /*JSONHandler.setJsonObjectNode(dummyProposal,"type");
            JSONHandler.setValueJsonObject(dummyProposal,"type","target_id","proposal");
            */
//            JSONHandler.setJsonObjectNode(dummyProposal,"title");
//            JSONHandler.setValueJsonObject(dummyProposal,"title","value","Creación de propuesta desde POST desde android");
            JSONHandler.setJsonObjectNode(dummyProposal,"body");
            JSONHandler.setValueJsonObject(dummyProposal,"body","value","Cuerpo de la propuesta en post");


            /*JSONHandler.setValueJsonArray(dummyProposal,"type","target_type","node_type");
            JSONHandler.setValueJsonArray(dummyProposal,"type","target_target_uuid","2a3b4be3-5bf9-48b7-a1c6-2a67f0e2b00c");*/

            /*JSONHandler.setJsonArrayNodeByName(dummyProposal,"uid");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","target_id","0");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","target_type","user");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","target_uuid","733d3dad-d298-4643-b0d9-81431dc83a2c");
            JSONHandler.setValueJsonArray(dummyProposal,"uid","url","/en/user/0");*/
            /*JSONHandler.setJsonArrayNodeByName(dummyProposal,"body");
            JSONHandler.setValueJsonArray(dummyProposal,"body","value","Cuerpo de la propuesta en post desde Android");
            JSONHandler.setValueJsonArray(dummyProposal,"body","format","null");
            JSONHandler.setValueJsonArray(dummyProposal,"body","summary","null");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_aal1");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_aal1","value","Comunidad de Madrid");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_aal2");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_aal2","value","Madrid");*/
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_category");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_category","target_id","3");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_location");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lat","40.373212");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lng","-3.660263");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lat_sin","0.64776378153254");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lat_cos","0.76184124549322");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_location","lng_rad","-0.063883640838925");
            dummyProposal.getJSONArray("field_proposal_location").getJSONObject(0).put("data",new JSONArray());

            /*JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_category","target_type","taxonomy_term");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_category","target_uuid","239c0953-e898-4ecc-9d23-9414b06bb3a3");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_category","url","/en/taxonomy/term/3");*/
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_notify_council");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_notify_council","value","0");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_status");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_status","target_id","1");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"langcode");
            JSONHandler.setValueJsonArray(dummyProposal,"langcode","value","es");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"title");
            JSONHandler.setValueJsonArray(dummyProposal,"title","value","Creación de propuesta desde POST Android");

            JSONHandler.setJsonArrayNodeByName(dummyProposal,"type");
            JSONHandler.setValueJsonArray(dummyProposal,"type","target_id","proposal");

            /*JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_country");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_country","value","Spain");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_formatted_address");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_formatted_address","value","Calle Cabo Machichaco, 3-5, 28053 Madrid, Madrid, Spain");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_images");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                dummyProposal.getJSONArray("field_proposal_images").remove(0);
            }
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_locality");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_locality","value","Madrid");*/
            /*JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_postal_code");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_postal_code","value","28053");
            JSONHandler.setJsonArrayNodeByName(dummyProposal,"field_proposal_route_name");
            JSONHandler.setValueJsonArray(dummyProposal,"field_proposal_route_name","value","Calle Cabo Machichaco");*/



            //
            // dummyProposal= new JSONObject().put("Hola","Mundo");


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
