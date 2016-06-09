
package com.labs.josemanuel.reportcenter.Controler;

import com.labs.josemanuel.reportcenter.Model.Categoria;
import com.labs.josemanuel.reportcenter.Model.Propuesta;
import com.labs.josemanuel.reportcenter.Model.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Usuario on 25/05/2016.
 */
public class PropuestaHandler {
    Propuesta[] mPropuestas=null;
    static SimpleDateFormat  formatter = new SimpleDateFormat("E, M d, yyyy - h:mm a");
    static SimpleDateFormat TimeFromDate = new SimpleDateFormat("d 'días y '  h 'horas'");
    static Date date=null;
    public PropuestaHandler(Propuesta[] propuestas){
        mPropuestas= propuestas;
    }

    public static String parseDate(String dateInMilliseconds){
        /**
         * Para realizar correctamente la conversión de milisegundos provenientes del servidor,
         * es necesario multiplicarlo por 1000 para el correcto funcionamiento del constructor de Date.
         * https://en.wikipedia.org/wiki/Epoch_(reference_date)#Notable_epoch_dates_in_computing
         * */
        date = new Date(Long.valueOf(dateInMilliseconds) * 1000L);

        //Formato elegido por HackityApp -->        Martes, Abril 19, 2016 - 08:14

        formatter.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
        return formatter.format(date);
    }
    //http://stackoverflow.com/questions/11969383/convert-milliseconds-string-to-date-string Thomas Tran
    public static String getTimeFromToday(String from){
        Date today= new Date();

        String now = String.valueOf(today.getTime()/1000L);
        String diff = String.valueOf(Long.parseLong(now)-Long.parseLong(from));

        date = new Date(Long.valueOf(diff)*1000L);

        return TimeFromDate.format(date);
    }
    public static String getColorKey(Status status){
        String url = status.getUrl();

        String key = url.replaceAll("[^0-9]", "");

        return key;
    }
    public static String getColorKey(Categoria categoria){
        String url = categoria.getUrl();

        String key = url.replaceAll("[^0-9]", "");

        return key;
    }

    public static String getEpochFromDate(String dateInput){
        SimpleDateFormat simpleDateFormatdf = new SimpleDateFormat("dd/mm/yyyy");
        date = null;
        try {
            date = simpleDateFormatdf.parse(dateInput);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long epoch = date.getTime();
        return String.valueOf(epoch);
    }
}