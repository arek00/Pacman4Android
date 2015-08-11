package com.arek00.pacman.Graphics.Views.ConcreteViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import com.arek00.pacman.Inputs.Handlers.ConcreteHandlers.AccelerometerHandler;

/**
 * View that display accelerometer state.
 */
public class CalibrationView extends View {

    private AccelerometerHandler accelerometer;
    private PointF viewSize = new PointF(0,0);

    public CalibrationView(Context context) {
        super(context);
        accelerometer = new AccelerometerHandler(context, 100);
    }

    private void calculateViewSize() {
        float height = getMeasuredHeight();
        float width = getMeasuredWidth();
        this.viewSize.set(width, height);
    }

    public void onDraw(Canvas canvas) {
        invalidate();
        calculateViewSize();
        drawBackCircle(canvas);
        drawLines(canvas);
        drawPointer(canvas, 8f);
    }


    private void drawBackCircle(Canvas canvas) {
        PointF centerPoint = new PointF(viewSize.x / 2, viewSize.y / 2);
        float radius = calculateRadius(centerPoint);

        Paint backCircle = new Paint();
        backCircle.setColor(Color.WHITE);
        canvas.drawCircle(centerPoint.x, centerPoint.y, radius, backCircle);
    }

    private void drawLines(Canvas canvas) {
        Paint linePaint = new Paint();
        linePaint.setColor(Color.BLACK);

        canvas.drawLine(viewSize.x / 2, 0, viewSize.x / 2, viewSize.y, linePaint);
        canvas.drawLine(0, viewSize.y / 2, viewSize.x, viewSize.y / 2, linePaint);
    }

    private void drawPointer(Canvas canvas, float pointerRadius) {
        PointF forces = accelerometer.getInput();
        PointF centerPoint = new PointF(viewSize.x / 2, viewSize.y / 2);

        float radius = calculateRadius(centerPoint);
        float unit = radius / 10f;

        float pointerX = centerPoint.x + forces.x * unit;
        float pointerY = centerPoint.y + forces.y * unit;

        Paint pointerPaint = new Paint();
        pointerPaint.setColor(Color.RED);

        canvas.drawCircle(pointerX, pointerY, pointerRadius, pointerPaint);
    }


    private float calculateRadius(PointF centerPoint) {
        if (centerPoint.x > centerPoint.y) {
            return centerPoint.x;
        } else {
            return centerPoint.y;
        }

    }

}
