package com.labs.josemanuel.reportcenter.ui.actividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.labs.josemanuel.reportcenter.Controler.JsonConstants;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.fragmentos.DetailFragment;

/**
 * Created by Usuario on 25/04/2016.
 */

public class DetailActivity extends AppCompatActivity {
    /**
     * Instancia global de la meta a detallar
     */
    private String idNodo;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        // getSupportActionBar().setIcon(R.drawable.ic_menu_share);
        // getSupportActionBar().setIcon(R.drawable.ic_arrow_back);

      /*  if (getSupportActionBar() != null) {
            // Dehabilitar titulo de la actividad
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            // Setear ícono "X" como Up button
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close);

        }*/





        // Retener instancia
        if (getIntent().getStringExtra(JsonConstants.NID) != null)
            idNodo = getIntent().getStringExtra(JsonConstants.NID);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DetailFragment.createInstance(idNodo), "DetailFragment")
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
