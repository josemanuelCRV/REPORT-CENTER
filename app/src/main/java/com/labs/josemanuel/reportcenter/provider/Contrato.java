package com.labs.josemanuel.reportcenter.provider;

/**
 * Created by Usuario on 18/05/2016.
 */

import android.net.Uri;

import java.util.UUID;

/**
 * Contrato con la estructura de la base de datos y forma de las URIs
 */
public class Contrato {

    interface ColumnasAlquiler {
        String ID_ALQUILER = "idAlquiler"; // Pk
        String NOMBRE = "nombre";
        String UBICACION = "ubicacion";
        String DESCRIPCION = "descripcion";
        String PRECIO = "precio";
        String URL_IMAGEN = "urlImagen";
    }


    // Autoridad del Content Provider
    public final static String AUTORIDAD = "com.labs.josemanuel.reportcenter";

    // Uri base
    public final static Uri URI_CONTENIDO_BASE = Uri.parse("content://" + AUTORIDAD);


    /**
     * Controlador de la tabla "alquiler"
     * Contiene la uri de contenido general del recurso y los tipos mimes necesarios para el content provider.
     * También tiene los siguientes métodos auxiliares:
     */
    public static class Alquileres implements ColumnasAlquiler {

        public static final Uri URI_CONTENIDO =
                URI_CONTENIDO_BASE.buildUpon().appendPath(RECURSO_ALQUILERES).build();

        public final static String MIME_RECURSO =
                "vnd.android.cursor.item/vnd." + AUTORIDAD + "/" + RECURSO_ALQUILERES;

        public final static String MIME_COLECCION =
                "vnd.android.cursor.dir/vnd." + AUTORIDAD + "/" + RECURSO_ALQUILERES;


        /**
         * Construye una {@link Uri} para el {@link #ID_ALQUILER} solicitado.
         * construirUriAlquiler(): Recibe el id de un alquiler para construir una uri de
         * contenido particular de la forma "alquileres/:id" por si requieres consultar cada item.
         */
        public static Uri construirUriAlquiler(String idApartamento) {
            return URI_CONTENIDO.buildUpon().appendPath(idApartamento).build();
        }

        // Genera un identificador único para un alquiler basado en el estándar UUID
        public static String generarIdAlquiler() {
            return "A-" + UUID.randomUUID();
        }

        // Extrae el identificador de una uri particular de un alquiler.
        public static String obtenerIdAlquiler(Uri uri) {
            return uri.getLastPathSegment();
        }
    }

    // Recursos
    public final static String RECURSO_ALQUILERES = "alquileres";

}