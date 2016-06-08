package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labs.josemanuel.reportcenter.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locManager;
    private LocationListener locListener;
    final String MIAPIKEY ="AIzaSyCtBCoNv0I0ZdvlONRzUUZ_CcABO-d7g-s";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        // llamar a comenzar localización
        comenzarLocalizacion();
    }


    public void comenzarLocalizacion() {
        //Obtenemos una referencia al LocationManager
        locManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Obtenemos la última posición conocida
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Mostramos la última posición conocida Log
        mostrarPosicion(loc);

        //Nos registramos para recibir actualizaciones de la posición
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                mostrarPosicion(location);
            }

            public void onProviderDisabled(String provider) {
                // lblEstado.setText("Provider OFF");
            }

            public void onProviderEnabled(String provider) {
                // lblEstado.setText("Provider ON ");
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                Log.i("", "Provider Status: " + status);
                // lblEstado.setText("Provider Status: " + status);
            }
        };

        locManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 30000, 0, locListener);
    }

    private void mostrarPosicion(Location loc) {
        Toast.makeText(MapsActivity.this, "pasamos por mostrar posicion", Toast.LENGTH_SHORT).show();

        if (loc != null) {
            // lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
            // lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
            // lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));
            Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));


        } else {
            // lblLatitud.setText("Latitud: (sin_datos)");
            // lblLongitud.setText("Longitud: (sin_datos)");
            // lblPrecision.setText("Precision: (sin_datos)");
        }
    }





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

        Toast.makeText(MapsActivity.this, "Latitud es:"+latitud, Toast.LENGTH_SHORT).show();

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
