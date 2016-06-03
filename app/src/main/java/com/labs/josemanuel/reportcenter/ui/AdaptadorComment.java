package com.labs.josemanuel.reportcenter.ui;

/**
 * Created by JMC on 25/05/2016.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.R;

/*
         Adaptador del RecyclerView que rellena la lista
        */
public class AdaptadorComment extends RecyclerView.Adapter<AdaptadorComment.ViewHolder> {
    private final Context contexto;
    private CommentWithUser[] mComments;


    //Constructor de la clase
    public AdaptadorComment(Context contexto,CommentWithUser[] comments) {
        this.contexto = contexto;
        this.mComments = comments;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Referencias UI
        public ImageView viewImagenUser; //inyecta foto del usuario
        public TextView viewName;
        public TextView viewTimestamp;
        public TextView viewBodyComment;
        public TextView viewCid;



        @TargetApi(Build.VERSION_CODES.M)
        public ViewHolder(View v) {
            super(v);
            viewImagenUser=(ImageView)v.findViewById(R.id.fotoGravatar);
            viewName = (TextView) v.findViewById(R.id.name);
            viewCid = (TextView) v.findViewById(R.id.cid);
            viewTimestamp = (TextView) v.findViewById(R.id.timestamp);
            viewBodyComment = (TextView) v.findViewById(R.id.body);


        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_comment, parent, false);
        return new ViewHolder(v);
    }

    /*
    método moveToPosition() dentro de onBindViewHolder() para acceder a la posición del cursor dependiendo del parámetro position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CommentWithUser comment = mComments[position];

        //Los recursos de una aplicación Android son integer de longitud 10, por tanto para cargar la imagen de un randonHero, guardada en memoria, es necesaria
        //hacer la comprobación, para realizar el casteo correspondiente
        if(comment.getPic_user().length()==10)
            Glide.with(contexto).load(Integer.parseInt(comment.getPic_user())).into(holder.viewImagenUser);
        else
            Glide.with(contexto).load(comment.getPic_user()).into(holder.viewImagenUser);

        holder.viewName.setText(comment.getName_user());
        holder.viewTimestamp.setText(PropuestaHandler.getTimeFromToday(comment.getCreated_attributes()));
        holder.viewBodyComment.setText(comment.getValue_comment_body_attributes()); // Consultar en api el username del id
        holder.viewCid.setText(comment.getCid_attributes());


    }


    // Obtén la cantidad de ítems con el método getCount() del cursor.
    @Override
    public int getItemCount() {
        if (mComments != null)
            return mComments.length;
        return 0;
    }


}

