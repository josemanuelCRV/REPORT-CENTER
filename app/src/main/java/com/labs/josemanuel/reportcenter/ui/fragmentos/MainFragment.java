package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Http.ClienteHttp;
import com.labs.josemanuel.reportcenter.Http.TrustAllSSLCerts;
import com.labs.josemanuel.reportcenter.Http.VolleySingleton;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.Utils.DialogBuilder;
import com.labs.josemanuel.reportcenter.ui.AdaptadorPropuestas;

import com.labs.josemanuel.reportcenter.ui.actividades.InsertActivity;

import org.json.JSONArray;

import java.util.concurrent.ExecutionException;

import static com.google.android.gms.internal.zzir.runOnUiThread;

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


    //Data
    //Aplicamos el patrón Singleton en el uso de Volley para generar una única instancia de una RequestQueue, o cola de peticiones
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    //Clase manejadora de JSON
    JSONHandler jsonHandler = new JSONHandler();
    JsonArrayRequest mJsonArrayRequest;
    //Array de Propuestas POJO.
    Propuesta[] feed;
    ClienteHttp mClienteHttp;

    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        new TrustAllSSLCerts().nuke();
        mClienteHttp= new ClienteHttp(getResources().getString(R.string.URL),getActivity());


        View v = inflater.inflate(R.layout.fragment_main, container, false);

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

        emptyFeedTextView = (TextView) v.findViewById(R.id.empty_view);

        loadProposalFeed();

        return v;
    }

    private void loadProposalFeed() {
        if (mClienteHttp.isNetworkAvailable()) {
            final AsyncTask<RequestFuture<JSONArray>, Void, Propuesta[]> loadProposalFeedTask = mClienteHttp.getPropuestas();

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("RT", "Thread t Begins");
                    try {
                        feed = loadProposalFeedTask.get();//
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cargarAdaptador();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                }
            });
            t.start();
        } else {
            DialogBuilder dialogBuilder = new DialogBuilder(getActivity());
            dialogBuilder.alertUserAboutError();
        }
    }
    public void cargarAdaptador() {
        emptyFeedTextView.setVisibility(View.GONE);
        lista.setVisibility(View.VISIBLE);
        adapter = new AdaptadorPropuestas(MainFragment.this.getContext(), feed);
        lista.setAdapter(adapter);
    }


}
