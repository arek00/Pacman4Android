package com.arek00.pacman.Inputs.Handlers.ConcreteHandlers;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import com.arek00.pacman.Config.AccelerometerConfig;
import com.arek00.pacman.Inputs.Handlers.InputHandler;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;
import com.arek00.pacman.Utils.Validators.NumberValidator;

/**
 * Basic handler of accelerometer data. It listens to accelerometer statement changes and return measurements from
 * X and Y axis.
 */
public class AccelerometerHandler implements InputHandler, SensorEventListener {

    private Context context;
    private int accelerometerSensitive;
    private Sensor accelerometer;

    private PointF accelerometerData = new PointF(0, 0);

    /**
     * Set context to activity in order to access to system services and set interval between reading accelerometer states.
     *
     * @param context
     * @param accelerometerSensitive - Sensitive in miliseconds
     */
    public AccelerometerHandler(Context context, int accelerometerSensitive) {
        NullPointerValidator.validate(context);
        NumberValidator.checkNegativeNumber(accelerometerSensitive);
        NumberValidator.checkNumberIsZero(accelerometerSensitive);

        this.context = context;
        this.accelerometerSensitive = accelerometerSensitive;
        registeredSensor(context, accelerometerSensitive);

    }

    private void registeredSensor(Context context, int sensitive) {
        SensorManager manager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        manager.registerListener(this, accelerometer, sensitive);
    }

    /**
     * Get most latest state of accelerometer.
     *
     * @return
     */
    public PointF getInput() {
        return accelerometerData;
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        Point axis = AccelerometerConfig.getPlayerAxis();
        Point flip = AccelerometerConfig.getAxisFlip();
        PointF offset = AccelerometerConfig.getCalibratedOffset();

        float x = (sensorEvent.values[axis.x] + offset.x) * flip.x;
        float y = (sensorEvent.values[axis.y] + offset.y) * flip.y;

        accelerometerData.set(x, y);

    }

    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
