package pl.lab.galinski.mtmproj.model.google.places.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paweł Galiński
 * 16.12.2015
 */
public class PlaceGeometry {

    @SerializedName("location")
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
