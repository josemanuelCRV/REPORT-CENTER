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

import java.util.ArrayList;
import java.util.Date;

/**
 * Clase para el manejo de archivos JSON
 * Created by bufigol on 15/05/16.
 */
public class jsoncontroler {
    private static final String ALT  = "alt";
    private static final String BODY = "body";
    private static final String CHGD = "changed";
    private static final String CID  = "cid";
    private static final String COCU = "comment_count";
    private static final String CRTD = "created";
    private static final String FPCO = "field_proposal_comments";
    private static final String FPIM = "field_proposal_images";
    private static final String FPLC = "field_proposal_location";
    private static final String FRMT = "format";
    private static final String HIGH = "height";
    private static final String LAT  = "lat";
    private static final String LCNM = "last_comment_name";
    private static final String LCTT = "last_comment_timestamp";
    private static final String LGCD = "langcode";
    private static final String LNG  = "lng";
    private static final String NID  = "nid";
    private static final String SMRY = "summary";
    private static final String STUS = "status";
    private static final String TGTY = "target_type";
    private static final String TGID = "targer_id";
    private static final String TGUD = "target_uuid";
    private static final String TITL = "title";
    private static final String TYPE = "type";
    private static final String URL  = "url";
    private static final String UUID = "uuid";
    private static final String VALU = "value";
    private static final String WITH = "width";

    public static ArrayList<Propuesta> generateProupestaArray(JSONArray jsoninput){
        try {
            ArrayList<Propuesta> output = new ArrayList<>();
            for (int i=0;i<jsoninput.length();i++) {
                output.add(generatePropuesta(jsoninput.getJSONObject(i)));
            }
            return output;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Propuesta generatePropuesta(JSONObject jsoninput){
        String       title      = getStringFromNode(jsoninput, TITL);
        String       langcode   = getStringFromNode(jsoninput, LGCD);
        String       uuid       = getStringFromNode(jsoninput, UUID);

        int          nid        = getIntFromNode(jsoninput, NID);

        long         created    = getLongFromNode(jsoninput, CRTD);
        long         changed    = getLongFromNode(jsoninput, CHGD);

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
            JSONArray dataArray = (JSONArray) jsoninput.get(nodeName);
            return dataArray.getJSONObject(0).getString(VALU);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static int getIntFromNode(JSONObject jsoninput, String nodeName){
        try {
            JSONArray dataArray = (JSONArray) jsoninput.get(nodeName);
            return dataArray.getJSONObject(0).getInt(VALU);
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
    private static long getLongFromNode(JSONObject jsoninput, String nodeName){
        try {
            JSONArray dataArray = (JSONArray) jsoninput.get(nodeName);
            return dataArray.getJSONObject(0).getLong(VALU);
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private static double getDoubleFromNode(JSONObject data, String nodeName) {
        try {
            return  data.getDouble(nodeName);
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }

    }
    private static Boolean getBooleanFromNode(JSONObject jsoninput, String nodeName) {
        try {
            JSONArray dataArray = (JSONArray) jsoninput.get(nodeName);
            return dataArray.getJSONObject(0).getBoolean(nodeName);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Date getDateFromNode(JSONObject jsoninput, String nodeName) {
        try {
            long unix_time = jsoninput.getLong(nodeName);
            Date date = new Date ();
            date.setTime(unix_time*1000);
            return date;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Body getBodyFromNode(JSONObject jsoninput) {
        try {
            JSONArray  dataArray = (JSONArray) jsoninput.get(BODY);
            JSONObject data      = dataArray.getJSONObject(0);
            String     value     = getStringFromNode(data, VALU);
            String     format    = getStringFromNode(data, FRMT);
            Boolean    summary   = getBooleanFromNode(data, SMRY);
            return new Body(value,format,summary);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Usuario getUsuarioFromNode(JSONObject jsoninput) {
        int    targer_id   = getIntFromNode(jsoninput,TGID);
        String target_type = getStringFromNode(jsoninput, TGTY);
        String target_uuid = getStringFromNode(jsoninput, TGUD);
        String url         = getStringFromNode(jsoninput, URL);

        return new Usuario(targer_id,target_type,target_uuid,url);
    }

    private static Comentario[] getCommentsArray(JSONObject jsoninput) {
        try {
            JSONArray array = (JSONArray) jsoninput.get(FPCO);
            Comentario[] output = new Comentario[array.length()];
            for(int i = 0;i < output.length; i++){
                output[i] = getSingleComment(array.getJSONObject(i));
            }
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Comentario getSingleComment(JSONObject jsoninput) {
        int     status       = getIntFromNode(jsoninput,STUS);
        int     cid          = getIntFromNode(jsoninput,CID);
        Date    timestamp    = getDateFromNode(jsoninput,LCTT);
        String  name         = getStringFromNode(jsoninput, LCNM);
        Usuario uid          = null;
        int     count        = getIntFromNode(jsoninput,COCU);
        return  new Comentario(status,cid,timestamp,name,uid,count);
    }

    private static Localizacion getLocalizacionFromNode(JSONObject jsoninput) {
        try {
            JSONArray array = jsoninput.getJSONArray(FPLC);
            JSONObject data = array.getJSONObject(0);
            double latitude = getDoubleFromNode(data, LAT);
            double longitude = getDoubleFromNode(data,LNG);
            return new Localizacion(latitude,longitude);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Imagen[] getImagenArray(JSONObject jsoninput) {
        try {
            JSONArray array = jsoninput.getJSONArray(FPIM);
            Imagen[] ouput = new Imagen[array.length()];
            for (int i = 0;i<array.length();i++){
                JSONObject data = array.getJSONObject(i);
                ouput[i] = getSingleImagen(data);
            }
            return ouput;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Imagen getSingleImagen(JSONObject jsoninput) {
        int    target_id   = getIntFromNode(jsoninput, TGID);
        String alt         = getStringFromNode(jsoninput, ALT) ;
        String title       = getStringFromNode(jsoninput,TITL);
        int    width       = getIntFromNode(jsoninput, WITH);
        int    height      = getIntFromNode(jsoninput, HIGH);
        String target_type = getStringFromNode(jsoninput, TGTY);
        String target_uuid = getStringFromNode(jsoninput, TGUD);
        String url         = getStringFromNode(jsoninput, URL);
        return new Imagen(target_id,alt,title,width,height,target_type,target_uuid,url);
    }

    private static Type getTypeFromNode(JSONObject jsoninput) {
        try {
            JSONArray array = jsoninput.getJSONArray(TYPE);
            JSONObject data = array.getJSONObject(0);
            String target_id   = getStringFromNode(data, TGID);
            String target_type = getStringFromNode(data, TGTY);
            String target_uuid = getStringFromNode(data, TGUD);
            return new Type(target_id,target_type,target_uuid);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
