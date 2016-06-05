package com.labs.josemanuel.reportcenter.ui.actividades;

import android.content.Context;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.labs.josemanuel.reportcenter.R;

import java.util.List;

public class TareaArrayAdapter extends ArrayAdapter<Tarea> {

    public TareaArrayAdapter(Context context, List<Tarea> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        //Obteniendo una instancia del inflater
        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con image_list_view.xml
            listItemView = inflater.inflate(
                    R.layout.image_list_item,
                    parent,
                    false);
        }

        //Obteniendo instancias de los elementos
        TextView titulo = (TextView)listItemView.findViewById(R.id.text1);
        final TextView subtitulo = (TextView)listItemView.findViewById(R.id.text2);
        ImageView editar= (ImageView) listItemView.findViewById(R.id.fab);
        final EditText subtitulo2 = (EditText)listItemView.findViewById(R.id.textInput);
        ImageView profImage =(ImageView)listItemView.findViewById(R.id.profImage);
        ImageView eliminar= (ImageView) listItemView.findViewById(R.id.fab2);


        //Obteniendo instancia de la Tarea en la posición actual
        Tarea item = getItem(position);
        if(position!=0 && position != 5){
            editar.setVisibility(View.VISIBLE);
        }
        if(position == 2){
            subtitulo.setTransformationMethod(new PasswordTransformationMethod());
            subtitulo2.setTransformationMethod(new PasswordTransformationMethod());
        }
        if(position == 3){
            profImage.setVisibility(View.VISIBLE);
        }
        if(position == 5){
            eliminar.setVisibility(View.VISIBLE);
        }

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subtitulo.setVisibility(View.INVISIBLE);
                subtitulo2.setVisibility(View.VISIBLE);
            }
        });

        titulo.setText(item.getNombre());
        subtitulo.setText(item.getValor());
        editar.setImageResource(item.getEditBtn());

        //Devolver al ListView la fila creada
        return listItemView;

    }


}