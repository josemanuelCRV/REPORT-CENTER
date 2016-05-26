package com.labs.josemanuel.reportcenter.ui;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.tools.Infrastructure;
import com.labs.josemanuel.reportcenter.ui.actividades.DetailActivity;

/**
 * Adaptador del RecyclerView que rellena la lista
 */
public class AdaptadorPropuestas extends RecyclerView.Adapter<AdaptadorPropuestas.ViewHolder> {
    private final Context contexto;
    private Propuesta[] propuestas;




    //Constructor de la clase
    public AdaptadorPropuestas(Context contexto, Propuesta[] propuestas) {
        this.contexto = contexto;
        this.propuestas = propuestas;

    }


    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView viewTitle;
        public TextView viewUbicacion;
        public TextView viewBody;
        public TextView viewUsername;
        public ImageView viewFoto;
        public Button viewDetalleButton;

        public ViewHolder(View v) {
            super(v);
            viewTitle = (TextView) v.findViewById(R.id.titulo);
            viewUbicacion = (TextView) v.findViewById(R.id.ubicacion);
            viewBody = (TextView) v.findViewById(R.id.descripcion);
            viewUsername = (TextView) v.findViewById(R.id.username);
            viewFoto = (ImageView) v.findViewById(R.id.foto);
            viewDetalleButton = (Button) v.findViewById(R.id.btn_detalle);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            obtenerNid(getAdapterPosition());
            obtenerPropuesta(getAdapterPosition());
            //pasamos la propuesta seleccionada
            Infrastructure.setPropuestaSeleccionada(obtenerPropuesta(getAdapterPosition()));
            Infrastructure.setComentarioSeleccionada(obtenerPropuesta(getAdapterPosition()).getCom());
            DetailActivity.launch(
                    (Activity) contexto, obtenerNid(getAdapterPosition()));
        }
    }


    //Identificador de la propuesta
    private String obtenerNid(int posicion) {
        if (propuestas != null) {
            return propuestas[posicion].getNid();
        }
        return null;
    }

    private Propuesta obtenerPropuesta(int posicion) {
        if (propuestas != null) {
            return propuestas[posicion];
        }
        return null;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_propuestas, parent, false);
        return new ViewHolder(v);
    }

    /*
    método moveToPosition() dentro de onBindViewHolder() para acceder a la posición del cursor dependiendo del parámetro position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Propuesta propuesta = propuestas[position];
        holder.viewTitle.setText(propuesta.getTitle());
        Localizacion loc = propuesta.getLoc();
        //holder.viewUbicacion.setText(String.format("Latitud %1s\nLongitud %2s", loc.getLatitude().substring(0, 7), loc.getLongitude().substring(0, 7)));
        holder.viewBody.setText(propuesta.getBody().getValue());
        holder.viewUsername.setText(String.format("idUsuario %s", propuesta.getUid().getTarget_id())); // Consultar en api el username del id
        Glide.with(contexto).load(propuesta.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).centerCrop().into(holder.viewFoto);


    }


    // Obtén la cantidad de ítems con el método getCount() del cursor.
    @Override
    public int getItemCount() {
        if (propuestas != null)
            return propuestas.length;
        return 0;
    }


}

