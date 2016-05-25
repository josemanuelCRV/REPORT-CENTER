package com.labs.josemanuel.reportcenter;

/**
 * Created by Usuario on 18/05/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Model.Localizacion;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.squareup.picasso.Picasso;

import java.io.IOException;


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
    private Cursor items;
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
            //escucha.onClick(this, obtenerIdAlquiler(getAdapterPosition()));
            escucha.onClick(this, obtenerNid(getAdapterPosition()));
        }
    }

    /*
    Retorna en el valor de la columna "idAlquiler" de la posición actual.
    Este método es muy útil a la hora de leer los eventos de click y mostrar detalles.
     */
    /*private String obtenerIdAlquiler(int posicion) {
        if (items != null) {
            if (items.moveToPosition(posicion)) {
                return items.getString(ConsultaAlquileres.ID_ALQUILER);
            }
        }
        return null;
    }*/
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
        holder.viewUbicacion.setText(String.format("Latitud %1s\nLongitud %2s",loc.getLatitude().substring(0,7),loc.getLongitude().substring(0,7)));
        holder.viewBody.setText(propuesta.getBody().getValue());
        holder.viewUsername.setText(String.format("idUsuario %s" ,propuesta.getUid().getTarget_id())); // Consultar en api el username del id
        Glide.with(contexto).load(propuesta.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).into(holder.viewFoto);

        /*items.moveToPosition(position);

        String s;

        // Asignación UI
        s = items.getString(ConsultaAlquileres.NOMBRE);
        holder.viewTitle.setText(s);

        s = items.getString(ConsultaAlquileres.UBICACION);
        holder.viewUbicacion.setText(s);

        s = items.getString(ConsultaAlquileres.DESCRIPCION);
        holder.viewBody.setText(s);

        s = items.getString(ConsultaAlquileres.PRECIO);
        holder.viewUsername.setText(String.format("%s participantes", s));

        s = items.getString(ConsultaAlquileres.URL);
        Glide.with(contexto).load(s).centerCrop().into(holder.viewFoto);*/

    }


    // Obtén la cantidad de ítems con el método getCount() del cursor.
    @Override
    public int getItemCount() {
        if (propuestas != null)
            return propuestas.length;
        return 0;
    }


    // Intercambia el cursor actual por uno nuevo. Y
    // Notifica que el cursor cambió con notifyDataSetChangeg()
    public void swapCursor(Cursor nuevoCursor) {
        if (nuevoCursor != null) {
            items = nuevoCursor;
            notifyDataSetChanged();
        }
    }

    // Retorna en el cursor actual para darle uso externo.
    public Cursor getCursor() {
        return items;
    }

    // Ten a la mano el índice de las columnas a consultar del cursor
    interface ConsultaAlquileres {
        int ID_ALQUILER = 1;
        int NOMBRE = 2;
        int UBICACION = 3;
        int DESCRIPCION = 4;
        int PRECIO = 5;
        int URL = 6;
    }
}