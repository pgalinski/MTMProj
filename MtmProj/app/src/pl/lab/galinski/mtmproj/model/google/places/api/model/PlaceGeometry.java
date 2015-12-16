package pl.lab.galinski.mtmproj.model.google.places.api.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Paweł Galiński
 * 16.12.2015
 */
public class PlaceGeometry {

    @SerializedName("location")
    private LatLng location;

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}
