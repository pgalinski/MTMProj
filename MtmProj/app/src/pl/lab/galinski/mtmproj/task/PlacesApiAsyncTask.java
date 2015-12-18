package pl.lab.galinski.mtmproj.task;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pl.lab.galinski.mtmproj.R;
import pl.lab.galinski.mtmproj.model.google.places.api.model.ListOfPlaces;
import pl.lab.galinski.mtmproj.model.google.places.api.model.PlacesApiResponse;

/**
 * Created by Paweł Galiński
 * 18.12.2015
 */

@EBean
public class PlacesApiAsyncTask implements PlacesApiTaskListener{

    private final String TAG = "PlacesApiAsyncTask";
    private Context contex;
    private PlacesApiTaskListener listener;



    public PlacesApiAsyncTask(Context contex) {
        this.contex = contex;

        listener = new PlacesApiTaskListener() {
            @Override
            public void RequestSuccess(ListOfPlaces string) {

            }

            @Override
            public void RequestFailed(Exception exception) {
            }
        };
    }

    public void exec(LatLng userLocation, PlacesApiTaskListener listener){
        this.listener = listener;
        doTheWork(userLocation.latitude,userLocation.longitude);
    }


    @Background(serial = "PlacesApiRequest")
    protected void doTheWork(double latitude, double longitude){
        getPlaces("https://maps.googleapis.com/maps/api/place/search/json?location="
                +latitude+","+longitude+"&radius=1000&sensor=true&key="+ contex.getString(R.string.Google_API_Key)
        );
    }


    @Background(serial = "PlacesApiRequest")
    protected void getPlaces(String url) {

        try {
            RestTemplate template = new RestTemplate();
            template.getMessageConverters().add(new StringHttpMessageConverter());
            template.getMessageConverters().add(new GsonHttpMessageConverter());

            PlacesApiResponse response = template.getForObject(url, PlacesApiResponse.class);
            RequestSuccess(response.getResults());
        } catch (Exception e) {
            RequestFailed(e);

        }
    }


    @Override
    @UiThread
    public void RequestSuccess(ListOfPlaces places) {
        Log.d(TAG,"places received");
        listener.RequestSuccess(places);
    }

    @Override
    @UiThread
    public void RequestFailed(Exception exception) {
        Log.e(TAG,"places request failed",exception);
        listener.RequestFailed(exception);

    }
}
