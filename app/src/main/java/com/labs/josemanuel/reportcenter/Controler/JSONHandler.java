package com.labs.josemanuel.reportcenter.Controler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.labs.josemanuel.reportcenter.Model.Body;
import com.labs.josemanuel.reportcenter.Model.Categoria;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Imagen;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.Status;
import com.labs.josemanuel.reportcenter.Model.Type;
import com.labs.josemanuel.reportcenter.Model.Usuario;
import com.labs.josemanuel.reportcenter.Utils.JsonConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bufigol on 15/05/16.
 */
public class JSONHandler {

    public static Propuesta[] generatePropuestaArray(JSONArray jsoninput){
        try {
        Propuesta[] output = new Propuesta[jsoninput.length()];
        for (int i=0;i<output.length;i++) {
            output[i] = generateCompletePropuesta(jsoninput.getJSONObject(i));
            PropuestaHandler.parseDate(output[i].getCreated());
            PropuestaHandler.getTimeFromToday(output[i].getCreated());
            PropuestaHandler.getColorKey(output[i].getField_proposal_status());
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
        Body         body       = getBodyFromNode(jsoninput);
        Usuario      usuario    = getUsuarioFromNode(jsoninput,"uid");
        Comentario[] comentario = getCommentsArray(jsoninput);
        Localizacion loc        = getLocalizacionFromNode(jsoninput);
        Imagen[]     imagen     = getImagenArray(jsoninput);
        Type         type       = getTypeFromNode(jsoninput);

        return new Propuesta(title,langcode,nid,uuid,created,changed,body,usuario,comentario,loc,imagen,type);
    }

    public static Propuesta generateCompletePropuesta(JSONObject jsoninput){
        String nid          = getStringFromNode(jsoninput, "nid");
        String uuid         = getStringFromNode(jsoninput, "uuid");
        String vid          = getStringFromNode(jsoninput,"vid");
        String langcode     = getStringFromNode(jsoninput, "langcode");
        Type type           = getTypeFromNode(jsoninput);
        String title        = getStringFromNode(jsoninput, "title");
        Usuario usuario     = getUsuarioFromNode(jsoninput, "uid");
        String status       = getStringFromNode(jsoninput,"status");
        String created      = getStringFromNode(jsoninput, "created");
        String changed      = getStringFromNode(jsoninput, "changed");
        String promote      = getStringFromNode(jsoninput,"promote");
        String sticky       = getStringFromNode(jsoninput,"sticky");
        String revision_timestamp = getStringFromNode(jsoninput,"revision_timestamp");
        Usuario revision_uid = getUsuarioFromNode(jsoninput,"revision_uid");
        String revision_log = getStringFromNode(jsoninput,"revision_log");
        String revision_translation_affected = getStringFromNode(jsoninput,"revision_translation_affected");
        String default_langcode = getStringFromNode(jsoninput,"default_langcode");
        String path = getStringFromNode(jsoninput,"path");
        String content_translation_source = getStringFromNode(jsoninput,"content_translation_source");
        String content_translation_outdated = getStringFromNode(jsoninput,"content_translation_outdated");
        Body body           = getBodyFromNode(jsoninput);
        String field_proposal_aal1 = getStringFromNode(jsoninput,"field_proposal_aal1");
        String field_proposal_aal2 = getStringFromNode(jsoninput,"field_proposal_aal2");
        Categoria categoria = getCategoriaFromNode(jsoninput,"field_proposal_category");
        Comentario[] comentario = getCommentsArray(jsoninput);
        String field_proposal_country = getStringFromNode(jsoninput,"field_proposal_country");
        String field_proposal_formatted_address = getStringFromNode(jsoninput,"field_proposal_formatted_address");
        String field_proposal_id_aviso = getStringFromNode(jsoninput,"field_proposal_id_aviso");
        Imagen[]     imagen     = getImagenArray(jsoninput);
        String field_proposal_locality = getStringFromNode(jsoninput,"field_proposal_locality");
        Localizacion loc        = getLocalizacionFromNode(jsoninput);
        String field_proposal_notify_council = getStringFromNode(jsoninput,"field_proposal_notify_council");
        String field_proposal_postal_code = getStringFromNode(jsoninput,"field_proposal_postal_code");
        String field_proposal_route_name = getStringFromNode(jsoninput,"field_proposal_route_name");
        Status field_proposal_status = getStatusFromNode(jsoninput,"field_proposal_status");



        return new Propuesta(nid,uuid,vid,langcode,type,
                title,usuario,status,created,changed,
                promote,sticky,revision_timestamp,revision_uid,revision_log,
                revision_translation_affected,default_langcode,path,content_translation_source,content_translation_outdated,body,
                field_proposal_aal1,field_proposal_aal2,categoria,comentario,field_proposal_country,field_proposal_formatted_address,
                field_proposal_id_aviso,imagen,field_proposal_locality,loc,field_proposal_notify_council,field_proposal_postal_code,
                field_proposal_route_name,field_proposal_status
        );
    }

    public static String generateJsonStringFromPropuesta(Propuesta propuesta){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(propuesta);
    }

    private static String getStringFromNode(JSONObject jsoninput, String nodeName){

        try {
            JSONArray arrayOutput = jsoninput.getJSONArray(nodeName);
            if(arrayOutput.length()==0)
                return null;
            JSONObject container = arrayOutput.getJSONObject(0);
            //Cojo la clave del objeto JSON contenedor
            JSONArray claves = container.names();

            return container.getString(claves.getString(0));
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
            //Limpia etiqueta <p> del body
            value= value.charAt(0)=='<'? value.substring(3,value.length()-6):value;

            String  format  = jsoninput.getString(JsonConstants.FRMT);
            String summary = jsoninput.getString(JsonConstants.SMRY);
            return  new Body(value,format,summary);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    private static Usuario getUsuarioFromNode(JSONObject jsoninput, String nodeName) {
        JSONArray jsonInput1;
        try {
            jsonInput1= jsoninput.getJSONArray(nodeName);
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
    private static Categoria getCategoriaFromNode(JSONObject jsoninput, String nodeName) {
        JSONArray jsonInput1;
        try {
            jsonInput1= jsoninput.getJSONArray(nodeName);
            jsoninput=jsonInput1.getJSONObject(0);
            String  target_id   = jsoninput.getString(JsonConstants.TGID);
            String  target_type  = jsoninput.getString(JsonConstants.TGTY);
            String target_uuid = jsoninput.getString(JsonConstants.TGUD);
            String url = jsoninput.getString(JsonConstants.URL);
            return new Categoria(target_id,target_type,target_uuid,url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Status getStatusFromNode(JSONObject jsoninput, String nodeName) {
        JSONArray jsonInput1;
        try {
            jsonInput1= jsoninput.getJSONArray(nodeName);
            if(jsonInput1.length()==0)
                return null;
            jsoninput=jsonInput1.getJSONObject(0);
            String  target_id   = jsoninput.getString(JsonConstants.TGID);
            String  target_type  = jsoninput.getString(JsonConstants.TGTY);
            String target_uuid = jsoninput.getString(JsonConstants.TGUD);
            String url = jsoninput.getString(JsonConstants.URL);
            return new Status(target_id,target_type,target_uuid,url);
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
            return new Localizacion(latitude,longitude);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
