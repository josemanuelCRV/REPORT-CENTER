package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labs.josemanuel.reportcenter.Controler.JsonConstants;
import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Infrastructure.Infrastructure;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.RandomHero;
import com.labs.josemanuel.reportcenter.Model.User;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.AdaptadorComment;
import com.labs.josemanuel.reportcenter.ui.InteractiveScrollView;


public class DetailFragment extends Fragment {


    /**
     * Etiqueta de depuración
     */
    public static final String TAG = DetailFragment.class.getSimpleName();


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
    private String abierta = "1";
    private ImageButton editButton;
    private String extra;
    private ScrollView panoflasquesomos;

    private ImageView viewImageParalax;


    // para identificacion de usuario
    private TextView viewUsername;
    private ImageView viewFoto;


    // inicializadas en el constructor de clase.
    private Double lat;
    private Double lon;

    AdaptadorComment mAdapter;

    /*
    instancia global del administrador
     */
    private RecyclerView.LayoutManager lManager;
    // scroll con acción Interactiva
    private InteractiveScrollView scrollView;
    // contenedor de los comentarios
    private LinearLayout comentariosContainer;
    private ImageButton btnVolver;
    // instancia SupportMapFragment para el mapa
    private SupportMapFragment mSupportMapFragment;
    // Propuesta seleccionada
    private Propuesta PropSeleecionada = Infrastructure.getPropuestaSeleccionada();


    // CONSTRUCTOR DE CLASE
    // inicializadas las variables que recogen la localización
    public DetailFragment() {
        lon = Double.valueOf(PropSeleecionada.getLoc().getLongitude());
        lat = Double.valueOf(PropSeleecionada.getLoc().getLatitude());
    }

    // crea instancia de Detail_Fragment
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

        // infla la vista con el fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);


        // vinculando los componentes de la vista
        //  bgCategoria = (ImageView) v.findViewById(R.id.bg_category);  // preparada para taxonomy
        //  viewCabeceraDetalle = (ImageView) v.findViewById(R.id.cabecera);

        // nuevo para usuario nombre y foto
        viewUsername = (TextView) v.findViewById(R.id.username_detail);
        viewFoto = (ImageView) v.findViewById(R.id.fotouser_detail);


        viewTituloDetalle = (TextView) v.findViewById(R.id.titulo);
        viewDescripcionDetalle = (TextView) v.findViewById(R.id.descripcion);
        viewFechaDetalle = (TextView) v.findViewById(R.id.fecha);
        viewCategoriaDetalle = (TextView) v.findViewById(R.id.categoria);
        viewDireccion = (TextView) v.findViewById(R.id.direccion);
        viewEstado = (TextView) v.findViewById(R.id.estado);
        viewFlagState = (ImageView) v.findViewById(R.id.flag_category); // Flag / Flag_Check_Done


        editButton = (ImageButton) v.findViewById(R.id.fab); // boton para Editar Propuesta
//        btnVolver = (ImageButton) v.findViewById(R.id.btnBack); // sin uso ahora el Fab

        // Scroll general que contiene los Datos Detalle Propuesta + Contenedor Comentarios
        panoflasquesomos = (ScrollView) v.findViewById(R.id.panoflasquesomos);


        viewImageParalax = (ImageView) v.findViewById(R.id.image_paralax);


        // OBTENER EL MAP-FRAGMENT y colocarlo en el frame del fragment_detail

        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }

        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {

                        googleMap.getUiSettings().setAllGesturesEnabled(true);
                        /*
                         En el constructor de la clase declaramos la variables (lat,lon)
                        */

                        // -> marker_latlng recoge la latitud y longitud en formato double//
                        LatLng marker_latlng = new LatLng(lat, lon);
                        // configurando la vista del mapa, setea posición, mueve la camara,aplica zoom, coloca título y controles
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(marker_latlng).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);
                        googleMap.addMarker(new MarkerOptions().position(marker_latlng).title(PropSeleecionada.getTitle()));
                        googleMap.getUiSettings().setCompassEnabled(true);
                        googleMap.getUiSettings().setZoomControlsEnabled(true);

                    }
                }
            });

        }


        // Contenedor Comentarios que muestra las Cards
        comentariosContainer = (LinearLayout) v.findViewById(R.id.comentariosContainer);

        // Aplica InteractiveScrollView al scroll en el que se muestran las cards de comentarios
        scrollView = (InteractiveScrollView) v.findViewById(R.id.comentarios);

        // adapter recoge el Comentario seleccionado
        mAdapter = new AdaptadorComment(getContext(), Infrastructure.getComment());

        // introduce en el scroll la propuesta seleccionada almacenada en el adaptador
        scrollView.setAdapter(mAdapter);


        // Usar un administrador para LinearLayout y aplicarlo al scroll
        lManager = new LinearLayoutManager(getContext());
        scrollView.setLayoutManager(lManager);

        // Hace visible el contenedor de los comentarios
        comentariosContainer.setVisibility(View.VISIBLE);

        // Obtener extra del intent de envío
        //extra = getArguments().getString(Constantes.EXTRA_ID);


        // Setear escucha para el fab
     /*   editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar actividad de actualización
                        Intent i = new Intent(getActivity(), UpdateActivity.class);
                        //i.putExtra(Constantes.EXTRA_ID, extra);
                        //getActivity().startActivityForResult(i, Constantes.CODIGO_ACTUALIZACION);
                        getActivity().startActivity(i);
                    }
                }
        );*/


        // Cargar datos desde el web service
        cargarDatos();


        loadImageParallax();// Cargar Imagen


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
        // MAPA
        // getLoc().getLatitude() --> 40.383617
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
//        Glide.with(this).load(PropSeleecionada.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).centerCrop().into(viewCabeceraDetalle);


        //   viewUsername.setText(String.format("idUsuario %s", PropSeleecionada.getUid().getTarget_id() + " propuso"));


        //  viewFoto.setImageDrawable(RandomHero.getHero().getResourceId(R.id.));


        //extraemos el drawable en un bitmap
            /*Drawable originalDrawable = v.getResources().getDrawable(RandomHero.getHero().getResourceId());*/

        Drawable originalDrawable = getResources().getDrawable(RandomHero.getHero().getResourceId());
        // Drawable originalDrawable = v.getResources().getDrawable(R.drawable.photo_profile);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        // seteamos el contenido mas abajo
        viewFoto.setImageDrawable(roundedDrawable);


        viewUsername.setText(RandomHero.getHero().getName());


    }


    public void notifyWhenDataChanged() {
        //adapter recoge el Comentario seleccionado
        mAdapter = new AdaptadorComment(getContext(), Infrastructure.getComment());
        scrollView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }

    /**
     * Se carga una imagen aleatoria para el detalle
     */
    public void loadImageParallax() {

        // DetailFragment mDetailFragment=(DetailFragment)getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);
        // ImageView image = (ImageView) findViewById(R.id.image_paralax);

        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(PropSeleecionada.getImage()[0].getUrl())
                .centerCrop()
                .into(viewImageParalax);
    }


}
