package androidx.core.location;

import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.PreRGnssStatusTransport f$0;
    public final /* synthetic */ Executor f$1;

    public /* synthetic */ LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda1(LocationManagerCompat.PreRGnssStatusTransport preRGnssStatusTransport, Executor executor) {
        this.f$0 = preRGnssStatusTransport;
        this.f$1 = executor;
    }

    public final void run() {
        this.f$0.m25lambda$onStopped$1$androidxcorelocationLocationManagerCompat$PreRGnssStatusTransport(this.f$1);
    }
}
