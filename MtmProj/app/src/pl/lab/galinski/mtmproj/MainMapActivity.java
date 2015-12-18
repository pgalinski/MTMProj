package pl.lab.galinski.mtmproj;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import pl.lab.galinski.mtmproj.model.google.places.api.model.ListOfPlaces;
import pl.lab.galinski.mtmproj.model.google.places.api.model.PlaceDetails;
import pl.lab.galinski.mtmproj.sensor.LocationAwareActivity;
import pl.lab.galinski.mtmproj.task.PlacesApiAsyncTask;
import pl.lab.galinski.mtmproj.task.PlacesApiTaskListener;

@EActivity(R.layout.activity_main_map)
public class MainMapActivity extends LocationAwareActivity{
    private static final String TAG = "MainMapActivity";

    private ListOfPlaces placesList;
    private PlaceDetails placeToshow;

    @Bean
    PlacesApiAsyncTask task;


    SupportMapFragment map;

    private ClusterManager<PlaceDetails> clusterManager;


    public PlaceDetails getPlaceToshow() {
        return placeToshow;
    }

    @Click(R.id.lookAroundBt)
    protected void lookAroundBtClicked(View v){
        goToLookAroundActivity();
    }

    @Click(R.id.refresh)
    protected void refreshClicked(View v){


        task.exec(new LatLng(54.3321684, 18.6001779), new PlacesApiTaskListener() {
            @Override
            public void RequestSuccess(ListOfPlaces places) {
                placesList = places;
                clusterManager.clearItems();
                clusterManager.addItems(places);
                clusterManager.cluster();
            }

            @Override
            public void RequestFailed(Exception exception) {

            }
        });
    }

    private void goToLookAroundActivity() {
        LookAroundActivity_.intent(this).start();
    }

    @AfterViews
    protected void initGUI(){
        map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        clusterManager = new ClusterManager<PlaceDetails>(this,map.getMap());
        map.getMap().setOnCameraChangeListener(clusterManager);
        map.getMap().setOnMarkerClickListener(clusterManager);
        DefaultClusterRenderer<PlaceDetails> renderer = new DefaultClusterRenderer<>(this, map.getMap(), clusterManager);
        clusterManager.setRenderer(renderer);
        clusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<PlaceDetails>() {
            @Override
            public boolean onClusterItemClick(PlaceDetails details) {
                placeToshow = details;
                new PlaceDetailsDialogFragment().show(getSupportFragmentManager(),"dialog");
                return true;
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public static class PlaceDetailsDialogFragment extends DialogFragment {

        MainMapActivity activity;


        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            activity = (MainMapActivity) getActivity();


        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.place_details_fragment_dialog, container, false);
            final PlaceDetails placeDetails = activity.getPlaceToshow();

            TextView name = (TextView) view.findViewById(R.id.name);
            name.setText(placeDetails.getName());


            ImageView icon = (ImageView) view.findViewById(R.id.image);


            Button pointBt = (Button) view.findViewById(R.id.navigate);
            pointBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LookAroundActivity_
                            .intent(activity)
                            .targetLat(placeDetails.getGeometry().getLocation().getLat())
                            .targetLon(placeDetails.getGeometry().getLocation().getLng())
                            .targetAlt(50)
                            .start();
                }
            });

            return view;
        }
    }

}
