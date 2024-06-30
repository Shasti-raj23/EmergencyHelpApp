package com.example.accident_detection;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public abstract class Speeddetection extends Service implements SensorEventListener {
    static String TAG ="SpeedometerService";
    SensorManager sensorManager;
    Sensor accelerometer;
    float currentSpeed;
    @Override
    public void onCreate(){
        super.onCreate();;
        Log.d(TAG,"Service created");
        sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer =sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"Service Destroyed");
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            float acc =event.values[0];
            currentSpeed=Math.abs(acc);
            Intent speedIntent = new Intent("speed update");
            speedIntent.putExtra("current_speed",currentSpeed);
            sendBroadcast(speedIntent);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor,int accuracy){

    }
}
