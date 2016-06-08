package com.labs.josemanuel.reportcenter.ui.actividades;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.fragmentos.ConfirmDialogFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.ConfirmLocationFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.DatePickerFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.InsertFragment;
import com.labs.josemanuel.reportcenter.ui.fragmentos.MapsActivity;





public class InsertActivity extends AppCompatActivity
        implements DatePickerFragment.OnDateSelectedListener,
        ConfirmDialogFragment.ConfirmDialogListener, ConfirmLocationFragment.ConfirmDialogListenerLocation,OnMapReadyCallback {



    private GoogleMap mMap;
    private LocationManager locManager;
    private LocationListener locListener;
    final String MIAPIKEY ="AIzaSyCtBCoNv0I0ZdvlONRzUUZ_CcABO-d7g-s";



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



    // ********************************************************************

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // check de permisos
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        // Comprueba el proveedor disponible

        Location loc = null;
        if(locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }else{
            loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }


        Double latitud= Double.valueOf(loc.getLatitude());
        Double longitud = Double.valueOf(loc.getLongitude());

        LatLng myLocation = new LatLng(latitud, longitud);

        Log.i("", "Latitud: " + latitud);
        Log.i("", "longitud: " + longitud);

        Toast.makeText(InsertActivity.this, "Latitud es:"+latitud, Toast.LENGTH_SHORT).show();

        //LatLng myLocation = new LatLng(loc.getLatitude(), loc.getLongitude());
        mMap.addMarker(new MarkerOptions().position(myLocation).title("Mi ubicación"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));//movemos la camara hasta nuestra posicion
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation,14));//amploa zoom del 1 al 21(mas cercano)
        /////////



        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(40.4522651,-3.7805837 );
        mMap.addMarker(new MarkerOptions().position(sydney).title("Mi ubicación"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14));*/



    }
















}
