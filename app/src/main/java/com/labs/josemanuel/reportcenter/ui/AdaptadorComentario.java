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
import android.widget.TextView;

import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.R;

/*
         Adaptador del RecyclerView que rellena la lista
        */
public class AdaptadorComentario extends RecyclerView.Adapter<AdaptadorComentario.ViewHolder> {
    private final Context contexto;
    private Comentario[] comentarios;
    private Comment[] comments;


    //Constructor de la clase
    public AdaptadorComentario(Context contexto, Comentario[] comentarios) {
        this.contexto = contexto;
        this.comentarios = comentarios;
        this.comments = comments;

    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Referencias UI
        public TextView viewStatus;
        public TextView viewId;
        public TextView viewTimestamp;
        public TextView viewName;
        public TextView viewUid;
        public TextView viewCount;
        public ImageButton btnVolver;


        @TargetApi(Build.VERSION_CODES.M)
        public ViewHolder(View v) {
            super(v);
            viewStatus = (TextView) v.findViewById(R.id.status);
            viewId = (TextView) v.findViewById(R.id.id);
            viewTimestamp = (TextView) v.findViewById(R.id.timestamp);
            viewName = (TextView) v.findViewById(R.id.name);
            viewUid = (TextView) v.findViewById(R.id.cid);
            viewCount = (TextView) v.findViewById(R.id.count);


        }

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_comentario, parent, false);
        return new ViewHolder(v);
    }

    /*
    método moveToPosition() dentro de onBindViewHolder() para acceder a la posición del cursor dependiendo del parámetro position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Comentario comentario = comentarios[position];
        holder.viewCount.setText(comentario.getCount());
        holder.viewCount.setText(comentario.toString());
        holder.viewId.setText(comentario.getId());
        holder.viewName.setText(comentario.getName());
        holder.viewStatus.setText(comentario.getStatus()); // Consultar en api el username del id
        String fecha = PropuestaHandler.parseDate(comentario.getTimestamp());
        holder.viewTimestamp.setText(fecha.toString());

        Comment comments;


        //holder.viewTimestamp.setText(comentario.getTimestamp()); // Consultar en api el username del id
        //holder.viewUid.setText(comentario.getUid().getTarget_id()); // Consultar en api el username del id


    }


    // Obtén la cantidad de ítems con el método getCount() del cursor.
    @Override
    public int getItemCount() {
        if (comentarios != null)
            return comentarios.length;
        return 0;
    }


}

