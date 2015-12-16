package pl.lab.galinski.mtmproj.model.google.places.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paweł Galiński
 * 16.12.2015
 */
public class AlternativeIdentifier {

    @SerializedName("place_id")
    private String placeId;

    @SerializedName("scope")
    private String scope;
}
