package com.boomapp.app.objects;

/**
 * @author mohsen
 * @since 11/4/2015.
 */
public class MarkerObject {
    private double latitude;
    private double longitude;

    public MarkerObject() {
    }

    public MarkerObject(double latitude, double longitude) {
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
}
