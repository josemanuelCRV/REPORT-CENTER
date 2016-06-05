package com.labs.josemanuel.reportcenter.ui.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.josemanuel.reportcenter.Http.TrustAllSSLCerts;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.fragmentos.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView emptyFeedTextView;

    private Context contexto;

    // private static final String EXTRA_DRAWABLE = "com.labs.josemanuel.reportcenter.drawable";



    public MainActivity() {

    }

    // --------------------------------------
    // nuevo para Toldo
   /* public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, MainFragment.class);
        intent.putExtra(EXTRA_DRAWABLE , R.drawable.bg_city2);
        return intent;
    }*/
// --------------------------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TrustAllSSLCerts().nuke();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //
        Credentials.setAuthorization(preferences.getString("Authorization","-1"));
        Credentials.setX_CSRF_Token(preferences.getString("X_CSRF_Token","-1"));
        String cadena = Credentials.getAuthorization() + Credentials.getX_CSRF_Token();
        Toast.makeText(MainActivity.this, cadena, Toast.LENGTH_LONG).show();


        // ----------------------------------------------------------
        // nuevo para Toldo
    /*    setToolbar();// Añadir action bar
        if (getSupportActionBar() != null) // Habilitar up button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        int idDrawable = i.getIntExtra(EXTRA_DRAWABLE, -1);

        CollapsingToolbarLayout collapser =
                (CollapsingToolbarLayout) findViewById(R.id.collapser);*/

//        collapser.setTitle(getPackageName().toString()); // Cambiar título


//        loadImageParallax(idDrawable);// Cargar Imagen
//
        // ------------------------------------------------------

        // Creación del Toolbar y el NavigationDrawer
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        emptyFeedTextView = (TextView) findViewById(R.id.empty_view);
//        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        /*if (getSupportActionBar() != null) {
            // Dehabilitar titulo de la actividad
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // Setear ícono "X" como Up button
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close);
        }*/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);



       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
*/

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Creación del fragmento principal
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment(), "MainFragment")
                    .commit();
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
   /* private void loadImageParallax(int id) {
        ImageView image = (ImageView) findViewById(R.id.image_paralax);
        // Usando Glide para la carga asíncrona
        Glide.with(this)
                .load(id)
                .centerCrop()
                .into(image);
    }*/

    // Glide.with(contexto).load(R.drawable.bg_city2).centerCrop().into(holder.viewFoto);

    // FIN METODOS DEL TOOLBAR NUEVO ----------------------------------------------------

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constantes.CODIGO_DETALLE || requestCode == 3) {
            if (resultCode == RESULT_OK || resultCode == 203) {
                MainFragment fragment = (MainFragment) getSupportFragmentManager().
                        findFragmentByTag("MainFragment");
                fragment.cargarAdaptador();
            }
        }
    }*/

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

        if (id == R.id.nav_inicio) {
            // Handle the camera action
        } else if (id == R.id.nav_crea_propuesta) {

        } else if (id == R.id.nav_mi_cuenta) {

        } else if (id == R.id.nav_logout) {
            Intent intentLogout = new Intent(MainActivity.this, LoginActivity.class);
            intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intentLogout);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
