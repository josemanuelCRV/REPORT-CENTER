package com.labs.josemanuel.reportcenter.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.RequestFuture;
import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Http.ClienteHttp;
import com.labs.josemanuel.reportcenter.Infrastructure.Infrastructure;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.User;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.Utils.DialogBuilder;
import com.labs.josemanuel.reportcenter.ui.actividades.DetailActivity;
import com.labs.josemanuel.reportcenter.ui.fragmentos.MapsActivity;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

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
                }
            });
        }

        @Override
        public void onClick(View view) {
            ClienteHttp mClienteHttp = new ClienteHttp("http://stag.hackityapp.com/api/user/"+ propuestas[getAdapterPosition()].getUid().getTarget_id()+"?_format=api_json", contexto);
            obtenerNid(getAdapterPosition());
            obtenerPropuesta(getAdapterPosition());
            //pasamos la propuesta seleccionada
            Infrastructure.setPropuestaSeleccionada(obtenerPropuesta(getAdapterPosition()));
            Infrastructure.setComentarioSeleccionada(obtenerPropuesta(getAdapterPosition()).getCom());
            if (mClienteHttp.isNetworkAvailable()) {
                final AsyncTask<RequestFuture<JSONObject>, Void, User> getUser = mClienteHttp.getUsuario();

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("RT", "Thread t Begins");
                        try {
                            final User user = getUser.get();
                            Log.v("user developer", user.getName());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();
            } else {
                DialogBuilder dialogBuilder = new DialogBuilder(contexto);
                dialogBuilder.alertUserAboutError();
            }

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

        if (propuesta.getImage() != null)
            Glide.with(contexto).load(propuesta.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).into(holder.viewFoto);
        else
            Glide.with(contexto).load(R.drawable.bg_city2).into(holder.viewFoto);

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

