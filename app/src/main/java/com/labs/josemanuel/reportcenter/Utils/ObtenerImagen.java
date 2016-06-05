package com.labs.josemanuel.reportcenter.Utils;

import android.content.Context;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

/**
 * Created by Sebastian on 05/06/2016.
 */
public class ObtenerImagen {
    public static byte[] getByteArrayFromFile(Context context, Uri uri) {
        byte[] fileBytes = null;
        InputStream inStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inStream = context.getContentResolver().openInputStream(uri);
            outputStream = new ByteArrayOutputStream();
            byte[] bytesFromFile = new byte[1024 * 1024];
            int bytesRead = inStream.read(bytesFromFile);
            while (bytesRead != -1) {
                outputStream.write(bytesFromFile, 0, bytesRead);
                bytesRead = inStream.read(bytesFromFile);
            }
            fileBytes = outputStream.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inStream != null) {
                        inStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        return fileBytes;
    }
}
