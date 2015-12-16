package pl.lab.galinski.mtmproj.model.google.places.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Paweł Galiński
 * 16.12.2015
 */
public class PlaceDetails {
    @SerializedName("address_components")
    private ArrayList<AddressComponent> addressComponents;

    @SerializedName("formatted_address")
    private String formattedAddress;

    @SerializedName("geometry")
    private PlaceGeometry geometry;

    @SerializedName("icon")
    private String iconUrl;

    @SerializedName("id")
    private String id;

    @SerializedName("alt_ids")
    private ArrayList<AlternativeIdentifier> altIds;

    @SerializedName("rating")
    private Float rating;

    @SerializedName("reference")
    private String reference;


    public ArrayList<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(ArrayList<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public PlaceGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(PlaceGeometry geometry) {
        this.geometry = geometry;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<AlternativeIdentifier> getAltIds() {
        return altIds;
    }

    public void setAltIds(ArrayList<AlternativeIdentifier> altIds) {
        this.altIds = altIds;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
