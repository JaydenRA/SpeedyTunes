package androidx.constraintlayout.core.motion.utils;

public class Schlick extends Easing {
    private static final boolean DEBUG = false;
    double eps;
    double mS;
    double mT;

    Schlick(String configString) {
        this.str = configString;
        int start = configString.indexOf(40);
        int off1 = configString.indexOf(44, start);
        this.mS = Double.parseDouble(configString.substring(start + 1, off1).trim());
        this.mT = Double.parseDouble(configString.substring(off1 + 1, configString.indexOf(44, off1 + 1)).trim());
    }

    private double func(double x) {
        double d = this.mT;
        if (x < d) {
            return (d * x) / ((this.mS * (d - x)) + x);
        }
        return ((1.0d - d) * (x - 1.0d)) / ((1.0d - x) - (this.mS * (d - x)));
    }

    private double dfunc(double x) {
        double d = this.mT;
        if (x < d) {
            double d2 = this.mS;
            return ((d2 * d) * d) / ((((d - x) * d2) + x) * ((d2 * (d - x)) + x));
        }
        double d3 = this.mS;
        return (((d - 1.0d) * d3) * (d - 1.0d)) / (((((-d3) * (d - x)) - x) + 1.0d) * ((((-d3) * (d - x)) - x) + 1.0d));
    }

    public double getDiff(double x) {
        return dfunc(x);
    }

    public double get(double x) {
        return func(x);
    }
}
