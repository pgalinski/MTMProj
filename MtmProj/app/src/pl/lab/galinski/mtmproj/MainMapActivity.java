package pl.lab.galinski.mtmproj;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main_map)
public class MainMapActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "MainMapActivity";
    private GoogleApiClient mGoogleApiClient;

    @Click(R.id.lookAroundBt)
    protected void lookAroundBtClicked(View v){
        goToLookAroundActivity();
    }

    private void goToLookAroundActivity() {
        LookAroundActivity_.intent(this).start();
    }

    @AfterViews
    protected void initGUI(){
        setupPlacesApiClient();
    }

    private void setupPlacesApiClient() {
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onDestroy() {
        mGoogleApiClient.disconnect();
        super.onDestroy();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG,"Connected!");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG,"Connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG,"Connection failed!!");
    }
}
