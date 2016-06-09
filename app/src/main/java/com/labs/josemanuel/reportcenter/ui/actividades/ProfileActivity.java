package com.labs.josemanuel.reportcenter.ui.actividades;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.labs.josemanuel.reportcenter.R;
import com.labs.josemanuel.reportcenter.ui.fragmentos.MapsActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ProfileActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_list_item);


        //extraemos el drawable en un bitmap
        Drawable originalDrawable = getResources().getDrawable(R.drawable.arnold_thumbnail);
        Bitmap originalBitmap = ((BitmapDrawable) originalDrawable).getBitmap();

        //creamos el drawable redondeado
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), originalBitmap);

        //asignamos el CornerRadius
        roundedDrawable.setCornerRadius(originalBitmap.getHeight());

        ImageView imageView = (ImageView) findViewById(R.id.userImg);

        imageView.setImageDrawable(roundedDrawable);

        ImageView geoImg = (ImageView) findViewById(R.id.ic_location);
        geoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, MapsActivity.class);
            }
        });







        ArrayList<String> titulos = new ArrayList<String>();

        titulos.add("Tu nombre de usuario");
        titulos.add("E-Mail");
        titulos.add("Nueva contraseña");
        titulos.add("Estoy en");
        titulos.add("Borrar cuenta");

        ArrayList<String> subtitulos = new ArrayList<String>();

        subtitulos.add("Manu");
        subtitulos.add("cityhacks.social@gmail.com");
        subtitulos.add("Contraseña");
        subtitulos.add("");
        subtitulos.add("Borrar cuenta");


        TextView titulo0 = (TextView) findViewById(R.id.title1);
        TextView titulo1 = (TextView) findViewById(R.id.title2);
        TextView titulo2 = (TextView) findViewById(R.id.title3);
        TextView titulo3 = (TextView) findViewById(R.id.title4);
        TextView titulo4 = (TextView) findViewById(R.id.title5);


        TextView subtitulo0 = (TextView) findViewById(R.id.subtitle1);
        TextView subtitulo1 = (TextView) findViewById(R.id.subtitle2);
        TextView subtitulo2 = (TextView) findViewById(R.id.subtitle3);
        TextView subtitulo3 = (TextView) findViewById(R.id.subtitle4);
        TextView subtitulo4 = (TextView) findViewById(R.id.subtitle5);


        titulo0.setText(titulos.get(0));
        titulo1.setText(titulos.get(1));
        titulo2.setText(titulos.get(2));
        titulo3.setText(titulos.get(3));
        titulo4.setText(titulos.get(4));



        subtitulo0.setText(subtitulos.get(0));
        subtitulo1.setText(subtitulos.get(1));
        subtitulo2.setText(subtitulos.get(2));
        subtitulo3.setText(subtitulos.get(3));
        subtitulo4.setText(subtitulos.get(4));












     /*   //Instancia del ListView
        lista = (ListView)findViewById(R.id.lista);

        //Inicializar el adaptador con la fuente de datos
        adaptador = new TareaArrayAdapter(this,DataSource.TAREAS);

        //Relacionando la lista con el adaptador
        lista.setAdapter(adaptador);

        //Estableciendo la escucha
       // lista.setOnItemClickListener(this);*/

    }







  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }*/

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            //Limpiar todos los elementos
            adaptador.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Tarea tareaActual = (Tarea)adaptador.getItem(position);
        String msg = "Elegiste la tarea:\n"+tareaActual.getNombre()+"-"+tareaActual.getValor();
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

    }*/
}
