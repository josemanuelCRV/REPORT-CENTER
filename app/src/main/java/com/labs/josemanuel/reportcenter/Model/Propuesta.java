package com.labs.josemanuel.reportcenter.Model;

import java.util.Arrays;

/**
 * Created by bufigol on 14/05/16.
 */
public class Propuesta {


    private String nid;
    private String uuid;
    private String vid;
    private String langcode;
    private Type type;
    private String title;
    private Usuario uid;
    private String status;
    private String created;
    private String changed;
    private String promote;
    private String sticky;
    private String revision_timestamp;
    private Usuario revision_uid;
    private String revision_log;
    private String revision_translation_affected;
    private String default_langcode;
    private String path;
    private String content_translation_source;
    private String content_translation_outdated;
    private Body body;
    private String field_proposal_aal1;
    private String field_proposal_aal2;
    private Categoria field_proposal_category;
    private Comentario[] com;
    private String field_proposal_country;
    private String field_proposal_formatted_address;
    private String field_proposal_id_aviso;
    private Imagen[] image;
    private String field_proposal_locality;
    private Localizacion loc;
    private String field_proposal_notify_council;
    private String field_proposal_postal_code;
    private String field_proposal_route_name;
    private Status field_proposal_status;
    private String url;

    public Propuesta(String title,
                     String langcode,
                     String nid, String uuid,
                     String created,
                     String changed,
                     Body body,
                     Usuario uid,
                     Comentario[] com,
                     Localizacion loc,
                     Imagen[] image,
                     Type type) {
        this.title = title;
        this.langcode = langcode;
        this.nid = nid;
        this.uuid = uuid;
        this.created = created;
        this.changed = changed;
        this.body = body;
        this.uid = uid;
        this.com = com;
        this.loc = loc;
        this.image = image;
        this.type = type;
    }

    public Propuesta(){}
    public Propuesta(String nid, String uuid, String vid, String langcode, Type type, String title, Usuario uid, String status, String created, String changed, String promote, String sticky, String revision_timestamp, Usuario revision_uid, String revision_log, String revision_translation_affected, String default_langcode, String path, String content_translation_source, String content_translation_outdated, Body body, String field_proposal_aal1, String field_proposal_aal2, Categoria field_proposal_category, Comentario[] com, String field_proposal_country, String field_proposal_formatted_address, String field_proposal_id_aviso, Imagen[] image, String field_proposal_locality, Localizacion loc, String field_proposal_notify_council, String field_proposal_postal_code, String field_proposal_route_name, Status field_proposal_status) {
        this.nid = nid;
        this.uuid = uuid;
        this.vid = vid;
        this.langcode = langcode;
        this.type = type;
        this.title = title;
        this.uid = uid;
        this.status = status;
        this.created = created;
        this.changed = changed;
        this.promote = promote;
        this.sticky = sticky;
        this.revision_timestamp = revision_timestamp;
        this.revision_uid = revision_uid;
        this.revision_log = revision_log;
        this.revision_translation_affected = revision_translation_affected;
        this.default_langcode = default_langcode;
        this.path = path;
        this.content_translation_source = content_translation_source;
        this.content_translation_outdated = content_translation_outdated;
        this.body = body;
        this.field_proposal_aal1 = field_proposal_aal1;
        this.field_proposal_aal2 = field_proposal_aal2;
        this.field_proposal_category=field_proposal_category;
        this.com = com;
        this.field_proposal_country = field_proposal_country;
        this.field_proposal_formatted_address = field_proposal_formatted_address;
        this.field_proposal_id_aviso = field_proposal_id_aviso;
        this.image = image;
        this.field_proposal_locality = field_proposal_locality;
        this.loc = loc;
        this.field_proposal_notify_council = field_proposal_notify_council;
        this.field_proposal_postal_code = field_proposal_postal_code;
        this.field_proposal_route_name = field_proposal_route_name;
        this.field_proposal_status = field_proposal_status;
    }

    //--------------
    public Propuesta(String target_id,String target_type, String target_uuid, String url) {
        this.nid = target_id;
        this.type.setTarget_type(target_type);
        this.uuid = target_uuid;
        this.url=url;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Usuario getUid() {
        return uid;
    }

    public void setUid(Usuario uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getPromote() {
        return promote;
    }

    public void setPromote(String promote) {
        this.promote = promote;
    }

    public String getSticky() {
        return sticky;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public String getRevision_timestamp() {
        return revision_timestamp;
    }

    public void setRevision_timestamp(String revision_timestamp) {
        this.revision_timestamp = revision_timestamp;
    }

    public Usuario getRevision_uid() {
        return revision_uid;
    }

    public void setRevision_uid(Usuario revision_uid) {
        this.revision_uid = revision_uid;
    }

    public String getRevision_log() {
        return revision_log;
    }

    public void setRevision_log(String revision_log) {
        this.revision_log = revision_log;
    }

    public String getRevision_translation_affected() {
        return revision_translation_affected;
    }

    public void setRevision_translation_affected(String revision_translation_affected) {
        this.revision_translation_affected = revision_translation_affected;
    }

    public String getDefault_langcode() {
        return default_langcode;
    }

    public void setDefault_langcode(String default_langcode) {
        this.default_langcode = default_langcode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent_translation_source() {
        return content_translation_source;
    }

    public void setContent_translation_source(String content_translation_source) {
        this.content_translation_source = content_translation_source;
    }

    public String getContent_translation_outdated() {
        return content_translation_outdated;
    }

    public void setContent_translation_outdated(String content_translation_outdated) {
        this.content_translation_outdated = content_translation_outdated;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getField_proposal_aal1() {
        return field_proposal_aal1;
    }

    public void setField_proposal_aal1(String field_proposal_aal1) {
        this.field_proposal_aal1 = field_proposal_aal1;
    }

    public String getField_proposal_aal2() {
        return field_proposal_aal2;
    }

    public void setField_proposal_aal2(String field_proposal_aal2) {
        this.field_proposal_aal2 = field_proposal_aal2;
    }

    public Comentario[] getCom() {
        return com;
    }

    public void setCom(Comentario[] com) {
        this.com = com;
    }

    public String getField_proposal_country() {
        return field_proposal_country;
    }

    public void setField_proposal_country(String field_proposal_country) {
        this.field_proposal_country = field_proposal_country;
    }

    public String getField_proposal_formatted_address() {
        return field_proposal_formatted_address;
    }

    public void setField_proposal_formatted_address(String field_proposal_formatted_address) {
        this.field_proposal_formatted_address = field_proposal_formatted_address;
    }

    public String getField_proposal_id_aviso() {
        return field_proposal_id_aviso;
    }

    public void setField_proposal_id_aviso(String field_proposal_id_aviso) {
        this.field_proposal_id_aviso = field_proposal_id_aviso;
    }

    public Imagen[] getImage() {
        return image;
    }

    public void setImage(Imagen[] image) {
        this.image = image;
    }

    public String getField_proposal_locality() {
        return field_proposal_locality;
    }

    public void setField_proposal_locality(String field_proposal_locality) {
        this.field_proposal_locality = field_proposal_locality;
    }

    public Localizacion getLoc() {
        return loc;
    }

    public void setLoc(Localizacion loc) {
        this.loc = loc;
    }

    public String getField_proposal_notify_council() {
        return field_proposal_notify_council;
    }

    public void setField_proposal_notify_council(String field_proposal_notify_council) {
        this.field_proposal_notify_council = field_proposal_notify_council;
    }

    public String getField_proposal_postal_code() {
        return field_proposal_postal_code;
    }

    public void setField_proposal_postal_code(String field_proposal_postal_code) {
        this.field_proposal_postal_code = field_proposal_postal_code;
    }

    public String getField_proposal_route_name() {
        return field_proposal_route_name;
    }

    public void setField_proposal_route_name(String field_proposal_route_name) {
        this.field_proposal_route_name = field_proposal_route_name;
    }

    public Status getField_proposal_status() {
        return field_proposal_status;
    }

    public void setField_proposal_status(Status field_proposal_status) {
        this.field_proposal_status = field_proposal_status;
    }

    @Override
    public String toString() {
        return "Propuesta{" +
                "title='" + title + '\'' +
                ", langcode='" + langcode + '\'' +
                ", nid=" + nid +
                ", uuid='" + uuid + '\'' +
                ", created=" + created +
                ", changed=" + changed +
                ", body=" + body +
                ", uid=" + uid +
                ", com=" + Arrays.toString(com) +
                ", loc=" + loc +
                ", image=" + Arrays.toString(image) +
                ", type=" + type +
                '}';
    }
}
