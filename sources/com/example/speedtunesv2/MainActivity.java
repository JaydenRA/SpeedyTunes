package com.example.speedtunesv2;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private AudioManager audioManager;
    /* access modifiers changed from: private */
    public LocationListener locationListener;
    /* access modifiers changed from: private */
    public LocationManager locationManager;
    private Location previousLocation;
    private long previousTime;
    private TextView speedText;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.speedText = (TextView) findViewById(R.id.speed_text);
        this.locationManager = (LocationManager) getSystemService("location");
        this.locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                MainActivity.this.calculateSpeed(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };
        this.audioManager = (AudioManager) getSystemService("audio");
        ((Button) findViewById(R.id.start_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.locationManager.requestLocationUpdates("gps", 0, 0.0f, MainActivity.this.locationListener);
            }
        });
        ((Button) findViewById(R.id.stop_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.locationManager.removeUpdates(MainActivity.this.locationListener);
            }
        });
    }

    /* access modifiers changed from: private */
    public void calculateSpeed(Location currentLocation) {
        Location location = this.previousLocation;
        if (location != null) {
            float speed = (float) (((double) (location.distanceTo(currentLocation) / ((float) (currentLocation.getTime() - this.previousTime)))) * 2.23694d * 1000.0d);
            this.speedText.setText("Current Speed: " + String.format("%.2f", new Object[]{Float.valueOf(speed)}) + "mph");
            this.audioManager.setStreamVolume(3, (int) (3.0f * speed), 0);
        }
        this.previousLocation = currentLocation;
        this.previousTime = currentLocation.getTime();
    }
}
