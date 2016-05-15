package com.labs.josemanuel.reportcenter.Controler;

import com.labs.josemanuel.reportcenter.Model.Body;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Imagen;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.Type;
import com.labs.josemanuel.reportcenter.Model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by bufigol on 15/05/16.
 */
public class jsoncontroler {
    /*
            try {
            Comentario[] output;
            JSONArray array = (JSONArray) jsoninput.get("field_proposal_comments");
            output = new Comentario[array.length()];
            for(int i = 0;i < output.length; i++){
                output[i] = getSingleComment(array.getJSONObject(i));
            }
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
     */
    public static Propuesta[] generateProupestaArray(JSONArray jsoninput){
        try {
        Propuesta[] output = new Propuesta[jsoninput.length()];
        for (int i=0;i<output.length;i++) {
            output[i] = generatePropuesta(jsoninput.getJSONObject(i));
        }
            return output;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Propuesta generatePropuesta(JSONObject jsoninput){
        String       title      = getStringFromNode(jsoninput, "title");
        String       langcode   = getStringFromNode(jsoninput, "langcode");
        int          nid        = getIntFromNode(jsoninput, "nid");
        String       uuid       = getStringFromNode(jsoninput, "uuid");
        long         created    = getLongFromNode(jsoninput, "created");
        long         changed    = getLongFromNode(jsoninput, "created");
        Body         body       = getBodyFromNode(jsoninput);
        Usuario      usuario    = getUsuarioFromNode(jsoninput);
        Comentario[] comentario = getCommentsArray(jsoninput);
        Localizacion loc        = getLocalizacionFromNode(jsoninput);
        Imagen[]     imagen     = getImagenArray(jsoninput);
        Type         type       = getTypeFromNode(jsoninput);

        return new Propuesta(title,langcode,nid,uuid,created,changed,body,usuario,comentario,loc,imagen,type);
    }


    private static String getStringFromNode(JSONObject jsoninput, String nodeName){
        try {
            JSONArray arrayOuput = (JSONArray) jsoninput.get(nodeName);
            return arrayOuput.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Integer getIntFromNode(JSONObject jsoninput, String nodeName){
        try {
            JSONArray arrayOuput = (JSONArray) jsoninput.get(nodeName);
            return arrayOuput.getInt(0);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Long getLongFromNode(JSONObject jsoninput, String nodeName){
        try {
            JSONArray arrayOuput = (JSONArray) jsoninput.get(nodeName);
            return arrayOuput.getLong(0);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Body getBodyFromNode(JSONObject jsoninput) {
        String  value   = getStringFromNode(jsoninput,"value");
        String  format  = getStringFromNode(jsoninput, "format");
        Boolean summary = getBooleanFromNode(jsoninput, "summary");
        return  new Body(value,format,summary);
    }

    private static Boolean getBooleanFromNode(JSONObject jsoninput, String nodeName) {
        try {
            JSONArray arrayOuput = (JSONArray) jsoninput.get(nodeName);
            return arrayOuput.getBoolean(0);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Usuario getUsuarioFromNode(JSONObject jsoninput) {
        int    targer_id   = getIntFromNode(jsoninput,"targer_id ");
        String target_type = getStringFromNode(jsoninput, "target_type");
        String target_uuid = getStringFromNode(jsoninput,"target_uuid");
        String url         = getStringFromNode(jsoninput,"url");

        return new Usuario(targer_id,target_type,target_uuid,url);
    }
    private static Comentario[] getCommentsArray(JSONObject jsoninput) {
        try {
            Comentario[] output;
            JSONArray array = (JSONArray) jsoninput.get("field_proposal_comments");
            output = new Comentario[array.length()];
            for(int i = 0;i < output.length; i++){
                output[i] = getSingleComment(array.getJSONObject(i));
            }
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Comentario getSingleComment(JSONObject jsonObject) {
        int     status       = getIntFromNode(jsonObject,"status");
        int     id           = getIntFromNode(jsonObject, "cid");
        Date    timestamp    = getDateFromNode(jsonObject);
        String  name         = getStringFromNode(jsonObject, "last_comment_name");
        Usuario uid          = null;
        int     count         = getIntFromNode(jsonObject,"comment_count");
        return  new Comentario(status,id,timestamp,name,uid,count);
 /*
     public Comentario(int status, int id, Date timestamp, String name, Usuario uid, int count) {
        this.status = status;
        this.id = id;
        this.timestamp = timestamp;
        this.name = name;
        this.uid = uid;
        this.count = count;
    }
*/
    }

    private static Date getDateFromNode(JSONObject object) {
        try {
            long unix_time = object.getLong("last_comment_timestamp");
            Date date = new Date ();
            date.setTime(unix_time*1000);
            return date;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Type getTypeFromNode(JSONObject jsoninput) {
        return null;

    }

    private static Imagen[] getImagenArray(JSONObject jsoninput) {
        return null;
    }

    private static Localizacion getLocalizacionFromNode(JSONObject jsoninput) {
        return null;
    }
}
