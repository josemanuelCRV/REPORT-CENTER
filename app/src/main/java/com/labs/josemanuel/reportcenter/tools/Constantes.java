package com.labs.josemanuel.reportcenter.tools;

/**
 * Clase que contiene los códigos usados en "I Wish" para
 * mantener la integridad en las interacciones entre actividades
 * y fragmentos
 */
public class Constantes {
    /**
     * Transición Home -> Detalle
     */
    public static final int CODIGO_DETALLE = 100;

    /**
     * Transición Detalle -> Actualización
     */
    public static final int CODIGO_ACTUALIZACION = 101;
    /**
     * Puerto que utilizas para la conexión.
     * Dejalo en blanco si no has configurado esta carácteristica.
     */
    private static final String PUERTO_HOST = "daniesco.esy.es";
    /**
     * Dirección IP de genymotion o AVD no lo necesito
     */
    //private static final String IP = "10.0.3.2";
    /**
     * URLs del Web Service
     */

    public static final String GET = "http://"  + PUERTO_HOST + "/obtener_metas.php";
    public static final String GET_BY_ID = "http://" + PUERTO_HOST + "/obtener_meta_por_id.php";
    public static final String UPDATE = "http://"  + PUERTO_HOST + "/actualizar_meta.php";
    public static final String DELETE = "http://"  + PUERTO_HOST + "/borrar_meta.php";
    public static final String INSERT = "http://"  + PUERTO_HOST + "/insertar_meta.php";

    /**
     * Clave para el valor extra que representa al identificador de una meta
     */
    public static final String EXTRA_ID = "IDEXTRA";

}
