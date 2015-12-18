package pl.lab.galinski.mtmproj.gui;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * Created by Paweł Galiński
 * 17.12.2015
 */
class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera camera;

    CameraPreview(Context context) {
        super(context);
        mHolder = getHolder();

        mHolder.addCallback(this);

        //musi być mimo że jest w dokumentacji jest deprecated
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public CameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHolder = getHolder();

        mHolder.addCallback(this);

        //musi być mimo że jest w dokumentacji jest deprecated
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {

        camera = Camera.open();
        camera.setDisplayOrientation(90);
        Camera.Parameters cp = camera.getParameters();
        cp.setPreviewSize(640,480);
        camera.setParameters(cp);
        //Toast.makeText(this.getContext(), "surfaceCreated", Toast.LENGTH_LONG).show();
        try {
            camera.setPreviewDisplay(holder);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        //Toast.makeText(this.getContext(), "surfaceDestroyed", Toast.LENGTH_LONG).show();
        camera = null;

    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {


        camera.startPreview();
    }
}
