package com.labs.josemanuel.reportcenter.ui.actividades;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.labs.josemanuel.reportcenter.Http.TrustAllSSLCerts;
import com.labs.josemanuel.reportcenter.Infrastructure.Credentials;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.fragmentos.MainFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawer;
    NavigationView navigationView;
    public MainActivity() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new TrustAllSSLCerts().nuke();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Credentials.setAuthorization(preferences.getString("Authorization", "-1"));
        Credentials.setX_CSRF_Token(preferences.getString("X_CSRF_Token", "-1"));
        String cadena = Credentials.getAuthorization() + Credentials.getX_CSRF_Token();
        Toast.makeText(MainActivity.this, cadena, Toast.LENGTH_LONG).show();


        // Creación del Toolbar y el NavigationDrawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

    // Creación del fragmento principal
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment(), "MainFragment")
                    .commit();
        }


        toggle.syncState();
        navigationView.bringToFront();
        navigationView.requestFocus();
        navigationView.setNavigationItemSelectedListener(this);


    } // FIN onCreate


    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
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
        Log.v("I say ", "hey .. hey!");
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
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
