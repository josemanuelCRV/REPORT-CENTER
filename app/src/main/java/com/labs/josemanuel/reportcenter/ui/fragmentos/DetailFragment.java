package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.plus.model.people.Person;
import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.Utils.JsonConstants;
import com.labs.josemanuel.reportcenter.modelo.Meta;
import com.labs.josemanuel.reportcenter.tools.Constantes;
import com.labs.josemanuel.reportcenter.tools.Infrastructure;
import com.labs.josemanuel.reportcenter.ui.AdaptadorComentario;
import com.labs.josemanuel.reportcenter.ui.InteractiveScrollView;
import com.labs.josemanuel.reportcenter.ui.actividades.UpdateActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailFragment extends Fragment implements OnMapReadyCallback {
    // añadido OnMapReadyCallback


    /**
     * Etiqueta de depuración
     */
    private static final String TAG = DetailFragment.class.getSimpleName();


    /*
    Instancias de Views
     */
    private ImageView bgCategoria;
    private ImageView viewCabeceraDetalle;
    private TextView viewTituloDetalle;
    private TextView viewDescripcionDetalle;
    private TextView viewFechaDetalle;
    private TextView viewCategoriaDetalle;
    private TextView viewDireccion;
    private TextView viewEstado;
    private ImageView viewFlagState;


    private GoogleMap mMap;


    private String abierta = "1";

    private ImageButton editButton;
    private String extra;
    private Gson gson = new Gson();
    private ScrollView panoflasquesomos;

    /*
    instancia global del administrador
     */
    private RecyclerView.LayoutManager lManager;

    private InteractiveScrollView scrollView;
    private LinearLayout comentariosContainer;
    private ImageButton btnVolver;

    private Propuesta PropSeleecionada = Infrastructure.getPropuestaSeleccionada();


    public DetailFragment() {
    }

    public static DetailFragment createInstance(String idNodo) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(JsonConstants.NID, idNodo);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        // Obtención de views
        bgCategoria = (ImageView) v.findViewById(R.id.bg_category);
        viewCabeceraDetalle = (ImageView) v.findViewById(R.id.cabecera);
        viewTituloDetalle = (TextView) v.findViewById(R.id.titulo);
        viewDescripcionDetalle = (TextView) v.findViewById(R.id.descripcion);
        viewFechaDetalle = (TextView) v.findViewById(R.id.fecha);
        viewCategoriaDetalle = (TextView) v.findViewById(R.id.categoria);
        viewDireccion = (TextView) v.findViewById(R.id.direccion);
        viewEstado = (TextView) v.findViewById(R.id.estado);
        viewFlagState = (ImageView) v.findViewById(R.id.flag_category); // Flag / Flag_Check_Done

        editButton = (ImageButton) v.findViewById(R.id.fab);
        btnVolver = (ImageButton) v.findViewById(R.id.btnBack);
        panoflasquesomos = (ScrollView) v.findViewById(R.id.panoflasquesomos);


        //Layout que contiene botón retroceder y comentarios

        comentariosContainer = (LinearLayout) v.findViewById(R.id.comentariosContainer);
        scrollView = (InteractiveScrollView) v.findViewById(R.id.comentarios);

        AdaptadorComentario adapter = new AdaptadorComentario(getContext(), Infrastructure.getComentarioSeleccionada());


        scrollView.setAdapter(adapter);         // Usar un administrador para LinearLayout

        lManager = new LinearLayoutManager(getContext());
        scrollView.setLayoutManager(lManager);

        comentariosContainer.setVisibility(View.VISIBLE);

        // Obtener extra del intent de envío
        extra = getArguments().getString(Constantes.EXTRA_ID);


        // Setear escucha para el fab
        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar actividad de actualización
                        Intent i = new Intent(getActivity(), UpdateActivity.class);
                        i.putExtra(Constantes.EXTRA_ID, extra);
                        getActivity().startActivityForResult(i, Constantes.CODIGO_ACTUALIZACION);
                    }
                }
        );


        // ********* Activar para evento al llegar al final del scroll
        // y sacar el contenedor de los comentarios del Scroll-Linear que tiene el escuchador


        // panoflasquesomos.setOnScrollChangeListener(new OnScrollListenerEnAnabolizantes(comentariosContainer));


      /*  btnVolver.setVisibility(View.VISIBLE);
        if(panoflasquesomos.isSelected() ){
            btnVolver.setVisibility(View.INVISIBLE);
        }else
            btnVolver.setVisibility(View.VISIBLE);*/


        // ************* Boton close

      /*  btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panoflasquesomos.setVisibility(View.VISIBLE);
                comentariosContainer.setVisibility(View.GONE);
                // btnVolver.setVisibility(View.GONE);
            }
        });*/


//        scrollView.setOnBottomReachedListener(new InteractiveScrollView.OnBottomReachedListener() {
//            @Override
//            public void onBottomReached() {
//                Log.v("EVENTO!","LLEGUE AL FINAL");
//            }
//        });
//


        /*scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.v("ScrollMOved","Up&down");
                *//*View view = scrollView.getChildAt(scrollView.getChildCount()-1);
                int diff = (view.getBottom()-(scrollView.getHeight()+scrollView.getScrollY()));

                if (diff == 0 && scrollView.getOnBottomReachedListener()!= null) {
                    scrollView.getOnBottomReachedListener();
                }*//*
        }}
        );*/

        //scrollView.setOnScrollChangeListener(new OnScrollListenerEnAnabolizantes());

        // Cargar datos desde el web service
        cargarDatos();

        return v;


    } // fin onCreate

    /**
     * Obtiene los datos desde el servidor
     */
    public void cargarDatos() {


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

        Infrastructure.getPropuestaSeleccionada();

        // título
        viewTituloDetalle.setText(PropSeleecionada.getTitle());
        // descripción
        viewDescripcionDetalle.setText(PropSeleecionada.getBody().getValue());
        // fecha
        String fecha = PropuestaHandler.parseDate(PropSeleecionada.getCreated());
        viewFechaDetalle.setText(fecha);
        // dirección
        viewDireccion.setText(PropSeleecionada.getField_proposal_formatted_address());
        // categoria
        String category = PropSeleecionada.getField_proposal_status().getUrl();
        viewCategoriaDetalle.setText(category);
        // Stado abierta/cerrada
        if (PropSeleecionada.getStatus().equals(abierta)) {
            viewEstado.setText("Abierta");
            viewFlagState.setImageResource(R.drawable.ic_bookmark); // cambia la bandera
        } else {
            viewEstado.setText("Cerrada");
            viewFlagState.setImageResource(R.drawable.bookmark_check);
        }
        // foto
        Glide.with(this).load(PropSeleecionada.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).centerCrop().into(viewCabeceraDetalle);



        /*String fecha = PropuestaHandler.parseDate(comentario.getTimestamp());
        holder.viewTimestamp.setText(fecha.toString());
*/
        /*
        bgCategoria = (ImageView) v.findViewById(R.id.bg_category);
        viewCabeceraDetalle = (ImageView) v.findViewById(R.id.cabecera);
        viewTituloDetalle = (TextView) v.findViewById(R.id.titulo);
        viewDescripcionDetalle = (TextView) v.findViewById(R.id.descripcion);
        viewPrioridadDetalle = (TextView) v.findViewById(R.id.prioridad);
        viewFechaDetalle = (TextView) v.findViewById(R.id.fecha);
        viewCategoriaDetalle = (TextView) v.findViewById(R.id.categoria);
        editButton = (ImageButton) v.findViewById(R.id.fab);
        btnVolver = (ImageButton) v.findViewById(R.id.btnBack);
        panoflasquesomos = (ScrollView) v.findViewById(R.id.panoflasquesomos);
        */


    }

    /**
     * Procesa cada uno de los estados posibles de la
     * respuesta enviada desde el servidor
     *
     * @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {

        try {
            // Obtener atributo "mensaje"
            String mensaje = response.getString("estado");

            switch (mensaje) {
                case "1":
                    // Obtener objeto "meta"
                    JSONObject object = response.getJSONObject("meta");

                    //Parsear objeto
                    Meta meta = gson.fromJson(object.toString(), Meta.class);

                    // Asignar color del fondo
                    switch (meta.getCategoria()) {
                        case "Moviliario Urbano":
                            viewCabeceraDetalle.setBackgroundColor(getResources().getColor(R.color.furmitureColor));
                            break;
                        case "Limpieza":
                            viewCabeceraDetalle.setBackgroundColor(getResources().getColor(R.color.cleaningColor));
                            break;
                        case "Movilidad":
                            viewCabeceraDetalle.setBackgroundColor(getResources().getColor(R.color.movilityColor));
                            break;
                        case "Áreas Verdes":
                            viewCabeceraDetalle.setBackgroundColor(getResources().getColor(R.color.areaColor));
                            break;
                        case "Vida urbana":
                            viewCabeceraDetalle.setBackgroundColor(getResources().getColor(R.color.urbanColor));
                            break;
                        case "Otras":
                            viewCabeceraDetalle.setBackgroundColor(getResources().getColor(R.color.othersColor));
                            break;
                    }

                    // Seteando valores en los views
                    viewTituloDetalle.setText(meta.getTitulo());
                    viewDescripcionDetalle.setText(meta.getDescripcion());
                    viewFechaDetalle.setText(meta.getFechaLim());
                    viewCategoriaDetalle.setText(meta.getCategoria());

                    break;

                case "2":
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje2,
                            Toast.LENGTH_LONG).show();
                    break;

                case "3":
                    String mensaje3 = response.getString("mensaje");
                    Toast.makeText(
                            getActivity(),
                            mensaje3,
                            Toast.LENGTH_LONG).show();
                    break;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
