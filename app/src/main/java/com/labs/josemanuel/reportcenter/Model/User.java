package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by Miguel on 5/27/2016.
 */
public class User {
    private String uid;
    private String uuid;
    private String langcode;
    private String preferred_langcode;
    private String preferred_admin_langcode;
    private String name;
    private String mail;
    private String timezone;
    private String status;
    private String changed;
    private String access;
    private String login;
    private String init;
    private String roles;
    private String default_langcode;
    private String path;
    private String avatars_avatar_generator;
    private String avatars_user_picture;
    private String user_picture;

    public User(String uid, String uuid, String langcode, String preferred_langcode, String preferred_admin_langcode, String name, String mail, String timezone, String status, String changed, String access, String login, String init, String roles, String default_langcode, String path, String avatars_avatar_generator, String avatars_user_picture, String user_picture) {
        this.uid = uid;
        this.uuid = uuid;
        this.langcode = langcode;
        this.preferred_langcode = preferred_langcode;
        this.preferred_admin_langcode = preferred_admin_langcode;
        this.name = name;
        this.mail = mail;
        this.timezone = timezone;
        this.status = status;
        this.changed = changed;
        this.access = access;
        this.login = login;
        this.init = init;
        this.roles = roles;
        this.default_langcode = default_langcode;
        this.path = path;
        this.avatars_avatar_generator = avatars_avatar_generator;
        this.avatars_user_picture = avatars_user_picture;
        this.user_picture = user_picture;
    }

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLangcode() {
        return langcode;
    }

    public void setLangcode(String langcode) {
        this.langcode = langcode;
    }

    public String getPreferred_langcode() {
        return preferred_langcode;
    }

    public void setPreferred_langcode(String preferred_langcode) {
        this.preferred_langcode = preferred_langcode;
    }

    public String getPreferred_admin_langcode() {
        return preferred_admin_langcode;
    }

    public void setPreferred_admin_langcode(String preferred_admin_langcode) {
        this.preferred_admin_langcode = preferred_admin_langcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChanged() {
        return changed;
    }

    public void setChanged(String changed) {
        this.changed = changed;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getInit() {
        return init;
    }

    public void setInit(String init) {
        this.init = init;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public String getAvatars_avatar_generator() {
        return avatars_avatar_generator;
    }

    public void setAvatars_avatar_generator(String avatars_avatar_generator) {
        this.avatars_avatar_generator = avatars_avatar_generator;
    }

    public String getAvatars_user_picture() {
        return avatars_user_picture;
    }

    public void setAvatars_user_picture(String avatars_user_picture) {
        this.avatars_user_picture = avatars_user_picture;
    }

    public String getUser_picture() {
        return user_picture;
    }

    public void setUser_picture(String user_picture) {
        this.user_picture = user_picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", langcode='" + langcode + '\'' +
                ", preferred_langcode='" + preferred_langcode + '\'' +
                ", preferred_admin_langcode='" + preferred_admin_langcode + '\'' +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", timezone='" + timezone + '\'' +
                ", status='" + status + '\'' +
                ", changed='" + changed + '\'' +
                ", access='" + access + '\'' +
                ", login='" + login + '\'' +
                ", init='" + init + '\'' +
                ", roles='" + roles + '\'' +
                ", default_langcode='" + default_langcode + '\'' +
                ", path='" + path + '\'' +
                ", avatars_avatar_generator='" + avatars_avatar_generator + '\'' +
                ", avatars_user_picture='" + avatars_user_picture + '\'' +
                ", user_picture='" + user_picture + '\'' +
                '}';
    }
}
