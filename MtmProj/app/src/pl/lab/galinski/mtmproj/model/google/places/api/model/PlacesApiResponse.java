package pl.lab.galinski.mtmproj.model.google.places.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Paweł Galiński
 * 18.12.2015
 */
public class PlacesApiResponse {



    @SerializedName("results")
    private ListOfPlaces results;


    public ListOfPlaces getResults() {
        return results;
    }

    public void setResults(ListOfPlaces results) {
        this.results = results;
    }
}
