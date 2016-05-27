package com.labs.josemanuel.reportcenter;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.Model.Comentario;
import com.labs.josemanuel.reportcenter.Model.Comment;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Http.ClienteHttp;
import com.labs.josemanuel.reportcenter.Http.NukeSSLCerts;

import org.json.JSONArray;
import org.json.JSONException;

import com.labs.josemanuel.reportcenter.provider.Contrato.Alquileres;


public class ActividadListaPropuestas extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdaptadorPropuestas.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {
    //UI
    private RecyclerView listaUI;
    private TextView emptyFeedTextView;
    private LinearLayoutManager linearLayoutManager;
    private AdaptadorPropuestas adaptador;
    ClienteHttp clienteHttp;
    //Clase manejadora de JSON
    JSONHandler jsonHandler = new JSONHandler();
    JsonArrayRequest mJsonArrayRequest;
    //Array de Propuestas POJO.
    Propuesta[] feed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new NukeSSLCerts().nuke();

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
        editor.putBoolean("login",false);
        editor.apply();

        //UI
        setContentView(R.layout.actividad_lista_propuestas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        emptyFeedTextView= (TextView) findViewById(R.id.empty_view);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Iniciar loader
        getSupportLoaderManager().restartLoader(1, null, this);
        // nuevo
        // Preparar lista
        listaUI = (RecyclerView) findViewById(R.id.lista);
        linearLayoutManager = new LinearLayoutManager(this);
        listaUI.setLayoutManager(linearLayoutManager);
        //Data
        //----------------------------------Nuevo
        clienteHttp= new ClienteHttp(getString(R.string.URL),this);
        clienteHttp.initiate();
        loadProposalFeed();


        //Una vez que tengamos la activity de addProposal activada, le pasamos al método JSONHandler.generateJSONObjectFromPropuesta() la  POJO.
        //clienteHttp.makePost(JSONHandler.generateJsonStringFromPropuesta());


    } // fin onCreate



    private void loadProposalFeed() {
        String  tag_JsonArray_req = "mJsonArrayRequest";
        /**
         * Misma finalidad que NukeSSLCerts.
         * @see NukeSSLCerts
         * */
        //HttpsTrustManager.allowAllSSL();
        mJsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                getResources().getString(R.string.URL),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Procesar la respuesta Json
                        feed=procesarRespuesta(response);//
                        emptyFeedTextView.setVisibility(View.GONE);//
                        listaUI.setVisibility(View.VISIBLE);//
                        adaptador = new AdaptadorPropuestas(ActividadListaPropuestas.this, ActividadListaPropuestas.this,feed);
                        listaUI.setAdapter(adaptador);

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
        clienteHttp.addToRequestQueue(tag_JsonArray_req,mJsonArrayRequest);
    }
    //Metodo envoltorio de la inserción de los POJO en el array de propuestas
    private Propuesta[] procesarRespuesta(JSONArray response){
        try {
            Log.v("Respuesta!" , response.getJSONObject(0).toString());
            return feed=jsonHandler.generatePropuestaArray(response);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Comment[] procesarCommentarios(Comentario[] comentarios){
        Comment[] salida = new Comment[comentarios.length];
        int i = 0;
        for(Comentario comentario : comentarios){
            salida[i]= JSONHandler.generateComment(clienteHttp.getCommentFromCid(comentario.getId()));
            i++;
        }
        return salida;

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
    public void onClick(AdaptadorPropuestas.ViewHolder holder, String nidPropuesta) {
        Snackbar.make(findViewById(android.R.id.content), ":nid = " + nidPropuesta,
                Snackbar.LENGTH_LONG).show();
    }

}
