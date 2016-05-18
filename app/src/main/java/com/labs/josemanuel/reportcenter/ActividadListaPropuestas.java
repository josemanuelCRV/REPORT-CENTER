package com.labs.josemanuel.reportcenter;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
<<<<<<< HEAD:app/src/main/java/com/labs/josemanuel/reportcenter/MainActivity.java
import android.util.Log;
||||||| merged common ancestors
=======
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
>>>>>>> origin/Content_main:app/src/main/java/com/labs/josemanuel/reportcenter/ActividadListaPropuestas.java
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.Gson;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Utils.VolleySingleton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD:app/src/main/java/com/labs/josemanuel/reportcenter/MainActivity.java
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //Aplicamos el patrón Singleton en el uso de Volley para generar una única instancia de una RequestQueue, o cola de peticiones
    VolleySingleton volleySingleton;
    RequestQueue requestQueue;
    JSONHandler jsonHandler;
    JsonArrayRequest mJsonArrayRequest;
    Propuesta[] feed;

    TextView tv;
    ImageView fotoCentro;
||||||| merged common ancestors
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
=======

import com.labs.josemanuel.reportcenter.provider.Contrato.Alquileres;


public class ActividadListaPropuestas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdaptadorPropuestas.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView listaUI;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorPropuestas adaptador;


>>>>>>> origin/Content_main:app/src/main/java/com/labs/josemanuel/reportcenter/ActividadListaPropuestas.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_lista_propuestas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tv=(TextView) findViewById(R.id.text);
        fotoCentro= (ImageView) findViewById(R.id.imageView2);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
<<<<<<< HEAD:app/src/main/java/com/labs/josemanuel/reportcenter/MainActivity.java

        //-----Procesar Http Volley
        //Recogemos una instancia de Volley
        volleySingleton= VolleySingleton.getInstance(this);
        requestQueue= volleySingleton.getRequestQueue();
        jsonHandler= new JSONHandler();

        mJsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                getResources().getString(R.string.URL),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesar la respuesta Json
                        procesarRespuesta(response);
                        tv.setText(String.valueOf(feed[0].getTitle()));
                        Picasso.with(MainActivity.this).load(feed[0].getImage()[0].getUrl()).into(fotoCentro);

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

        volleySingleton.addToRequestQueue(mJsonArrayRequest);
    }
||||||| merged common ancestors
    }
=======


        // nuevo
        // Preparar lista
        listaUI = (RecyclerView) findViewById(R.id.lista);
        listaUI.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        listaUI.setLayoutManager(linearLayoutManager);

        adaptador = new AdaptadorPropuestas(this, this);
        listaUI.setAdapter(adaptador);

        // Iniciar loader
        getSupportLoaderManager().restartLoader(1, null, this);





    } // fin onCreate
>>>>>>> origin/Content_main:app/src/main/java/com/labs/josemanuel/reportcenter/ActividadListaPropuestas.java

    private void procesarRespuesta(JSONArray response){
        try {
            Log.v("Respuesta!" , response.getJSONObject(0).toString());
            feed=jsonHandler.generatePropuestaArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // NUEVA SECCIÓN DE MANEJO DE RECYCLER ---------------------------------------

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Alquileres.URI_CONTENIDO, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (adaptador != null) {
            adaptador.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(AdaptadorPropuestas.ViewHolder holder, String idAlquiler) {
        Snackbar.make(findViewById(android.R.id.content), ":id = " + idAlquiler,
                Snackbar.LENGTH_LONG).show();
    }
}
