package pl.lab.galinski.mtmproj.model.google.places.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paweł Galiński
 * 18.12.2015
 */
public class Location {

    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
