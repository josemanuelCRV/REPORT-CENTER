package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by bufigol on 14/05/16.
 */
public class Localizacion {

    private double latitude;
    private double longitude;

    public Localizacion(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
