package com.labs.josemanuel.reportcenter.ui.actividades;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.fragmentos.ConfirmDialogFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.ConfirmLocationFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.DatePickerFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.InsertFragment;


public class InsertActivity extends AppCompatActivity
        implements DatePickerFragment.OnDateSelectedListener,
        ConfirmDialogFragment.ConfirmDialogListener, ConfirmLocationFragment.ConfirmDialogListenerLocation {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        if (getSupportActionBar() != null)
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_done);

        // Creación del fragmento de inserción
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new InsertFragment(), "InsertFragment")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public void onDateSelected(int year, int month, int day) {
        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");

        if (insertFragment != null) {
            insertFragment.actualizarFecha(year, month, day);
        }
    }



    // -------------  PARA LOS DIALOG DE DESCARTAR CAMBIOS
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            finish(); // Finalizar actividad descartando cambios

        }

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            // Nada por el momento

        }

    }



    // -------------  DIALOGS DE LOCALIZACIÓN
    @Override
    public void onDialogPositiveClickLocation(DialogFragment dialog) {
        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");

        if (insertFragment != null) {
            Toast.makeText(InsertActivity.this, "Nuevo Dialog Positive Location", Toast.LENGTH_SHORT).show();

        }
    }



    @Override
    public void onDialogNegativeClickLocation(DialogFragment dialog) {
        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");

        if (insertFragment != null) {
            Toast.makeText(InsertActivity.this, "Nuevo Dialog Negative Location", Toast.LENGTH_SHORT).show();

        }
    }


}
