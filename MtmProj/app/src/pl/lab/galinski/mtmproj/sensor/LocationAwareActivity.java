package pl.lab.galinski.mtmproj.sensor;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

/**
 * Created by Paweł Galiński
 * 18.12.2015
 */
public class LocationAwareActivity extends FragmentActivity implements LocationListener {

    private final String TAG = "LocationAwareActivity";
    private final int UPDATE_MIN_TIME = 1000; //1sec
    private final int UPDATE_MIN_DIST = 5; //5m
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, UPDATE_MIN_TIME, UPDATE_MIN_DIST, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, UPDATE_MIN_TIME, UPDATE_MIN_DIST, this);
    }

    public Location getLastKnownLocation(){
        Location gpsLast = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(gpsLast!=null){
            return gpsLast;
        }
        return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    protected void onDestroy() {
        locationManager.removeUpdates(this);
        super.onDestroy();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG,"LocationChanged" + location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.d(TAG,"StatusChanged" + s);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.d(TAG,"ProviderEnabled" + s);
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.d(TAG,"ProviderDisabled" + s);
    }
}
