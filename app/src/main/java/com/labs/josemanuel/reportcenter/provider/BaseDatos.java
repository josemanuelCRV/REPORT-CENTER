package com.labs.josemanuel.reportcenter.provider;

/**
 * Created by Usuario on 18/05/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.labs.josemanuel.reportcenter.provider.Contrato.Alquileres;

/**
 * Clase auxiliar para controlar accesos a la base de datos SQLite
 */
public class BaseDatos extends SQLiteOpenHelper {

    static final int VERSION = 1;

    static final String NOMBRE_BD = "alquileres.db";


    interface Tablas {
        String APARTAMENTO = "alquiler";
    }

    public BaseDatos(Context context) {
        super(context, NOMBRE_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + Tablas.APARTAMENTO + "("
                        + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + Alquileres.ID_ALQUILER + " TEXT UNIQUE NOT NULL,"
                        + Alquileres.NOMBRE + " TEXT NOT NULL,"
                        + Alquileres.UBICACION + " TEXT NOT NULL,"
                        + Alquileres.DESCRIPCION + " TEXT NOT NULL,"
                        + Alquileres.PRECIO + " REAL NOT NULL,"
                        + Alquileres.URL_IMAGEN + " TEXT NOT NULL)");

        // Registro ejemplo #1
        ContentValues valores = new ContentValues();
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Bicycle uses counter");
        valores.put(Alquileres.UBICACION, "San Francisco");
        valores.put(Alquileres.DESCRIPCION, "I LOVE this idea @sfmta_muni. You can feel you are part of movement and you fight against climate crisis.");
        valores.put(Alquileres.PRECIO, "6");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/391/xvga_pIk8ULlNGX.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto1.jpg

        // Registro ejemplo #2
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Me encanta este alcorque en Lavapies");
        valores.put(Alquileres.UBICACION, "Madrid");
        valores.put(Alquileres.DESCRIPCION, "Este #alcorque está a la altura del número 3 de la calle Miguel Servet...");
        valores.put(Alquileres.PRECIO, "26");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/390/xvga_ZquIXWjDjh.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);

        //http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto2.jpg
        // http://app.letshackity.com/files/image/attachment/387/xvga_8pS1LKqCm9.jpg


        // Registro ejemplo #3
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "¿Esto es arreglar un banco?");
        valores.put(Alquileres.UBICACION, "Madrid");
        valores.put(Alquileres.DESCRIPCION, "Se realizó esta propuesta el 10.04.2016 aviso número 2301290");
        valores.put(Alquileres.PRECIO, "300");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/388/xvga_99BT8G7lnq.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto3.jpg

        // Registro ejemplo #4
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Bache en Sierra Contraviesa 41");
        valores.put(Alquileres.UBICACION, "Madrid");
        valores.put(Alquileres.DESCRIPCION, "Hay un bache en mitad de la acera que se estanca de agua..");
        valores.put(Alquileres.PRECIO, "325");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/387/xvga_8pS1LKqCm9.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto4.jpg


        // Registro ejemplo #5
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Publicidad vertical ilegal");
        valores.put(Alquileres.UBICACION, "Madrid");
        valores.put(Alquileres.DESCRIPCION, "Se prohíbe expresamente..La fijación de publicidad o propaganda mediante carteles, pegatinas, etiquetas");
        valores.put(Alquileres.PRECIO, "200");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/386/xvga_9ikmt8EC9r.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto5.jpg
        // http://www.letshackity.com/images/projects/living1.jpg


        // Registro ejemplo #6
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Hundimiento progresivo de pasarela peatonal.");
        valores.put(Alquileres.UBICACION, "Madrid");
        valores.put(Alquileres.DESCRIPCION, "Se aprecia en diversas partes hundimientos del suelo respecto al nivel original.");
        valores.put(Alquileres.PRECIO, "55");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/385/xvga_EuXYHBap9X.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto6.jpg
        // http://app.letshackity.com/files/image/attachment/385/xvga_EuXYHBap9X.jpg


        // Registro ejemplo #7
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Bordillos hundidos y fracturados");
        valores.put(Alquileres.UBICACION, "Madrid");
        valores.put(Alquileres.DESCRIPCION, "Bordillos hundidos o volteados, fractura y levantamiento de firme.");
        valores.put(Alquileres.PRECIO, "540");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/384/xvga_nRZ41ZtTo9.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto7.jpg


        // Registro ejemplo #8
        valores.put(Alquileres.ID_ALQUILER, Alquileres.generarIdAlquiler());
        valores.put(Alquileres.NOMBRE, "Broken sidewalk");
        valores.put(Alquileres.UBICACION, "San Francisco");
        valores.put(Alquileres.DESCRIPCION, "I love this way of indicating that the street is broken is very good.");
        valores.put(Alquileres.PRECIO, "310");
        valores.put(Alquileres.URL_IMAGEN, "http://app.letshackity.com/files/image/attachment/383/xvga_BEYb15kEnL.jpg");
        db.insertOrThrow(Tablas.APARTAMENTO, null, valores);
        // http://www.hermosaprogramacion.com/wp-content/uploads/2016/01/apto8.jpg


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Tablas.APARTAMENTO);
        } catch (SQLiteException e) {
            // Manejo de excepciones
        }
        onCreate(db);
    }
}
