package androidx.core.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.BuildCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;

public class ComponentActivity extends Activity implements LifecycleOwner, KeyEventDispatcher.Component {
    private SimpleArrayMap<Class<? extends ExtraData>, ExtraData> mExtraDataMap = new SimpleArrayMap<>();
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Deprecated
    public static class ExtraData {
    }

    @Deprecated
    public void putExtraData(ExtraData extraData) {
        this.mExtraDataMap.put(extraData.getClass(), extraData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ReportFragment.injectIfNeededIn(this);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        this.mLifecycleRegistry.markState(Lifecycle.State.CREATED);
        super.onSaveInstanceState(outState);
    }

    @Deprecated
    public <T extends ExtraData> T getExtraData(Class<T> extraDataClass) {
        return (ExtraData) this.mExtraDataMap.get(extraDataClass);
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public boolean superDispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        View decor = getWindow().getDecorView();
        if (decor == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decor, event)) {
            return super.dispatchKeyShortcutEvent(event);
        }
        return true;
    }

    public boolean dispatchKeyEvent(KeyEvent event) {
        View decor = getWindow().getDecorView();
        if (decor == null || !KeyEventDispatcher.dispatchBeforeHierarchy(decor, event)) {
            return KeyEventDispatcher.dispatchKeyEvent(this, decor, this, event);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean shouldDumpInternalState(String[] args) {
        return !shouldSkipDump(args);
    }

    private static boolean shouldSkipDump(String[] args) {
        if (args != null && args.length > 0) {
            String str = args[0];
            char c = 65535;
            switch (str.hashCode()) {
                case -645125871:
                    if (str.equals("--translation")) {
                        c = 2;
                        break;
                    }
                    break;
                case 100470631:
                    if (str.equals("--dump-dumpable")) {
                        c = 4;
                        break;
                    }
                    break;
                case 472614934:
                    if (str.equals("--list-dumpables")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1159329357:
                    if (str.equals("--contentcapture")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1455016274:
                    if (str.equals("--autofill")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    if (Build.VERSION.SDK_INT >= 26) {
                        return true;
                    }
                    return false;
                case 1:
                    if (Build.VERSION.SDK_INT >= 29) {
                        return true;
                    }
                    return false;
                case 2:
                    if (Build.VERSION.SDK_INT >= 31) {
                        return true;
                    }
                    return false;
                case 3:
                case 4:
                    return BuildCompat.isAtLeastT();
            }
        }
        return false;
    }
}
