package com.labs.josemanuel.reportcenter.Controler;

import android.util.Log;

import com.labs.josemanuel.reportcenter.Model.Body;
import com.labs.josemanuel.reportcenter.Model.Categoria;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Imagen;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.Status;
import com.labs.josemanuel.reportcenter.Model.Type;
import com.labs.josemanuel.reportcenter.Model.User;
import com.labs.josemanuel.reportcenter.Model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bufigol on 15/05/16.
 */
public class JSONHandler {
    static JSONArray arrayJSON;
    static JSONObject container;

    public static Propuesta[] generatePropuestaArray(JSONArray jsoninput) {
        try {
            Propuesta[] output = new Propuesta[jsoninput.length()];
            for (int i = 0; i < output.length; i++) {
                output[i] = generateCompletePropuesta(jsoninput.getJSONObject(i));
                //PropuestaHandler.parseDate(output[i].getCreated());
                //PropuestaHandler.getTimeFromToday(output[i].getCreated());
                //PropuestaHandler.getColorKey(output[i].getField_proposal_status());
            }

            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Propuesta generateCompletePropuesta(JSONObject jsoninput) {
        String nid = getStringFromNode(jsoninput, "nid");
        String uuid = getStringFromNode(jsoninput, "uuid");
        String vid = getStringFromNode(jsoninput, "vid");
        String langcode = getStringFromNode(jsoninput, "langcode");
        Type type = getTypeFromNode(jsoninput);
        String title = getStringFromNode(jsoninput, "title");
        Usuario usuario = getUsuarioFromNode(jsoninput, "uid");
        String status = getStringFromNode(jsoninput, "status");
        String created = getStringFromNode(jsoninput, "created");
        String changed = getStringFromNode(jsoninput, "changed");
        String promote = getStringFromNode(jsoninput, "promote");
        String sticky = getStringFromNode(jsoninput, "sticky");
        String revision_timestamp = getStringFromNode(jsoninput, "revision_timestamp");
        Usuario revision_uid = getUsuarioFromNode(jsoninput, "revision_uid");
        String revision_log = getStringFromNode(jsoninput, "revision_log");
        String revision_translation_affected = getStringFromNode(jsoninput, "revision_translation_affected");
        String default_langcode = getStringFromNode(jsoninput, "default_langcode");
        String path = getStringFromNode(jsoninput, "path");
        String content_translation_source = getStringFromNode(jsoninput, "content_translation_source");
        String content_translation_outdated = getStringFromNode(jsoninput, "content_translation_outdated");
        Body body = getBodyFromNode(jsoninput);
        String field_proposal_aal1 = getStringFromNode(jsoninput, "field_proposal_aal1");
        String field_proposal_aal2 = getStringFromNode(jsoninput, "field_proposal_aal2");
        Categoria categoria = getCategoriaFromNode(jsoninput, "field_proposal_category");
        Comentario[] comentario = getCommentsArray(jsoninput);
        String field_proposal_country = getStringFromNode(jsoninput, "field_proposal_country");
        String field_proposal_formatted_address = getStringFromNode(jsoninput, "field_proposal_formatted_address");
        String field_proposal_id_aviso = getStringFromNode(jsoninput, "field_proposal_id_aviso");
        Imagen[] imagen = getImagenArray(jsoninput);
        String field_proposal_locality = getStringFromNode(jsoninput, "field_proposal_locality");
        Localizacion loc = getLocalizacionFromNode(jsoninput);
        String field_proposal_notify_council = getStringFromNode(jsoninput, "field_proposal_notify_council");
        String field_proposal_postal_code = getStringFromNode(jsoninput, "field_proposal_postal_code");
        String field_proposal_route_name = getStringFromNode(jsoninput, "field_proposal_route_name");
        Status field_proposal_status = getStatusFromNode(jsoninput, "field_proposal_status");


        return new Propuesta(nid, uuid, vid, langcode, type,
                title, usuario, status, created, changed,
                promote, sticky, revision_timestamp, revision_uid, revision_log,
                revision_translation_affected, default_langcode, path, content_translation_source, content_translation_outdated, body,
                field_proposal_aal1, field_proposal_aal2, categoria, comentario, field_proposal_country, field_proposal_formatted_address,
                field_proposal_id_aviso, imagen, field_proposal_locality, loc, field_proposal_notify_council, field_proposal_postal_code,
                field_proposal_route_name, field_proposal_status
        );
    }

    public static User generateUser(JSONObject jsoninput) throws JSONException {
        JSONObject data = jsoninput.getJSONObject("data");
        JSONObject attributes = data.getJSONObject("attributes");
        JSONObject relationships = data.getJSONObject("relationships");
        String links= data.getJSONObject("links").getString("self");

        String id = data.getString("id");
        String type = data.getString("type");
        String uid = attributes.getString("uid");
        String uuid = attributes.getString("uuid");
        String langcode = attributes.getString("langcode");
        String name = attributes.getString("name");
        String created = attributes.getString("created");
        String changed = attributes.getString("changed");
        String path = attributes.getString("path");
        String avatars_avatar_generator = attributes.getString("avatars_avatar_generator");

        String avatars_user_picture = relationships.getJSONObject("avatars_user_picture").getString("data");
        String user_picture= relationships.getJSONObject("user_picture").getString("data");
        /*if(!relationships.getJSONObject("links").isNull("self")) {
            String links_ = relationships.getJSONObject("links").getString("self");
        }*/
        String links_= "links 1 2 3";


        return new User(links,id,type,uid,uuid,langcode,name,created,changed,path,avatars_avatar_generator,avatars_user_picture,user_picture,links_);

    }




    public static String getStringFromNode(JSONObject jsoninput, String nodeName) {

        try {
            arrayJSON = jsoninput.getJSONArray(nodeName);
            if (arrayJSON.length() == 0)
                return null;
            container = arrayJSON.getJSONObject(0);
            //Cojo la clave del objeto JSON contenedor
            JSONArray claves = container.names();

            return container.getString(claves.getString(0));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }



    private static Body getBodyFromNode(JSONObject jsoninput) {

        try {
            arrayJSON = jsoninput.getJSONArray(JsonConstants.BODY);
            if (arrayJSON.length() == 0)
                return null;
            container = arrayJSON.getJSONObject(0);

            String value = container.getString(JsonConstants.VALU);
            //Limpia etiqueta <p> del body
            value = value.charAt(0) == '<' ? value.substring(3, value.length() - 6) : value;
            String format = container.getString(JsonConstants.FRMT);
            String summary;
            //Comprobación body de propuesta, si lo es devuelve el constructor con los 3 parámetros.
            if (container.length() == 3) {
                summary = container.getString(JsonConstants.SMRY);
                return new Body(value, format, summary);
            }
            //Si no lo es, devuelve el constructor de un body de comentario
            return new Body(value, format);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }

    private static Usuario getUsuarioFromNode(JSONObject jsoninput, String nodeName) {
        try {
            arrayJSON = jsoninput.getJSONArray(nodeName);
            if (arrayJSON.length() == 0)
                return null;
            container = arrayJSON.getJSONObject(0);
            String target_id = container.getString(JsonConstants.TGID);
            String target_type = container.getString(JsonConstants.TGTY);
            String target_uuid = container.getString(JsonConstants.TGUD);
            String url = container.getString(JsonConstants.URL);
            return new Usuario(target_id, target_type, target_uuid, url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Categoria getCategoriaFromNode(JSONObject jsoninput, String nodeName) {
        try {
            arrayJSON = jsoninput.getJSONArray(nodeName);
            if (arrayJSON.length() == 0)
                return null;
            container = arrayJSON.getJSONObject(0);
            String target_id = container.getString(JsonConstants.TGID);
            String target_type = container.getString(JsonConstants.TGTY);
            String target_uuid = container.getString(JsonConstants.TGUD);
            String url = container.getString(JsonConstants.URL);
            return new Categoria(target_id, target_type, target_uuid, url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Status getStatusFromNode(JSONObject jsoninput, String nodeName) {
        try {
            arrayJSON = jsoninput.getJSONArray(nodeName);
            if (arrayJSON.length() == 0)
                return null;
            container = arrayJSON.getJSONObject(0);
            String target_id = container.getString(JsonConstants.TGID);
            String target_type = container.getString(JsonConstants.TGTY);
            String target_uuid = container.getString(JsonConstants.TGUD);
            String url = container.getString(JsonConstants.URL);
            return new Status(target_id, target_type, target_uuid, url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Comentario[] getCommentsArray(JSONObject jsoninput) {
        Comentario[] output;
        try {
            arrayJSON = (JSONArray) jsoninput.get("field_proposal_comments");
            output = new Comentario[arrayJSON.length()];
            for (int i = 0; i < output.length; i++) {
                output[i] = getSingleComment(arrayJSON.getJSONObject(i));
            }
            return output;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    //getStringFromNode
    private static Comentario getSingleComment(JSONObject jsonObject) {
        String status = null;
        String id = null;
        String timestamp = null;
        String name = null;
        Usuario uid = null;
        String count = null;
        try {
            status = jsonObject.getString("status");
            id = jsonObject.getString("cid");
            timestamp = jsonObject.getString("last_comment_timestamp");
            name = jsonObject.getString("last_comment_name");
            uid = null;
            count = jsonObject.getString("comment_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Comentario(status, id, timestamp, name, uid, count);
    }

    private static Type getTypeFromNode(JSONObject jsoninput) {

        try {
            JSONArray jsonInput1 = (JSONArray) jsoninput.get("type");
            jsoninput = jsonInput1.getJSONObject(0);
            String target_id = jsoninput.getString(JsonConstants.TGID);
            String target_type = jsoninput.getString(JsonConstants.TGTY);
            String target_uuid = jsoninput.getString(JsonConstants.TGUD);
            return new Type(target_id, target_type, target_uuid);
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
            arrayImagenesJson = jsonObject.getJSONArray("field_proposal_images");
            if (arrayImagenesJson.length() != 0) {
                arrayImagenes = new Imagen[arrayImagenesJson.length()];

                for (int index = 0; index < arrayImagenesJson.length(); index++) {
                    imagen = arrayImagenesJson.getJSONObject(index);
                    arrayImagenes[index] = getImagenFromNode(imagen);
                }
                return arrayImagenes;
            }
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Imagen getImagenFromNode(JSONObject jsonObject) {
        try {
            String target_id = jsonObject.getString(JsonConstants.TGID);
            String alt = jsonObject.getString(JsonConstants.ALT);
            String title = jsonObject.getString(JsonConstants.TITL);
            String width = jsonObject.getString(JsonConstants.WITH);
            String height = jsonObject.getString(JsonConstants.HIGH);
            String target_type = jsonObject.getString(JsonConstants.TGTY);
            String target_uuid = jsonObject.getString(JsonConstants.TGUD);
            String url = jsonObject.getString(JsonConstants.URL);
            //public Imagen(int target_id, String alt, String title, int width, int height, String target_type, String target_uuid, String url)
            return new Imagen(target_id, alt, title, width, height, target_type, target_uuid, url);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Localizacion getLocalizacionFromNode(JSONObject jsonObject) {
        JSONArray arrayLocalizacionesJson;
        JSONObject localizacionJsonObject;
        try {
            arrayLocalizacionesJson = jsonObject.getJSONArray("field_proposal_location");
            /**
             * 26/05/2016
             * Comprobación nodo vacío en Localización
             * Si no se ha registrado la localización de la propuesta, muestra lat= 0 lg=0
             * */
            if (arrayLocalizacionesJson.length() == 0)
                return new Localizacion("0", "0");

            localizacionJsonObject = arrayLocalizacionesJson.getJSONObject(0);
            String latitude = localizacionJsonObject.getString(JsonConstants.LAT);
            String longitude = localizacionJsonObject.getString(JsonConstants.LNG);
            return new Localizacion(latitude, longitude);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Comment[] generateCommentsArray(Comentario[] comentarios) {

        Comment[] arrayComments = new Comment[comentarios.length];





        return arrayComments;
    }
    public static Comment generateComment(JSONObject jsoninput) throws JSONException {
        JSONObject data = jsoninput.getJSONObject("data");
        String id = data.getString("id");
        String type = data.getString("type");
        JSONObject attributes = data.getJSONObject("attributes");
        String cid = attributes.getString("cid");
        String uuid = attributes.getString("uuid");
        String langcode = attributes.getString("langcode");
        String name = attributes.getString("name");
        String homepage = attributes.getString("homepage");
        String created = attributes.getString("created");
        String changed = attributes.getString("changed");
        String status = attributes.getString("status");
        String thread = attributes.getString("thread");
        String entity_type = attributes.getString("entity_type");
        String field_name = attributes.getString("field_name");
        String default_langcode = attributes.getString("default_langcode");
        JSONObject comment_body = attributes.getJSONObject("comment_body");
        String value = comment_body.getString("value");
        String format = comment_body.getString("format");
        JSONObject relationships = data.getJSONObject("relationships");
        JSONObject pid = relationships.getJSONObject("pid");
        String data_pid=  pid.getString("data");
        JSONObject entity_id = relationships.getJSONObject("entity_id");
        JSONObject data_entity_id = entity_id.getJSONObject("data");
        String type_data_entity_id=  data_entity_id.getString("type");
        String id_data_entity_id=  data_entity_id.getString("id");
        JSONObject links_entity_id=  entity_id.getJSONObject("links");
        String self_links_entity_id=  links_entity_id.getString("self");
        String related_links_entity_id=  links_entity_id.getString("related");
        JSONObject uid = relationships.getJSONObject("uid");
        JSONObject data_uid = uid.getJSONObject("data");
        String type_data_uid = data_uid.getString("type");
        String id_data_uid = data_uid.getString("id");
        JSONObject links_uid = uid.getJSONObject("links");
        String self_links_uid = links_uid.getString("self");
        String related_links_uid = links_uid.getString("related");
        JSONObject comment_type = relationships.getJSONObject("comment_type");
        JSONObject data_comment_type=  comment_type.getJSONObject("data");
        String type_data_comment_type=  data_comment_type.getString("type");
        String id_data_comment_type=  data_comment_type.getString("id");
        JSONObject links_comment_type=  comment_type.getJSONObject("links");
        String self_links_comment_type=  links_comment_type.getString("self");
        String related_links_comment_type=  links_comment_type.getString("related");
        JSONObject links_data = data.getJSONObject("links");
        String self_links_data=  links_data.getString("self");
        JSONObject links = jsoninput.getJSONObject("links");
        String self_links=links.getString("self");


        return Comment.commentFactory(cid,id,created,changed,value,format);
    }

    public static CommentWithUser generateCommentWithUser(JSONObject jsoninput) throws JSONException {
        JSONObject data = jsoninput.getJSONObject("data");
        JSONObject attributes = data.getJSONObject("attributes");
        String cid = attributes.getString("cid");
        String created = attributes.getString("created");
        String changed = attributes.getString("changed");
        JSONObject comment_body = attributes.getJSONObject("comment_body");
        String value = comment_body.getString("value");
        String format = comment_body.getString("format");

        JSONObject relationships = data.getJSONObject("relationships");
        JSONObject uid = relationships.getJSONObject("uid");
        JSONObject data_uid = uid.getJSONObject("data");
        String id_data_uid = data_uid.getString("id");


        //null,null son los valores provenientes del usuario, name y pic
        return CommentWithUser.commentWithUserFactory(cid,id_data_uid,created,changed,value,format,null,null);
    }

    public static void setJsonArrayNodeByName(JSONObject input,String nodeKey) throws JSONException {
        input.put(nodeKey,new JSONArray().put(new JSONObject()));
    }

    public static void setJsonObjectNode(JSONObject input,String nodeKey) throws JSONException {
        input.put(nodeKey,new JSONObject());
    }
    public static void setValueJsonObject(JSONObject input,String jsonObjectName,String key,String value){
        try {
            input.getJSONObject(jsonObjectName).put(key,value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void setValueJsonObject(JSONObject input,String jsonObjectName,String key,JSONObject value){
        try {
            input.getJSONObject(jsonObjectName).put(key,value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void setValueJsonArray(JSONObject input,String jsonArrayName,String key,String value){
        try {
            JSONArray jsonArrayToBeSet=input.getJSONArray(jsonArrayName);
            JSONObject jsonObjectWithinJSONArray= jsonArrayToBeSet.getJSONObject(0);
            jsonObjectWithinJSONArray.put(key,value);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
