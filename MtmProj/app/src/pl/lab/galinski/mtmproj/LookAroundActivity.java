package pl.lab.galinski.mtmproj;

import android.hardware.SensorEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import pl.lab.galinski.mtmproj.gui.CamerOverlay;
import pl.lab.galinski.mtmproj.sensor.SensorAwareActivity;

/**
 * Created by Paweł Galiński
 * 16.12.2015
 */

@EActivity(R.layout.activity_look_around)
public class LookAroundActivity extends SensorAwareActivity {


    @ViewById(R.id.overlay)
    protected CamerOverlay camerOverlay;

    @AfterViews
    protected void initGUI(){

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        super.onSensorChanged(sensorEvent);
        camerOverlay.setX(getX());
        camerOverlay.setY(getY());
        camerOverlay.setDistance((float)getDistance());
        camerOverlay.invalidate();
    }
}
