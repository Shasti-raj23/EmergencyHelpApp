package com.example.accident_detection;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Locale;

public class homepage extends Fragment implements SensorEventListener {

    TextView speed, alert;
    ImageView gifImageView;
    Button btn1, chat, map;
    float currentSpeed = 0;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        String username;
        speed = view.findViewById(R.id.speed);
        alert = view.findViewById(R.id.alert);
        gifImageView = view.findViewById(R.id.gifImageView);
        Bundle args = getArguments();
        if (args != null) {
            username = args.getString("USERNAME_EXTRA");
            // Proceed with username usage
            alert.setText(username);
        } ;

        sensorManager = (SensorManager) requireActivity().getSystemService(requireContext().SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        return view;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float acc = event.values[0];
        currentSpeed = Math.abs(acc);
        speed.setText(String.format(Locale.getDefault(), "%.2f m/s", acc));

        if (acc > 5) {
            alert.setText("detected");
            gifImageView.setVisibility(View.VISIBLE);

            gifImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(requireActivity(), lauchapp.class));
                }
            });
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not needed for this example
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
