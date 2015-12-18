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
    private float[] dane=null;

    private float x;
    private float y;

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

    public void setDane(float[] dane) {
        this.dane = dane;
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
        canvas.drawCircle(-x*sx+sx/2,y*sy+sy/2,30,p);


        if(dane!=null)
        {
            canvas.drawLine(0, 0, 100*dane[0], 100*dane[0], p);
            p.setStyle(Paint.Style.STROKE);
            canvas.drawRect(100, 100, 200, 200, p);

        }
    }
}
