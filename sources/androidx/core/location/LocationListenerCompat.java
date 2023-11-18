package androidx.core.location;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import java.util.List;

public interface LocationListenerCompat extends LocationListener {
    void onStatusChanged(String provider, int status, Bundle extras) {
    }

    void onProviderEnabled(String provider) {
    }

    void onProviderDisabled(String provider) {
    }

    void onLocationChanged(List<Location> locations) {
        int size = locations.size();
        for (int i = 0; i < size; i++) {
            onLocationChanged(locations.get(i));
        }
    }

    void onFlushComplete(int requestCode) {
    }
}
