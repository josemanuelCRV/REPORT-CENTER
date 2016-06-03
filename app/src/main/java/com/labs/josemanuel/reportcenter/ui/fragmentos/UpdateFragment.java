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

import com.labs.josemanuel.reportcenter.Controler.ConstantsTransition;
import com.labs.josemanuel.reportcenter.Controler.PropuestaHandler;
import com.labs.josemanuel.reportcenter.Infrastructure.Infrastructure;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.R;


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
     * Instancia Gson para el parsing Json
     */

    private Propuesta PropSeleecionada = Infrastructure.getPropuestaSeleccionada();


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
        bundle.putString(ConstantsTransition.EXTRA_ID, extra);
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
        idMeta = getArguments().getString(ConstantsTransition.EXTRA_ID);

        if (idMeta != null) {
            cargarDatos();
        }

        return v;
    }

    /**
     * Obtiene los datos desde el servidor
     */
    private void cargarDatos() {

        Infrastructure.getPropuestaSeleccionada();

        // título
        titulo_input.setText(PropSeleecionada.getTitle());
        // descripción
        descripcion_input.setText(PropSeleecionada.getBody().getValue());
        // fecha
        String fecha = PropuestaHandler.parseDate(PropSeleecionada.getCreated());
        fecha_text.setText(fecha);




        // Añadiendo idMeta como parámetro a la URL
     /*    String newURL = ConstantsTransition.GET_BY_ID + "?idMeta=" + idMeta;
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
        ); */
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
                break;

        }
        ;

        return super.onOptionsItemSelected(item);
    }

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