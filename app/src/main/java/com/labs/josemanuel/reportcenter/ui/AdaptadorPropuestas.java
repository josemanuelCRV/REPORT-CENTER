package com.labs.josemanuel.reportcenter.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.tools.Infrastructure;
import com.labs.josemanuel.reportcenter.ui.actividades.DetailActivity;
import com.labs.josemanuel.reportcenter.ui.fragmentos.MapsActivity;

/**
 * Adaptador del RecyclerView que rellena la lista
 */
public class AdaptadorPropuestas extends RecyclerView.Adapter<AdaptadorPropuestas.ViewHolder> {
    private final Context contexto;
    private Propuesta[] propuestas;

    private final String abierta = "1";


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
        public TextView viewFecha;
        public TextView viewEstado;
        public ImageView viewFlagState;
        public FloatingActionButton viewFabFollow;

        public ViewHolder(View v) {
            super(v);
            viewTitle = (TextView) v.findViewById(R.id.titulo);
            viewUbicacion = (TextView) v.findViewById(R.id.ubicacion);
            viewBody = (TextView) v.findViewById(R.id.descripcion);
            viewUsername = (TextView) v.findViewById(R.id.username);
            viewFoto = (ImageView) v.findViewById(R.id.foto);
            viewFecha = (TextView) v.findViewById(R.id.fecha);
            viewEstado = (TextView) v.findViewById(R.id.categoria);
            viewFlagState = (ImageView) v.findViewById(R.id.status_flag);
            viewFabFollow = (FloatingActionButton) v.findViewById(R.id.btnFollow);


            v.setOnClickListener(this);


            // Boton Follow
            viewFabFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent goMain = new Intent(contexto, MapsActivity.class);
                    contexto.startActivity(goMain);
                /*Snackbar.make(view, "Añadido a tus favoritos", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                }
            });
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


    /*
     // getField_proposal_country --> Spain
        // getField_proposal_aal1 --> Comunidad de Madrid
        // getField_proposal_aal2 --> Madrid
        // getField_proposal_locality --> Madrid
        // getField_proposal_postal_code --> 28023
        // getField_proposal_route_name --> Calle del Puerto de Balbarrán


        // getDefault_langcode --> 1 ¿?
        // getField_proposal_id_aviso --> ¿?
        // getChanged --> 1463892295 milisegundos
        // getNid --> 457 xejm
        // getPath --> ¿?
        // getPromote --> 0, ¿?
        // getRevision_log --> ¿?
        // getStatus() --> 1, ¿?
        // getSticky --> 0 , ¿?
        // getUid().getUrl() --> /en/user/301
        // getVid --> 374
        // getLangcode --> en,
        // getField_proposal_status().getUrl() --> en/taxonomy/term/1
        // getField_proposal_status().getTarget_id --> 1, ¿?
        // getField_proposal_status().getTarget_type --> taxonomy_term


        // taxonomy/term/ ----------------------->
        // term/3 --> Urban equipament
        // temr/4 --> Cleaning
        // term/5 --> Mobility
        // term/6 --> Green areas
        // term/7 --> Neighborhood life
        // term/8 --> Others

     */


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Propuesta propuesta = propuestas[position];

        // Título
        if (propuesta.getTitle() != null) {
            holder.viewTitle.setText(propuesta.getTitle());
        } else {
            holder.viewTitle.setText("Título no encontrado");
        }

        // Dirección
        if (propuesta.getField_proposal_route_name() + ", " + propuesta.getField_proposal_locality() != null) {
            holder.viewUbicacion.setText(propuesta.getField_proposal_route_name() + ", " + propuesta.getField_proposal_locality());
        } else {
            holder.viewUbicacion.setText("dirección no encontrada");
        }

        // Localizacion loc = propuesta.getLoc();
        /**
         * 26/05/2016
         * visualización nodo vacío en Localización
         * Si no se ha registrado la localización de la propuesta, muestra lat= 0 lg=0
         * */
        /*if (loc.getLongitude().length() == 1)
            holder.viewUbicacion.setText(String.format("Latitud %1s\nLongitud %2s", loc.getLatitude(), loc.getLongitude()));
        else
            holder.viewUbicacion.setText(String.format("Latitud %1s\nLongitud %2s", loc.getLatitude().substring(0, 7), loc.getLongitude().substring(0, 7)));
*/
        // propuesta.getField_proposal_route_name() = calle del aviador
        // propuesta.getField_proposal_locality() = Madrid
        // propuesta.getStatus() = 1 o 2
        // propuesta.getPromote() = 0 ¿?
        // propuesta.getSticky() = 0


        // Estado
        if (propuesta.getStatus() != null) {
            if (propuesta.getStatus().equals(abierta)) {
                holder.viewEstado.setText("Abierta");
                holder.viewFlagState.setImageResource(R.drawable.status_open_bg);
            } else {
                holder.viewEstado.setText("Cerrada");
                holder.viewFlagState.setImageResource(R.drawable.status_closed_bg);
            }
        } else {
            holder.viewEstado.setText("Estado no encontrado");
        }

        // Descripción
        if (propuesta.getBody().getValue() != null) {
            holder.viewBody.setText(propuesta.getBody().getValue());
        } else {
            holder.viewBody.setText("Descripción no encontrada");
        }

        // ID de Usuario
        if (propuesta.getUid().getTarget_id() != null) {
            holder.viewUsername.setText(String.format("idUsuario %s", propuesta.getUid().getTarget_id() + " propuso")); // Consultar en api el username del id
        } else {
            holder.viewUsername.setText("Usuario desconocido");
        }

        // Imágen de Propuesta
        /*if (propuesta.getImage()[0].getUrl().length() != 0) {
            Glide.with(contexto).load(propuesta.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).centerCrop().into(holder.viewFoto);
        } else {
            Glide.with(contexto).load(R.drawable.bg_city2).centerCrop().into(holder.viewFoto);
        }*/

        Glide.with(contexto).load(R.drawable.bg_city2).centerCrop().into(holder.viewFoto);

        // Fecha de Propuesta
        if (propuesta.getCreated() != null) {
            String fecha = PropuestaHandler.getTimeFromToday(propuesta.getCreated());
            holder.viewFecha.setText(fecha.toString());
        } else {
            holder.viewFecha.setText("sin fecha");
        }


    }


    // Obtén la cantidad de ítems con el método getCount() del cursor.
    @Override
    public int getItemCount() {
        if (propuestas != null)
            return propuestas.length;
        return 0;
    }


}

