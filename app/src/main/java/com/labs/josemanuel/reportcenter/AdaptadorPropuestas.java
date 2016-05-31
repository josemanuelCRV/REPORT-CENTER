package com.labs.josemanuel.reportcenter;

/**
 * Created by Usuario on 18/05/2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;


/**
 * Created by JMC on 17/05/2016.
 */


/**
 * En esta sección veremos cómo crear el adaptador del Recycler View con un cursor que alimente la vista.
 * <p/>
 * Adaptador con un cursor para poblar la lista de alquileres desde SQLite
 */
public class AdaptadorPropuestas extends RecyclerView.Adapter<AdaptadorPropuestas.ViewHolder> {
    private final Context contexto;
    private Propuesta[] propuestas;

    /*
    interfaz de comunicación, mecanismo para que la actividad o fragment escuche los clicks que escucha View.OnClickListener
    sobre los view holders. Con esta transición en cadena es posible saber el identificador y
    el view al que se clickeó a través de OnItemClickListener.onClick().
     */
    private OnItemClickListener escucha;

    interface OnItemClickListener {
        void onClick(ViewHolder holder, String idAlquiler);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        // Referencias UI
        public TextView viewTitle;
        public TextView viewUbicacion;
        public TextView viewBody;
        public TextView viewUsername;
        public ImageView viewFoto;

        public ViewHolder(View v) {
            super(v);
            viewTitle = (TextView) v.findViewById(R.id.nombre);
            viewUbicacion = (TextView) v.findViewById(R.id.ubicacion);
            viewBody = (TextView) v.findViewById(R.id.descripcion);
            viewUsername = (TextView) v.findViewById(R.id.precio);
            viewFoto = (ImageView) v.findViewById(R.id.foto);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            escucha.onClick(this, obtenerNid(getAdapterPosition()));
        }
    }

    //Identificador de la propuesta
    private String obtenerNid(int posicion) {
        if (propuestas != null) {
            return propuestas[posicion].getNid();
        }
        return null;
    }

    //Constructor de la clase
    public AdaptadorPropuestas(Context contexto, OnItemClickListener escucha, Propuesta[] propuestas) {
        this.contexto = contexto;
        this.escucha = escucha;
        this.propuestas=propuestas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_lista_alquiler, parent, false);
        return new ViewHolder(v);
    }

    /*
    método moveToPosition() dentro de onBindViewHolder() para acceder a la posición del cursor dependiendo del parámetro position.
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Propuesta propuesta= propuestas[position];
        holder.viewTitle.setText(propuesta.getTitle());
        Localizacion loc = propuesta.getLoc();
        /**
         * 26/05/2016
         * visualización nodo vacío en Localización
         * Si no se ha registrado la localización de la propuesta, muestra lat= 0 lg=0
        * */
        if(loc.getLongitude().length()==1)
            holder.viewUbicacion.setText(String.format("Latitud %1s\nLongitud %2s",loc.getLatitude(),loc.getLongitude()));
        else
            holder.viewUbicacion.setText(String.format("Latitud %1s\nLongitud %2s",loc.getLatitude().substring(0,7),loc.getLongitude().substring(0,7)));
        holder.viewBody.setText(propuesta.getBody().getValue());
        holder.viewUsername.setText(String.format("idUsuario %s" ,propuesta.getUid().getTarget_id())); // Consultar en api el username del id
        if(propuesta.getImage()!=null)
            Glide.with(contexto).load(propuesta.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).into(holder.viewFoto);
        else
            Glide.with(contexto).load(R.drawable.bg_city2).into(holder.viewFoto);

    }


    // Obtén la cantidad de ítems con el método getCount() del cursor.
    @Override
    public int getItemCount() {
        if (propuestas != null)
            return propuestas.length;
        return 0;
    }


}