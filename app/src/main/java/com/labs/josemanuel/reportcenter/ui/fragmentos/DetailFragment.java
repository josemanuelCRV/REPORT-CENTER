package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Model.Comentario;
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

public class DetailFragment extends Fragment {


    // asdfcasd
    /**
     * Etiqueta de depuración
     */
    private static final String TAG = DetailFragment.class.getSimpleName();

    /*
    Instancias de Views
     */
    private ImageView bgCategoria;
    private ImageView cabecera;
    private TextView titulo;
    private TextView descripcion;
    private TextView prioridad;
    private TextView fechaLim;
    private TextView categoria;
    private ImageButton editButton;
    private String extra;
    private Gson gson = new Gson();

    /*
    instancia global del administrador
     */
    private RecyclerView.LayoutManager lManager;
    private InteractiveScrollView scrollView;

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
        cabecera = (ImageView) v.findViewById(R.id.cabecera);
        titulo = (TextView) v.findViewById(R.id.titulo);
        descripcion = (TextView) v.findViewById(R.id.descripcion);
        prioridad = (TextView) v.findViewById(R.id.prioridad);
        fechaLim = (TextView) v.findViewById(R.id.fecha);
        categoria = (TextView) v.findViewById(R.id.categoria);
        editButton = (ImageButton) v.findViewById(R.id.fab);
        scrollView = (InteractiveScrollView) v.findViewById(R.id.contenedor);

        AdaptadorComentario adapter = new AdaptadorComentario(getContext(), Infrastructure.getComentarioSeleccionada());
        scrollView.setAdapter(adapter);         // Usar un administrador para LinearLayout

        lManager = new LinearLayoutManager(getContext());
        scrollView.setLayoutManager(lManager);
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


        // Cargar datos desde el web service
        cargarDatos();

        return v;


    } // fin onCreate

    /**
     * Obtiene los datos desde el servidor
     */
    public void cargarDatos() {


        Infrastructure.getPropuestaSeleccionada();

        titulo.setText(PropSeleecionada.getTitle());
        descripcion.setText(PropSeleecionada.getBody().getValue());
        Glide.with(this).load(PropSeleecionada.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).centerCrop().into(cabecera);

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
                            cabecera.setBackgroundColor(getResources().getColor(R.color.furmitureColor));
                            break;
                        case "Limpieza":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.cleaningColor));
                            break;
                        case "Movilidad":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.movilityColor));
                            break;
                        case "Áreas Verdes":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.areaColor));
                            break;
                        case "Vida urbana":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.urbanColor));
                            break;
                        case "Otras":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.othersColor));
                            break;
                    }

                    // Seteando valores en los views
                    titulo.setText(meta.getTitulo());
                    descripcion.setText(meta.getDescripcion());
                    prioridad.setText(meta.getPrioridad());
                    fechaLim.setText(meta.getFechaLim());
                    categoria.setText(meta.getCategoria());

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


}
