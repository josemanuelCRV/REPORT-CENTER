package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.Utils.DialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Fragmento que permite al usuario insertar un nueva meta
 */
public class InsertFragment extends Fragment {
    /**
     * Etiqueta para depuración
     */
    private static final String TAG = InsertFragment.class.getSimpleName();

    /*
    Controles
    */
    EditText titulo_input;
    EditText descripcion_input;
    Spinner prioridad_spinner;
    TextView fecha_text;
    Spinner categoria_spinner;

    Button select_img_btn;

    // Mostrar el nombre de la calle en base a la localización
    TextView messageDireccion;


    // instancia SupportMapFragment para el mapa
    private SupportMapFragment mSupportMapFragment;

    private LocationManager locManager;
    private LocationListener locListener;
    final String MIAPIKEY = "AIzaSyBBGoPEmOhpfRYE7zxejlqaCHxyY75FSOw";
    // final String MIAPIKEY ="AIzaSyCtBCoNv0I0ZdvlONRzUUZ_CcABO-d7g-s";


    private Double lat;
    private Double lon;

    /*
    Instancia global del FAB
     */
    com.melnykov.fab.FloatingActionButton fabCamera;


    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/CityHacks/";
    private File file = new File(ruta_fotos);


    public InsertFragment() {


        lon = -3.7854557;
        lat = 40.4595114;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar al fragmento para contribuir en la action bar
        setHasOptionsMenu(true);


        mostrarDialogoLocalizacion();


        // INSTANCIAR EL MAPA


        // mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
        // mSupportMapFragment.getMapAsync(  );


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
        titulo_input = (EditText) v.findViewById(R.id.titulo_input);
        descripcion_input = (EditText) v.findViewById(R.id.descripcion_input);
        fecha_text = (TextView) v.findViewById(R.id.fecha_ejemplo_text);
        categoria_spinner = (Spinner) v.findViewById(R.id.categoria_spinner);


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




        // Data Picker
        fecha_text.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment picker = new DatePickerFragment();
                        picker.show(getFragmentManager(), "datePicker");

                    }
                }
        );


        // INSTANCIAR EL MAPA


        mSupportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapwhere);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.mapwhere, mSupportMapFragment).commit();
        }


        if (mSupportMapFragment != null) {
            mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    if (googleMap != null) {



                 /*       Location loc = null;



                        Double latitud= Double.valueOf(loc.getLatitude());
                        Double longitud = Double.valueOf(loc.getLongitude());

                        LatLng myLocation = new LatLng(latitud, longitud);

                        Log.i("", "Latitud: " + latitud);
                        Log.i("", "longitud: " + longitud);

                        Toast.makeText(getContext(), "Latitud es:"+latitud, Toast.LENGTH_SHORT).show();
*/

                        // -> marker_latlng recoge la latitud y longitud en formato double//
                        //  LatLng marker_latlng = new LatLng(lat, lon);
                        LatLng marker_latlng = new LatLng(lat, lon);
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
        // Remover el action button de borrar
        menu.removeItem(R.id.action_delete);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:// CONFIRMAR
                if (!camposVacios())
                    guardarMeta();
                else
                    Toast.makeText(
                            getActivity(),
                            "Completa los campos",
                            Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_discard:// DESCARTAR
                if (!camposVacios())
                    mostrarDialogo();
                else
                    getActivity().finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Guarda los cambios de una meta editada.
     * <p/>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    public void guardarMeta() {

        // Obtener valores actuales de los controles
/*
        final String titulo = titulo_input.getText().toString();
        final String descripcion = descripcion_input.getText().toString();
        final String fecha = fecha_text.getText().toString();
        final String categoria = categoria_spinner.getSelectedItem().toString();
        final String prioridad = prioridad_spinner.getSelectedItem().toString();

        HashMap<String, String> map = new HashMap<>();// Mapeo previo

        map.put("titulo", titulo);
        map.put("descripcion", descripcion);
        map.put("fechaLim", fecha);
        map.put("categoria", categoria);
        map.put("prioridad", prioridad);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d(TAG, jobject.toString());

        // Actualizar datos en el servidor
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constantes.INSERT,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }

                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );
*/

    } // FIN GUARDAR META

    /**
     * Procesa la respuesta obtenida desde el sevidor
     *
     * @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {

        try {
            // Obtener estado
            String estado = response.getString("estado");
            // Obtener mensaje
            String mensaje = response.getString("mensaje");

            switch (estado) {
                case "1":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de éxito
                    getActivity().setResult(Activity.RESULT_OK);
                    // Terminar actividad
                    getActivity().finish();
                    break;

                case "2":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de falla
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    // Terminar actividad
                    getActivity().finish();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Valida si los campos se han rellenado
     *
     * @return true si alguno o dos de los campos están vacios, false si ambos
     * están completos
     */
    public boolean camposVacios() {
        String titulo = titulo_input.getText().toString();
        String descripcion = descripcion_input.getText().toString();

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
        fecha_text.setText(ano + "-" + (mes + 1) + "-" + dia);
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
