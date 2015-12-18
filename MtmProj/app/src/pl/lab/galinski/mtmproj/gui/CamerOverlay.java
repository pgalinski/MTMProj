package pl.lab.galinski.mtmproj.gui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Paweł Galiński
 * 17.12.2015
 */
public class CamerOverlay extends View {
    private final String TAG = "Overlay";

    private float x;
    private float y;
    private float d;

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public CamerOverlay(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    public CamerOverlay(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Paint p = new Paint();
        p.setARGB(255, 255, 0, 0);




        float sy = canvas.getHeight();
        float sx = canvas.getWidth();
        float cx = -x * sx + sx / 2;
        float cy = y * sy + sy / 2;
        canvas.drawCircle(cx, cy, 30, p);

    }
}
