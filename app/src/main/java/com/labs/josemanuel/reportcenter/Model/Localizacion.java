package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by bufigol on 14/05/16.
 */
public class Localizacion {

    private String latitude;
    private String longitude;

    public Localizacion(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
