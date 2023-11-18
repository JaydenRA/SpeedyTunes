package androidx.core.content.pm;

import java.util.List;

public abstract class ShortcutInfoChangeListener {
    public void onShortcutAdded(List<ShortcutInfoCompat> list) {
    }

    public void onShortcutUpdated(List<ShortcutInfoCompat> list) {
    }

    public void onShortcutRemoved(List<String> list) {
    }

    public void onAllShortcutsRemoved() {
    }

    public void onShortcutUsageReported(List<String> list) {
    }
}
