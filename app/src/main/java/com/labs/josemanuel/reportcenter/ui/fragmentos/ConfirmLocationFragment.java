package com.labs.josemanuel.reportcenter.ui.fragmentos;


import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.labs.josemanuel.reportcenter.R;


public class ConfirmLocationFragment extends DialogFragment {

    /**
     * Etiqueta del valor extra
     */
    private static final String EXTRA_NOMBRE = "NOMBRE";

    /**
     * Crea una nueva instancia del diálogo con un parámetro extra
     * que personalice el mensaje que aparece en él.
     *
     * @param extra Parámetro
     * @return Nuevo Diálogo
     */
    public static DialogFragment createInstance(String extra) {
        DialogFragment fragmentLocation = new ConfirmLocationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NOMBRE, extra);
        fragmentLocation.setArguments(bundle);
        return fragmentLocation;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Construcción del diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage(getArguments().getString(EXTRA_NOMBRE))
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Enviar evento de aceptar a la actividad
                        listener.onDialogPositiveClickLocation(ConfirmLocationFragment.this);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Enviar evento de cancelar a la actividad
                        listener.onDialogNegativeClickLocation(ConfirmLocationFragment.this);
                    }
                });
        return builder.create();
    }

    /**
     * Cuerpo de la interfaz de comunicación.
     * Ambos métodos deben ser implementados en la actividad
     * huesped para el manejo de los botones del dialogo.
     */
    public interface ConfirmDialogListenerLocation {
        public void onDialogPositiveClickLocation(DialogFragment dialogLocation);

        public void onDialogNegativeClickLocation(DialogFragment dialogLocation);

    }

    // Interfaz para la comunicación de eventos con la actividad
    ConfirmDialogListenerLocation listener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the ConfirmDialogListenerLocation so we can send events to the host
            listener = (ConfirmDialogListenerLocation) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " Debes implementar ConfirmDialogListenerLocation");
        }
    }

}
