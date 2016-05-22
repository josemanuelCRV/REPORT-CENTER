package com.labs.josemanuel.reportcenter.Model;

/**
 * Created by bufigol on 14/05/16.
 */
public class Localizacion {

    private String latitude;
    private String longitude;
    private String lat_sin;
    private String lat_cos;
    private String lng_rad;

    public Localizacion(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Localizacion(String latitude, String longitude, String lat_sin, String lat_cos, String lng_rad) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.lat_sin = lat_sin;
        this.lat_cos = lat_cos;
        this.lng_rad = lng_rad;
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

    public String getLat_sin() {
        return lat_sin;
    }

    public void setLat_sin(String lat_sin) {
        this.lat_sin = lat_sin;
    }

    public String getLat_cos() {
        return lat_cos;
    }

    public void setLat_cos(String lat_cos) {
        this.lat_cos = lat_cos;
    }

    public String getLng_rad() {
        return lng_rad;
    }

    public void setLng_rad(String lng_rad) {
        this.lng_rad = lng_rad;
    }

    @Override
    public String toString() {
        return "Localizacion{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
