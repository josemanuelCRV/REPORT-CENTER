package com.labs.josemanuel.reportcenter.Model;

/**
 * Clase que representa el cuerpo de una propuesta. En esta clase se guarda 
 * el fromato del texto, y el propio texto vinculado a una propuesta.
 * Created by bufigol on 14/05/16.
 */
public class Body {
    private String value;
    private String format;
    private String summary;

    public Body(String value, String format, String summary) {
        this.value = value;
        this.format = format;
        this.summary = summary;
    }

    public Body(String value, String format){
        this.value = value;
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isSummary() {
        return Boolean.getBoolean(summary);
    }

    public void setSummary(boolean summary) {
        this.summary = String.valueOf(summary);
    }

    @Override
    public String toString() {
        return "Body{" +
                "value='" + value + '\'' +
                ", format='" + format + '\'' +
                ", summary=" + summary +
                '}';
    }
}
