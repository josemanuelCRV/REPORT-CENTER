package com.labs.josemanuel.reportcenter.Utils;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Miguel on 5/26/2016.
 */
public class FileHandler {
    Context mContext;

    public FileHandler(Context context){
        mContext=context;
    }


    @NonNull
    public StringBuilder readAuthTokenFromFile() {
        File file = mContext.getFileStreamPath("clave.txt");
        String linea ="";
        StringBuilder stringBuilder =  new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while((linea=bufferedReader.readLine())!=null);
            {
                stringBuilder.append(linea);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
