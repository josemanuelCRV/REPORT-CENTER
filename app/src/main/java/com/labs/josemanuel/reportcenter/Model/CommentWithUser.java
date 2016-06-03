package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by Miguel on 6/2/2016.
 */
public class CommentWithUser extends Comment {
    private String name_user;
    private String pic_user;

    public CommentWithUser(String cid_attributes, String id_data_uid_relationships, String created_attributes, String changed_attributes, String value_comment_body_attributes, String format_comment_body_attributes, String name_user, String pic_user) {
        super(cid_attributes, id_data_uid_relationships, created_attributes, changed_attributes, value_comment_body_attributes, format_comment_body_attributes);
        this.name_user = name_user;
        this.pic_user = pic_user;
    }

    public static CommentWithUser commentWithUserFactory(String cid_attributes,
                                                         String id_data_uid_relationships, String created_attributes,
                                                         String changed_attributes, String value_comment_body_attributes,
                                                         String format_comment_body_attributes, String name_user,
                                                         String pic_user) throws NullPointerException{
        CommentWithUser comment =  new CommentWithUser("-1","-1","-1","-1","-1","-1","chuck norris","http://a1.files.biography.com/image/upload/c_fill,cs_srgb,dpr_1.0,g_face,h_300,q_80,w_300/MTE5NDg0MDU1MjQ5OTc4ODk1.jpg");

        if(cid_attributes!=null)
            comment.setCid_attributes(cid_attributes);
        if(id_data_uid_relationships!=null)
            comment.setId_data_uid_relationships(id_data_uid_relationships);
        if(created_attributes !=null)
            comment.setCreated_attributes(created_attributes);
        if(changed_attributes!=null)
            comment.setChanged_attributes(changed_attributes);
        if(value_comment_body_attributes!=null)
            comment.setValue_comment_body_attributes(value_comment_body_attributes);
        if(format_comment_body_attributes!=null)
            comment.setFormat_comment_body_attributes(format_comment_body_attributes);
        if(name_user!=null)
            comment.setName_user(name_user);
        if(pic_user!=null)
            comment.setPic_user(pic_user);

        return comment;
    }


    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getPic_user() {
        return pic_user;
    }

    public void setPic_user(String pic_user) {
        this.pic_user = pic_user;
    }
}
