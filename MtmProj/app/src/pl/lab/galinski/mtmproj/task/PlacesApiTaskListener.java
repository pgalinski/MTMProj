package pl.lab.galinski.mtmproj.task;

import pl.lab.galinski.mtmproj.model.google.places.api.model.ListOfPlaces;

/**
 * Created by Paweł Galiński
 * 18.12.2015
 */
public interface PlacesApiTaskListener {

    public void RequestSuccess(ListOfPlaces places);
    public void RequestFailed(Exception exception);

}
