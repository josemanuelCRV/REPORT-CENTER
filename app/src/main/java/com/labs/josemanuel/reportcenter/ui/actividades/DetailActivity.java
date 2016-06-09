package com.labs.josemanuel.reportcenter.ui.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.toolbox.RequestFuture;
import com.bumptech.glide.Glide;
import com.labs.josemanuel.reportcenter.Controler.ConstantsTransition;
import com.labs.josemanuel.reportcenter.Infrastructure.Infrastructure;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Controler.JsonConstants;
import com.labs.josemanuel.reportcenter.Http.ClienteHttp;
import com.labs.josemanuel.reportcenter.Infrastructure.Infrastructure;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.CommentWithUser;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.User;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.Utils.DialogBuilder;
import com.labs.josemanuel.reportcenter.ui.fragmentos.DetailFragment;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Usuario on 25/04/2016.
 */

public class DetailActivity extends AppCompatActivity {
    /**
     * Instancia global de la meta a detallar
     */
    private String idNodo;
    private Propuesta PropSeleecionada = Infrastructure.getPropuestaSeleccionada();

    DetailFragment mDetailFragment= null;


    private ImageView viewImageParalax;

    public static final String EXTRA_DRAWABLE = "com.labs.josemanuel.reportcenter.drawable";


    /**
     * Inicia una nueva instancia de la actividad
     *
     * @param activity Contexto desde donde se lanzará
     * @param idNodo   Identificador de la meta a detallar
     */
    public static void launch(Activity activity, String idNodo) {
        Intent intent = getLaunchIntent(activity, idNodo);
        activity.startActivity(intent);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad
     * de detalle.
     *
     * @param context Contexto donde se inicia
     * @param idNodo  Identificador de la meta
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, String idNodo) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(JsonConstants.NID, idNodo);
        return intent;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // oculta el progressBar
        /*spinner = (ProgressBar)
                v.findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.SwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);*/





        // ----------------------------------------------------------
        // nuevo para Toldo

       setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);

/*
        collapser.setTitle(); // Cambiar título
*/
        // viewImageParalax = (ImageView) findViewById(R.id.image_paralax);


       // loadImageParallax();// Cargar Imagen

        // ------------------------------------------------------


        // Antiguo ------------------------------------------

        //Comienzan las tareas asíncronas a recoger información del servidor
        //Recogida usuario propuesta
        Propuesta propuesta= Infrastructure.getPropuestaSeleccionada();
        int numComentarios=0;
        ClienteHttp mClienteHttp = new ClienteHttp("http://stag.hackityapp.com/api/user/"+propuesta.getUid().getTarget_id()+"?_format=api_json", this);

        //pasamos la propuesta seleccionada && //Recogida comentarios propuesta
        if (mClienteHttp.isNetworkAvailable()) {
            final AsyncTask<RequestFuture<JSONObject>, Void, User> getUser = mClienteHttp.getUsuario();
            numComentarios=propuesta.getCom().length;
            //Si la propuesta no tiene comentarios, no se realiza

            if(numComentarios==0) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("RT", "Thread t Begins");
                        try {
                            final User user = getUser.get();
                            Log.v("user developer", user.getName());
                        } catch (InterruptedException | ExecutionException e) {
                            if (e instanceof ExecutionException )
                                Log.e("Erroraco",  e.getMessage());
                            else
                                Log.e("ErroracoInterrupted",e.getMessage());
                        }

                    }
                });
                t.start();
            }else{
                String idComentario= propuesta.getCom()[0].getId(); //Id del primer comentario
                ClienteHttp.setmUrl("http://stag.hackityapp.com/api/comment/" + idComentario + "?_format=api_json");
                final AsyncTask<RequestFuture<JSONObject>, Void, CommentWithUser> getCommentario = mClienteHttp.getCommentWithUser();
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("RT", "HiloRecogeComentarios Begins");
                        try {

                            final User user = getUser.get();
                            Log.v("user developer", user.getName());
                            final CommentWithUser commentWithUser=getCommentario.get();
                            CommentWithUser[] comments = new CommentWithUser[1];
                            comments[0]=commentWithUser;
                            Infrastructure.setComment(comments);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mDetailFragment= (DetailFragment)getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);
                                    mDetailFragment.notifyWhenDataChanged();
                                    Log.d("RT", "!notifyWhenDataChanged!!");
                                }
                            });
                        } catch (InterruptedException | ExecutionException e) {
                            if (e instanceof ExecutionException )
                                Log.e("Erroraco", ((ExecutionException) e).getMessage());
                            else
                                Log.e("ErroracoInterrupted",e.getMessage());
                        }

                    }
                });
                t.setName("HiloRecogeComentarios");
                t.start();
            }
        } else {
            DialogBuilder dialogBuilder = new DialogBuilder(this);
            dialogBuilder.alertUserAboutError();
        }

        // Antiguo ------------------------------------------


        // Retener instancia
        if (getIntent().getStringExtra(JsonConstants.NID) != null)
            idNodo = getIntent().getStringExtra(JsonConstants.NID);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DetailFragment.createInstance(idNodo), "DetailFragment")
                    .commit();

            // mDetailFragment=(DetailFragment)getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);
        }

    }


    // METODOS DEL TOOLBAR NUEVO ----------------------------------------------------

    private void setToolbar() {
        // Añadir la Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * Se carga una imagen aleatoria para el detalle
     */
    /*public void loadImageParallax() {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);

        DetailFragment mDetailFragment=(DetailFragment)getSupportFragmentManager().findFragmentByTag(DetailFragment.TAG);


        // Usando Glide para la carga asíncrona
        Glide.with(mDetailFragment.getContext())
                .load(PropSeleecionada.getImage()[0].getUrl())
                .centerCrop()
                .into(image);
    }*/

    // Glide.with(this).load(PropSeleecionada.getImage()[0].getUrl()).placeholder(R.drawable.bg_city2).centerCrop().into(image);
    // Glide.with(contexto).load(R.drawable.bg_city2).centerCrop().into(holder.viewFoto);

    // FIN METODOS DEL TOOLBAR NUEVO ----------------------------------------------------




    // FINN onCreate

}
