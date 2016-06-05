package com.labs.josemanuel.reportcenter.ui.actividades;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.labs.josemanuel.reportcenter.R;


public class ProfileActivity extends Activity {

    ListView lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Instancia del ListView
        lista = (ListView)findViewById(R.id.lista);

        //Inicializar el adaptador con la fuente de datos
        adaptador = new TareaArrayAdapter(this,DataSource.TAREAS);

        //Relacionando la lista con el adaptador
        lista.setAdapter(adaptador);

        //Estableciendo la escucha
       // lista.setOnItemClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear) {
            //Limpiar todos los elementos
            adaptador.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Tarea tareaActual = (Tarea)adaptador.getItem(position);
        String msg = "Elegiste la tarea:\n"+tareaActual.getNombre()+"-"+tareaActual.getValor();
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();

    }*/
}
