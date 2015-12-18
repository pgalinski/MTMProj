package pl.lab.galinski.mtmproj.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

/**
 * Created by gpawl_000 on 16.12.2015.
 */

@EActivity
public class SensorAwareActivity extends LocationAwareActivity implements SensorEventListener {

    private static final String TAG = "SensorAwareActivity";

    protected static final float Radius = 6378137;
    protected float[] magVal=null;
    protected float[] accVal=null;
    protected final float cameraB[]=new float[]{0,0,-1};
    protected float namiarM[]=new float[]{1,0,0};
    protected float namiarB[]=new float[]{0,1,0};
    protected float cameraM[]=new float[3];
    protected float[] rotFromBtoM=new float[9];
    private SensorManager sensorManager;
    private float x;
    private float y;

    private double mylat;
    private double mylng;
    private double myAlt;

    @Extra("targetLat")
    protected double targetLat;

    @Extra("targetLon")
    protected double targetLon;

    @Extra("targetAlt")
    protected double targetAlt;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Location lastKnownLocation = getLastKnownLocation();
        mylat = lastKnownLocation.getLatitude();
        mylng = lastKnownLocation.getLongitude();
        myAlt = getLastKnownLocation().getAltitude();

        /*
        float lonx = (float) (18.6039608 /180*Math.PI);
        float latx = (float)(54.3297708 /180*Math.PI);
        float lonu = (float) (18.6001779 /180*Math.PI);
        float latu = (float)(54.3321684 /180*Math.PI);
        namiarM = latlonToENU(latx,lonx,50,latu,lonu,50);
        */

    }

    private void calcENU(double lat,double lon,double h , double ulat,double ulon,double uh){
        float lonx = (float) (lat /180*Math.PI);
        float latx = (float)(lon /180*Math.PI);
        float lonu = (float) (ulat /180*Math.PI);
        float latu = (float)(ulon /180*Math.PI);
        namiarM = latlonToENU(latx,lonx,50,latu,lonu,50);
    }


    //lat i lon w radianach !!!!
    private float[] latLonToECEF(float lat,float lon,float h){
        float[] ECEF = new float[3];
        ECEF[0]=(float)((h+Radius)*Math.cos(lat)*Math.cos(lon));
        ECEF[1]=(float)((h+Radius)*Math.cos(lat)*Math.sin(lon));
        ECEF[2]=(float)((h+Radius)*Math.sin(lat));
        return ECEF;
    }

    //lat i lon w radianach
    private float[] latlonToENU(float lat,float lon,float h,
                               float ulat,float ulon,float uh){
        float[] ecefX=latLonToECEF(lat,lon,h);
        float[] ecefU=latLonToECEF(ulat,ulon,uh);
        float[] d = new float[3];
        d[0]=ecefX[0]-ecefU[0];
        d[1]=ecefX[1]-ecefU[1];
        d[2]=ecefX[2]-ecefU[2];
        float[] enu = new float[3];

        enu[0]= (float)(-Math.sin(ulon)*d[0]+Math.cos(ulon)*d[1]);
        enu[1]= (float)(-Math.cos(ulon)*Math.sin(ulat)*d[0]-Math.sin(ulon)*Math.sin(ulat)*d[1]+Math.cos(ulat)*d[2]);
        enu[2]= (float)(Math.cos(ulon)*Math.cos(ulat)*d[0]+Math.sin(ulon)*Math.cos(ulat)*d[1]+Math.sin(ulat)*d[2]);
        return enu;
    }

    double getAngle(float[]a,float b[]){
        double temp = (a[0]*b[0]+a[1]*b[1]+a[2]*b[2])/
                Math.sqrt(a[0]*a[0]+a[1]*a[1]+a[2]*a[2])/
                Math.sqrt(b[0]*b[0]+b[1]*b[1]+b[2]*b[2]);
        return Math.acos(temp);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sensor mag = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        sensorManager.registerListener(this, acc, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, mag, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case (Sensor.TYPE_ACCELEROMETER):
                accVal = sensorEvent.values.clone();
                break;
            case (Sensor.TYPE_MAGNETIC_FIELD):
                magVal = sensorEvent.values.clone();
                break;
        }


        if(magVal!=null&&accVal!=null){
            boolean success =SensorManager.getRotationMatrix(rotFromBtoM,null,accVal,magVal);

            //rotFrmBtoM*cameraB

            if (success) {
                cameraM[0]=cameraB[0]*rotFromBtoM[0]+cameraB[1]*rotFromBtoM[1]+cameraB[2]*rotFromBtoM[2];
                cameraM[1]=cameraB[0]*rotFromBtoM[0+3]+cameraB[1]*rotFromBtoM[1+3]+cameraB[2]*rotFromBtoM[2+3];
                cameraM[2]=cameraB[0]*rotFromBtoM[0+6]+cameraB[1]*rotFromBtoM[1+6]+cameraB[2]*rotFromBtoM[2+6];
                //Log.i("kbbrlog", "getAngle: " + getAngle(northM, cameraM));
                Log.d(TAG,"Angle: " + getAngle(namiarM, cameraM));

                namiarB[0]=namiarM[0]*rotFromBtoM[0]+namiarM[1]*rotFromBtoM[3]+namiarM[2]*rotFromBtoM[6];
                namiarB[1]=namiarM[0]*rotFromBtoM[1]+namiarM[1]*rotFromBtoM[4]+namiarM[2]*rotFromBtoM[7];
                namiarB[2]=namiarM[0]*rotFromBtoM[2]+namiarM[1]*rotFromBtoM[5]+namiarM[2]*rotFromBtoM[8];

                x=namiarB[0]/namiarB[2];
                y=namiarB[1]/namiarB[2];

            }


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    @Override
    public void onLocationChanged(Location location) {
        super.onLocationChanged(location);
        mylat = location.getLatitude();
        mylng = location.getLongitude();
        myAlt = location.getAltitude();
        calcENU(targetLat,targetLon,targetAlt,mylat,mylng,myAlt);
    }
}
