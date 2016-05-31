package com.labs.josemanuel.reportcenter.ui.fragmentos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.modelo.Meta;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link // UpdateFragment.// OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UpdateFragment# // newInstance} factory method to
 * create an instance of this fragment.
 /**
 * Fragmento con formulario para actualizar la meta
 */
public class UpdateFragment extends Fragment {
    /*
    Etiqueta de depuración
     */
    private static final String TAG = UpdateFragment.class.getSimpleName();

    /*
    Controles
    */
    private EditText titulo_input;
    private EditText descripcion_input;
    private Spinner prioridad_spinner;
    private TextView fecha_text;
    private Spinner categoria_spinner;

    /*
    Valor del argumento extra
     */
    private String idMeta;

    /**
     * Es la meta obtenida como respuesta de la petición HTTP
     */
    private Meta metaOriginal;

    /**
     * Instancia Gson para el parsing Json
     */



    public UpdateFragment() {
    }

    /**
     * Crea un nuevo fragmento basado en un argumento
     *
     * @param extra Argumento de entrada
     * @return Nuevo fragmento
     */
    public static Fragment createInstance(String extra) {
        UpdateFragment detailFragment = new UpdateFragment();
        Bundle bundle = new Bundle();
        //bundle.putString(Constantes.EXTRA_ID, extra);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

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
        prioridad_spinner = (Spinner) v.findViewById(R.id.prioridad_spinner);

        fecha_text.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment picker = new DatePickerFragment();
                        picker.show(getFragmentManager(), "datePicker");

                    }
                }
        );

        // Obtener valor extra
        //idMeta = getArguments().getString(Constantes.EXTRA_ID);

        if (idMeta != null) {
            cargarDatos();
        }

        return v;
    }

    /**
     * Obtiene los datos desde el servidor
     */
    private void cargarDatos() {
        // Añadiendo idMeta como parámetro a la URL
    /*    String newURL = Constantes.GET_BY_ID + "?idMeta=" + idMeta;

        // Consultar el detalle de la meta
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        newURL,
                        null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesa la respuesta GET_BY_ID
                                procesarRespuestaGet(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }
                )
        );*/
    }

    /**
     * Procesa la respuesta de obtención obtenida desde el sevidor     *
     */
    /*private void procesarRespuestaGet(JSONObject response) {

        try {
            String estado = response.getString("estado");

            switch (estado) {
                case "1":
                    JSONObject meta = response.getJSONObject("meta");
                    // Guardar instancia
                    metaOriginal = gson.fromJson(meta.toString(), Meta.class);
                    // Setear valores de la meta
                    cargarViews(metaOriginal);
                    break;

                case "2":
                    String mensaje = response.getString("mensaje");
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
    }*/

    /**
     * Carga los datos iniciales del formulario con los
     * atributos de un objeto {@link Meta}
     *
     * @param meta Instancia
     */
    private void cargarViews(Meta meta) {
        // Seteando valores de la respuesta
        titulo_input.setText(meta.getTitulo());
        descripcion_input.setText(meta.getDescripcion());
        fecha_text.setText(meta.getFechaLim());


        // Obteniendo acceso a los array de strings para categorias y prioridades
        String[] categorias = getResources().getStringArray(R.array.entradas_categoria);
        String[] prioridades = getResources().getStringArray(R.array.entradas_prioridad);

        // Obteniendo la posición del spinner categorias
        int posicion_categoria = 0;
        for (int i = 0; i < categorias.length; i++) {
            if (categorias[i].compareTo(meta.getCategoria()) == 0) {
                posicion_categoria = i;
                break;
            }
        }

        // Setear selección del Spinner de categorías
        categoria_spinner.setSelection(posicion_categoria);

        // Obteniendo la posición del spinner de prioridades
        int posicion_prioridad = 0;
        for (int i = 0; i < prioridades.length; i++) {
            Log.d(TAG, "posición:" + i);
            if (prioridades[i].compareTo(meta.getPrioridad()) == 0) {
                posicion_prioridad = i;

                break;
            }
        }

        // Setear selección del Spinner de prioridades
        prioridad_spinner.setSelection(posicion_prioridad);
    }

    /**
     * Compara los datos actuales con aquellos que se obtuvieron
     * por primera vez en la respuesta HTTP
     *
     * @return true si los datos no han cambiado, de lo contrario false
     */
    public boolean validarCambios() {
        return metaOriginal.compararCon(obtenederDatos());
    }

    /**
     * Retorna en una nueva meta creada a partir
     * de los datos del formulario actual
     *
     * @return Instancia {@link Meta}
     */
    private Meta obtenederDatos() {

        String titulo = titulo_input.getText().toString();
        String descripcion = descripcion_input.getText().toString();
        String fecha = fecha_text.getText().toString();
        String categoria = (String) categoria_spinner.getSelectedItem();
        String prioridad = (String) prioridad_spinner.getSelectedItem();

        return new Meta("1", titulo, descripcion, fecha, categoria, prioridad);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Contribución a la AB
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:// CONFIRMAR
                /*if (!validarCambios())
                guardarMeta();
                else
                    // Terminar actividad, ya que no hay cambios
                    getActivity().finish();*/
                return true;

            case R.id.action_delete:// ELIMINAR
                mostrarDialogo(R.string.dialog_delete_msg);
                break;

            case R.id.action_discard:// DESCARTAR
                if (!validarCambios()) {
                    mostrarDialogo(R.string.dialog_discard_msg);
                } else
                    // Terminar actividad, ya que no hay cambios
                    getActivity().finish();
                break;

        }
        ;

        return super.onOptionsItemSelected(item);
    }

    /**
     * Guarda los cambios de una meta editada.
     * <p>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    /*private void guardarMeta() {

        // Obtener valores actuales de los controles
        final String titulo = titulo_input.getText().toString();
        final String descripcion = descripcion_input.getText().toString();
        final String fecha = fecha_text.getText().toString();
        final String categoria = categoria_spinner.getSelectedItem().toString();
        final String prioridad = prioridad_spinner.getSelectedItem().toString();

        HashMap<String, String> map = new HashMap<>();// Mapeo previo

        map.put("idMeta", idMeta);
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
                        Constantes.UPDATE,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                procesarRespuestaActualizar(response);
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

    }*/

    /**
     * Procesa todos las tareas para eliminar
     * una meta en la aplicación. Este método solo se usa
     * en la edición
     */
    /*public void eliminarMeta() {

        HashMap<String, String> map = new HashMap<>();// MAPEO

        map.put("idMeta", idMeta);// Identificador

        JSONObject jobject = new JSONObject(map);// Objeto Json

        // Eliminar datos en el servidor
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constantes.DELETE,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta
                                procesarRespuestaEliminar(response);

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
    }*/

    /**
     * Procesa la respuesta de eliminación obtenida desde el sevidor
     */
    private void procesarRespuestaEliminar(JSONObject response) {

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
                    getActivity().setResult(203);
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
     * Procesa la respuesta de actualización obtenida desde el sevidor
     */
    private void procesarRespuestaActualizar(JSONObject response) {

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
     * Actualiza la fecha del campo {@link //fecha_text}
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
     * Muestra un diálogo de confirmación, cuyo mensaje esta
     * basado en el parámetro identificador de Strings
     *
     * @param id Parámetro
     */
    private void mostrarDialogo(int id) {
        DialogFragment dialogo = ConfirmDialogFragment.
                createInstance(
                        getResources().
                                getString(id));
        dialogo.show(getFragmentManager(), "ConfirmDialog");
    }

}
