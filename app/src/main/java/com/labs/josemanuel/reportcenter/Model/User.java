package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by Miguel on 5/27/2016.
 */
public class User {
    String links;
    String id;
    String type;
    String uid;
    String uuid;
    String langcode;
    String name;
    String created;
    String changed;
    String path;
    String avatars_avatar_generator;
    String avatars_user_picture;
    String user_picture;
    String links_;


    public User(String links, String id, String type, String uid, String uuid, String langcode, String name, String created, String changed, String path, String avatars_avatar_generator, String avatars_user_picture, String user_picture, String links_) {
        this.links = links;
        this.id = id;
        this.type = type;
        this.uid = uid;
        this.uuid = uuid;
        this.langcode = langcode;
        this.name = name;
        this.created = created;
        this.changed = changed;
        this.path = path;
        this.avatars_avatar_generator = avatars_avatar_generator;
        this.avatars_user_picture = avatars_user_picture;
        this.user_picture = user_picture;
        this.links_ = links_;
    }

    public User(){}

    public static User userFactory(String links, String id, String type, String uid, String uuid, String langcode,String name,
                                   String created,String changed,String path,String avatars_avatar_generator,String avatars_user_picture,
                                   String user_picture,String links_) throws NullPointerException{
        User user=  new User("-1","-1","-1","-1","-1","-1","-1","1","1","1","1","1","1","1");

        if(links!=null)
            user.setLinks(links);
        if(id!=null)
            user.setId(id);
        if(type !=null)
            user.setType(type);
        if(uid!=null)
            user.setUid(uid);
        if(uuid!=null)
            user.setUuid(uuid);
        if(langcode!=null)
            user.setLangcode(langcode);
        if(name!=null)
            user.setName(name);
        if(created!=null)
            user.setCreated(created);
        if(changed!=null)
            user.setChanged(changed);
        if(path!=null)
            user.setPath(path);
        if(avatars_avatar_generator!=null)
            user.setAvatars_avatar_generator(avatars_avatar_generator);
        if(avatars_user_picture!=null)
            user.setAvatars_user_picture("http://www.los3dragones.com/imagenes/chucknorris/norris2.jpg");
        if(user_picture!=null)
            user.setUser_picture("http://www.los3dragones.com/imagenes/chucknorris/norris2.jpg");
        if(links!=null)
            user.setLangcode(links_);


        return user;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLinks_() {
        return links_;
    }

    public void setLinks_(String links_) {
        this.links_ = links_;
    }

    @Override
    public String toString() {
        return "User{" +
                "links='" + links + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", uid='" + uid + '\'' +
                ", uuid='" + uuid + '\'' +
                ", langcode='" + langcode + '\'' +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                ", changed='" + changed + '\'' +
                ", path='" + path + '\'' +
                ", avatars_avatar_generator='" + avatars_avatar_generator + '\'' +
                ", avatars_user_picture='" + avatars_user_picture + '\'' +
                ", user_picture='" + user_picture + '\'' +
                ", links_='" + links_ + '\'' +
                '}';
    }
}
