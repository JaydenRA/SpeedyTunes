package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;

public final class WindowCompat {
    public static final int FEATURE_ACTION_BAR = 8;
    public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;

    private WindowCompat() {
    }

    public static <T extends View> T requireViewById(Window window, int id) {
        if (Build.VERSION.SDK_INT >= 28) {
            return (View) Api28Impl.requireViewById(window, id);
        }
        T view = window.findViewById(id);
        if (view != null) {
            return view;
        }
        throw new IllegalArgumentException("ID does not reference a View inside this Window");
    }

    public static void setDecorFitsSystemWindows(Window window, boolean decorFitsSystemWindows) {
        if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setDecorFitsSystemWindows(window, decorFitsSystemWindows);
        } else if (Build.VERSION.SDK_INT >= 16) {
            Api16Impl.setDecorFitsSystemWindows(window, decorFitsSystemWindows);
        }
    }

    public static WindowInsetsControllerCompat getInsetsController(Window window, View view) {
        return new WindowInsetsControllerCompat(window, view);
    }

    static class Api16Impl {
        private Api16Impl() {
        }

        static void setDecorFitsSystemWindows(Window window, boolean decorFitsSystemWindows) {
            int i;
            View decorView = window.getDecorView();
            int sysUiVis = decorView.getSystemUiVisibility();
            if (decorFitsSystemWindows) {
                i = sysUiVis & -1793;
            } else {
                i = sysUiVis | 1792;
            }
            decorView.setSystemUiVisibility(i);
        }
    }

    static class Api30Impl {
        private Api30Impl() {
        }

        static void setDecorFitsSystemWindows(Window window, boolean decorFitsSystemWindows) {
            window.setDecorFitsSystemWindows(decorFitsSystemWindows);
        }
    }

    static class Api28Impl {
        private Api28Impl() {
        }

        static <T> T requireViewById(Window window, int id) {
            return window.requireViewById(id);
        }
    }
}
