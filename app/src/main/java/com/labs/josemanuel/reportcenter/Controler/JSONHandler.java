package com.labs.josemanuel.reportcenter.Controler;

import com.labs.josemanuel.reportcenter.Model.Body;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Imagen;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.Type;
import com.labs.josemanuel.reportcenter.Model.Usuario;
import com.labs.josemanuel.reportcenter.Utils.JsonConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Iterator;

/**
 * Created by bufigol on 15/05/16.
 */
public class JSONHandler {
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
    public static Propuesta[] generatePropuestaArray(JSONArray jsoninput){
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
        String          nid        = getStringFromNode(jsoninput, "nid");
        String       uuid       = getStringFromNode(jsoninput, "uuid");
        String         created    = getStringFromNode(jsoninput, "created");
        String         changed    = getStringFromNode(jsoninput, "changed");
        /*JSONObject bodyAux=null;
        try {
            bodyAux= jsoninput.getJSONObject("body");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Body         body       = getBodyFromNode(bodyAux);*/
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
            JSONArray arrayOutput = jsoninput.getJSONArray(nodeName);
            JSONObject container = arrayOutput.getJSONObject(0);
            //Cojo la clave del objeto JSON contenedor
            JSONArray claves = container.names();

            //return arrayOuput.getString(0);
            return container.getString(claves.getString(0));
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
        JSONArray jsonInput1;
        try {
            jsonInput1= jsoninput.getJSONArray(JsonConstants.BODY);
            jsoninput=jsonInput1.getJSONObject(0);
            String  value   = jsoninput.getString(JsonConstants.VALU);
            String  format  = jsoninput.getString(JsonConstants.FRMT);
            String summary = jsoninput.getString(JsonConstants.SMRY);
            return  new Body(value,format,summary);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


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
        JSONArray jsonInput1;
        try {
            jsonInput1= jsoninput.getJSONArray("uid");
            jsoninput=jsonInput1.getJSONObject(0);
            String  target_id   = jsoninput.getString(JsonConstants.TGID);
            String  target_type  = jsoninput.getString(JsonConstants.TGTY);
            String target_uuid = jsoninput.getString(JsonConstants.TGUD);
            String url = jsoninput.getString(JsonConstants.URL);
            return new Usuario(target_id,target_type,target_uuid,url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


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
    //getStringFromNode
    private static Comentario getSingleComment(JSONObject jsonObject) {
        /*String     status       = getStringFromNode(jsonObject,"status");
        String     id           = getStringFromNode(jsonObject, "cid");
        Date    timestamp    = getDateFromNode(jsonObject);
        String  name         = getStringFromNode(jsonObject, "last_comment_name");
        Usuario uid          = null;
        String     count         = getStringFromNode(jsonObject,"comment_count");*/

        String     status       = null;
        String     id = null;
        String    timestamp=null;
        String  name=null;
        Usuario uid=null;
        String     count=null;
        try {
            status = jsonObject.getString("status");
            id = jsonObject.getString("cid");
            //timestamp= new Date(jsonObject.getString("last_comment_timestamp"));
            timestamp=jsonObject.getString("last_comment_timestamp");
            name= jsonObject.getString("last_comment_name");
            uid= null;
            count= jsonObject.getString("comment_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        try {
            JSONArray jsonInput1= (JSONArray)jsoninput.get("type");
            jsoninput=jsonInput1.getJSONObject(0);
            String  target_id   = jsoninput.getString(JsonConstants.TGID);
            String  target_type  = jsoninput.getString(JsonConstants.TGTY);
            String target_uuid = jsoninput.getString(JsonConstants.TGUD);
            return new Type(target_id,target_type,target_uuid);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static Imagen[] getImagenArray(JSONObject jsonObject) {
        JSONArray arrayImagenesJson;
        JSONObject imagen;
        Imagen[] arrayImagenes;
        try {
            arrayImagenesJson=jsonObject.getJSONArray("field_proposal_images");
            arrayImagenes= new Imagen[arrayImagenesJson.length()];
            for(int index=0;index<arrayImagenesJson.length();index++){
                imagen=arrayImagenesJson.getJSONObject(index);
                arrayImagenes[index]=getImagenFromNode(imagen);
            }
            return arrayImagenes;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static Imagen getImagenFromNode(JSONObject jsonObject){
        try {
            String  target_id   = jsonObject.getString(JsonConstants.TGID);
            String  alt  = jsonObject.getString(JsonConstants.ALT);
            String title = jsonObject.getString(JsonConstants.TITL);
            String width = jsonObject.getString(JsonConstants.WITH);
            String height = jsonObject.getString(JsonConstants.HIGH);
            String target_type = jsonObject.getString(JsonConstants.TGTY);
            String target_uuid = jsonObject.getString(JsonConstants.TGUD);
            String url = jsonObject.getString(JsonConstants.URL);
            //public Imagen(int target_id, String alt, String title, int width, int height, String target_type, String target_uuid, String url)
            return new Imagen(target_id,alt,title,width,height,target_type,target_uuid,url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Localizacion getLocalizacionFromNode(JSONObject jsonObject) {
        JSONArray arrayLocalizacionesJson;
        JSONObject localizacionJsonObject;
        try {
            arrayLocalizacionesJson=jsonObject.getJSONArray("field_proposal_location");
            localizacionJsonObject= arrayLocalizacionesJson.getJSONObject(0);
            String latitude = localizacionJsonObject.getString(JsonConstants.LAT);
            String longitude = localizacionJsonObject.getString(JsonConstants.LNG);
            //public Imagen(int target_id, String alt, String title, int width, int height, String target_type, String target_uuid, String url)
            return new Localizacion(latitude,longitude);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
