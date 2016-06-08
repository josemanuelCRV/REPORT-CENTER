package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.labs.josemanuel.reportcenter.Controler.JSONHandler;
import com.labs.josemanuel.reportcenter.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Fragmento que permite al usuario insertar un nueva meta
 */
public class InsertFragment extends Fragment implements LocationListener, View.OnClickListener {


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * Etiqueta para depuración
     */
    private static final String TAG = InsertFragment.class.getSimpleName();

    /*
    Controles
    */
    EditText titleField;
    EditText bodyField;
    TextView dateField;
    Spinner categorySpinner;
    Button imageBtn;
    // instancia SupportMapFragment para el mapa
    SupportMapFragment mSupportMapFragment;
    CheckBox councilCheckBox;
    // Instancia global del FAB
    com.melnykov.fab.FloatingActionButton fabCamera;

    LatLng marker_latlng;
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/CityHacks/";
    private File file = new File(ruta_fotos);


    public InsertFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar al fragmento para contribuir en la action bar
        setHasOptionsMenu(true);

        mostrarDialogoLocalizacion();

    } // FIN onCreate


    // 0 Crear localización y mostrar en el mapa
    // 1º dialog con pregunta de si estas en este punto nada más entrar
    // 2º  Si estas recoge lat_long
    // 3º  si No - setVisible tru input search
    // icono buscar envía petición a google places para ser devuelta lat_long en base a un nombre de calle
    // preparar datos para el envio dentro del modelo de datos


    /// -------------------------------------------------------------------------
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflando layout del fragmento
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        // Obtención de instancias controles
        titleField = (EditText) v.findViewById(R.id.titleField);
        bodyField = (EditText) v.findViewById(R.id.bodyField);
        dateField = (TextView) v.findViewById(R.id.dateLabel);
        categorySpinner = (Spinner) v.findViewById(R.id.categorySpinner);
        imageBtn = (Button) v.findViewById(R.id.btn_select_img);
        councilCheckBox = (CheckBox) v.findViewById(R.id.councilCheckbox);


        // prioridad_spinner = (Spinner) v.findViewById(R.id.prioridad_spinner);

        //Si no existe crea la carpeta donde se guardaran las fotos
        file.mkdirs();
        //accion para el boton
        // Obtener instancia del FAB
        fabCamera = (com.melnykov.fab.FloatingActionButton) v.findViewById(R.id.fab);
        // Asignar escucha al FAB
        fabCamera.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String file = ruta_fotos + getCode() + ".jpg";
                        File mi_foto = new File(file);
                        try {
                            mi_foto.createNewFile();
                        } catch (IOException ex) {
                            Log.e("ERROR ", "Error:" + ex);
                        }
                        //
                        Uri uri = Uri.fromFile(mi_foto);
                        //Abre la camara para tomar la foto
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        //Guarda imagen
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        //Retorna a la actividad
                        startActivityForResult(cameraIntent, 0);
                    }
                }
        );

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Ready to send", setJSONObjectForSending().toString());
            }
        });
        // Data Picker
        dateField.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment picker = new DatePickerFragment();
                        picker.show(getFragmentManager(), "datePicker");

                    }
                }
        );


        // INSTANCIAR EL MAPA
        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere1);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere1, mSupportMapFragment).commit();
        }


        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {
                        marker_latlng = getLatLng(googleMap);
                        if (marker_latlng == null) return;


                        // -> marker_latlng recoge la latitud y longitud en formato double//
                        //  LatLng marker_latlng = new LatLng(lat, lon);

                        // configurando la vista del mapa, setea posición, mueve la camara,aplica zoom, coloca título y controles
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(marker_latlng).zoom(15.0f).build();
                        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
                        googleMap.moveCamera(cameraUpdate);
                        googleMap.addMarker(new MarkerOptions().position(marker_latlng).title("Posición de propuesta"));
                        googleMap.getUiSettings().setCompassEnabled(true);
                        googleMap.getUiSettings().setZoomControlsEnabled(true);

                    }
                }
            });

        }
        return v;

    } // FIN onCreateView

    //********************************************************* for refactor's sake...
    @Nullable
    private LatLng getLatLng(GoogleMap googleMap) {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        googleMap.setMyLocationEnabled(true);

        // Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);


        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(new Criteria(), true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);
        //LatLng marker_latlng = new LatLng(lat, lon);
        LatLng marker_latlng;
        if (location != null) {
            // Getting latitude of the current location
            double latitude = location.getLatitude();

            // Getting longitude of the current location
            double longitude = location.getLongitude();

            marker_latlng = new LatLng(latitude, longitude);

        } else {
            marker_latlng = new LatLng(40.4169514, -3.7057225);//Puerta del Sol
        }
        return marker_latlng;
    }


    private JSONObject setJSONObjectForSending() {
        JSONObject propuestaToSend = new JSONObject();
        try {
            /*Definición objeto que se envía en POST a Drupal
            * _links es un objeto que se integra en el formato hal_json.
            * Gracias a este objeto, el sistema permite relacionar este objeto JSON con otras entidades.
            * Es una versión relacional del json (HAL => Hypertext Application Language)
            * */
            JSONHandler.setJsonObjectNode(propuestaToSend, "_links");
            JSONHandler.setValueJsonObject(propuestaToSend, "_links", "type", new JSONObject().put("href", "http://stag.hackityapp.com/rest/type/node/proposal"));

            /*
            * El cuerpo del mensaje, texto del textbox
            * */
            JSONHandler.setJsonObjectNode(propuestaToSend, "body");
            JSONHandler.setValueJsonObject(propuestaToSend, "body", "value", bodyField.getText().toString());
            /*
            * Se especifica en el uid la referencia al usuario que envía la propuesta,
            * en este momento no tenemos forma de rescatar el uid del usuario que se logea,
            * porque no tenemos un sistema de login propiamente dicho.
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "uid");
            JSONHandler.setValueJsonArray(propuestaToSend, "uid", "target_id", "301");
            JSONHandler.setValueJsonArray(propuestaToSend, "uid", "target_type", "user");
            JSONHandler.setValueJsonArray(propuestaToSend, "uid", "url", "/en/user/301");
            /*
            * La categoría cambiará en función de lo que se elija en el combobox a la hora de la inserción.
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "field_proposal_category");
            JSONHandler.setValueJsonArray(propuestaToSend, "field_proposal_category", "target_id", String.valueOf(categorySpinner.getSelectedItem()));
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "field_proposal_location");
            /*
             *No hace especificar la dirección con una dirección,
             * al enviar la posición en lat y lng el sistema las procesa
             * y devuelve una dirección formateada.
              *  */
            JSONHandler.setValueJsonArray(propuestaToSend, "field_proposal_location", "lat", String.valueOf(marker_latlng.latitude));
            JSONHandler.setValueJsonArray(propuestaToSend, "field_proposal_location", "lng", String.valueOf(marker_latlng.longitude));
            /*JSONHandler.setValueJsonArray(propuestaToSend,"field_proposal_location","lat_sin","0.64776378153254");
            JSONHandler.setValueJsonArray(propuestaToSend,"field_proposal_location","lat_cos","0.76184124549322");
            JSONHandler.setValueJsonArray(propuestaToSend,"field_proposal_location","lng_rad","-0.063883640838925");*/
            propuestaToSend.getJSONArray("field_proposal_location").getJSONObject(0).put("data", new JSONArray());

            /*
            * En la página web está marcado como un checkbox, podemos darle el mismo sentido en la aplicación
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "field_proposal_notify_council");
            JSONHandler.setValueJsonArray(propuestaToSend, "field_proposal_notify_council", "value", councilCheckBox.isChecked() ? "1" : "2");
            /*
            * Las propuestas por defecto están abiertas nada más ser añadidas.
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "field_proposal_status");
            JSONHandler.setValueJsonArray(propuestaToSend, "field_proposal_status", "target_id", "1");
            /*
            * Idioma en el que se encuentra la aplicación. (MULTIIDIOMAS!)
            * Configuración del sistema
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "langcode");
            JSONHandler.setValueJsonArray(propuestaToSend, "langcode", "value", Locale.getDefault().getLanguage());
            /*
            * Titulo de la propuesta
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "title");
            JSONHandler.setValueJsonArray(propuestaToSend, "title", "value", titleField.getText().toString());

            /*
            * Indica el tipo del recurso que se va a añadir. En este momento sólo tenemos capacidad de añadir propuestas.
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "type");
            JSONHandler.setValueJsonArray(propuestaToSend, "type", "target_id", "proposal");
            /*
            * Añadir las imagenes a la propuesta.
            * */
            JSONHandler.setJsonArrayNodeByName(propuestaToSend, "field_proposal_images");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                propuestaToSend.getJSONArray("field_proposal_images").remove(0);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (basicValidation())
            return propuestaToSend;
        else
            return null;
    }


    /**
     * Metodo privado que genera un codigo unico segun la hora y fecha del sistema
     *
     * @return photoCode
     */
    @SuppressLint("SimpleDateFormat")
    private String getCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date());
        String photoCode = "IMG_" + date;
        return photoCode;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:// CONFIRMAR
                if (!basicValidation())
                    //postProposal
                    basicValidation();//insertarPropuesta
                else
                    Toast.makeText(
                            getActivity(),
                            "Completa los campos",
                            Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_discard:// DESCARTAR
                if (!basicValidation())
                    mostrarDialogo();
                else
                    getActivity().finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Valida si los campos se han rellenado
     *
     * @return true si alguno o dos de los campos están vacios, false si ambos
     * están completos
     */
    public boolean basicValidation() {
        String titulo = titleField.getText().toString();
        String descripcion = bodyField.getText().toString();

        return (titulo.isEmpty() || descripcion.isEmpty());
    }

    /**
     * Actualiza la fecha del campo fecha
     *
     * @param ano Año
     * @param mes Mes
     * @param dia Día
     */
    public void actualizarFecha(int ano, int mes, int dia) {
        // Setear en el textview la fecha
        dateField.setText(String.format(Locale.getDefault().getLanguage(),"%d/0%d/%d",dia,mes+1,ano));
    }

    /**
     * Muestra un diálogo de confirmación
     */
    public void mostrarDialogo() {
        DialogFragment dialogo = ConfirmDialogFragment.
                createInstance(
                        getResources().
                                getString(R.string.dialog_discard_msg));
        dialogo.show(getFragmentManager(), "ConfirmDialog");
    }


    public void mostrarDialogoLocalizacion() {
        DialogFragment dialogo = ConfirmLocationFragment.
                createInstance(
                        getResources().
                                getString(R.string.dialog_location));
        dialogo.show(getFragmentManager(), "ConfirmDialogLocation");
    }


}
