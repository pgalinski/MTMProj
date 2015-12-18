package pl.lab.galinski.mtmproj;

import android.view.View;

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

    @Bean
    PlacesApiAsyncTask task;


    SupportMapFragment map;

    private ClusterManager<PlaceDetails> clusterManager;



    @Click(R.id.lookAroundBt)
    protected void lookAroundBtClicked(View v){
        goToLookAroundActivity();
    }

    @Click(R.id.refresh)
    protected void refreshClicked(View v){


        task.exec(new LatLng(54.3321684, 18.6001779), new PlacesApiTaskListener() {
            @Override
            public void RequestSuccess(ListOfPlaces places) {
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
        clusterManager.setRenderer(new DefaultClusterRenderer<PlaceDetails>(this,map.getMap(),clusterManager));
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
