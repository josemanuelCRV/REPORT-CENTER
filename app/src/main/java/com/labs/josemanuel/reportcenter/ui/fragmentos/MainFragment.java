package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.AdaptadorPropuestas;
import com.labs.josemanuel.reportcenter.ui.actividades.InsertActivity;
import com.labs.josemanuel.reportcenter.Utils.VolleySingleton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * Fragmento principal que contiene la lista de las metas
 */
public class MainFragment extends Fragment {

    //UI
    // private RecyclerView listaUI;
    private TextView emptyFeedTextView;
    // private LinearLayoutManager linearLayoutManager;
    // private AdaptadorPropuestas adaptador;


    /*
    Etiqueta de depuracion
     */
    private static final String TAG = MainFragment.class.getSimpleName();

    /*
    Adaptador del recycler view
     */
    private AdaptadorPropuestas adapter;

    /*
    Instancia global del recycler view
     */
    private RecyclerView lista;

    /*
    instancia global del administrador
     */
    private RecyclerView.LayoutManager lManager;

    /*
    Instancia global del FAB
     */
    com.melnykov.fab.FloatingActionButton fab;
    private Gson gson = new Gson();


    //Data
    //Aplicamos el patrón Singleton en el uso de Volley para generar una única instancia de una RequestQueue, o cola de peticiones
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    //Clase manejadora de JSON
    JSONHandler jsonHandler = new JSONHandler();
    JsonArrayRequest mJsonArrayRequest;
    //Array de Propuestas POJO.
    Propuesta[] feed;


    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_main, container, false);

        emptyFeedTextView = (TextView) v.findViewById(R.id.empty_view);

        lista = (RecyclerView) v.findViewById(R.id.reciclador);
        lista.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);

        // Obtener instancia del FAB
        fab = (com.melnykov.fab.FloatingActionButton) v.findViewById(R.id.fab);

        // Asignar escucha al FAB
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar actividad de inserción
                        getActivity().startActivityForResult(
                                new Intent(getActivity(), InsertActivity.class), 3);
                    }
                }


        );


        //Data
        //Recogemos una instancia de Volley
        volleySingleton = VolleySingleton.getInstance(getActivity());
        //Recogemos una cola de peticiones Http
        requestQueue = volleySingleton.getRequestQueue();
        // Cargar datos en el adaptador
        cargarAdaptador();

        return v;
    }


    //Metodo envoltorio de la inserción de los POJO en el array de propuestas
    private Propuesta[] procesarRespuesta(JSONArray response) {
        try {
            Log.v("Respuesta!", response.getJSONObject(0).toString());
            return feed = jsonHandler.generatePropuestaArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }



/*    */

    /**
     * Carga el adaptador con las metas obtenidas
     * en la respuesta
     */

    public void cargarAdaptador() {
        mJsonArrayRequest = new JsonArrayRequest(
                getResources().getString(R.string.URL),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesar la respuesta Json
                        feed = procesarRespuesta(response);
                        emptyFeedTextView.setVisibility(View.GONE);
                        lista.setVisibility(View.VISIBLE);
                        adapter = new AdaptadorPropuestas(MainFragment.this.getContext(), feed);
                        lista.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Log.d(TAG, "Error Volley: " + error.toString());
                        error.printStackTrace();
                    }
                }
        );

        if (feed == null) {
            lista.setVisibility(View.GONE);
            // emptyFeedTextView.setVisibility(View.VISIBLE);
        }

        volleySingleton.addToRequestQueue(mJsonArrayRequest);

    }


}
