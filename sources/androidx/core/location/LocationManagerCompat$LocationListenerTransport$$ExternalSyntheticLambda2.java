package androidx.core.location;

import androidx.core.location.LocationManagerCompat;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda2(LocationManagerCompat.LocationListenerTransport locationListenerTransport, String str) {
        this.f$0 = locationListenerTransport;
        this.f$1 = str;
    }

    public final void run() {
        this.f$0.m19lambda$onProviderDisabled$5$androidxcorelocationLocationManagerCompat$LocationListenerTransport(this.f$1);
    }
}
