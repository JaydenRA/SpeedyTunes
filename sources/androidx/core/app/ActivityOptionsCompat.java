package androidx.core.app;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.core.util.Pair;

public class ActivityOptionsCompat {
    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";

    public static ActivityOptionsCompat makeCustomAnimation(Context context, int enterResId, int exitResId) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActivityOptionsCompatImpl(Api16Impl.makeCustomAnimation(context, enterResId, exitResId));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View source, int startX, int startY, int startWidth, int startHeight) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActivityOptionsCompatImpl(Api16Impl.makeScaleUpAnimation(source, startX, startY, startWidth, startHeight));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeClipRevealAnimation(View source, int startX, int startY, int width, int height) {
        if (Build.VERSION.SDK_INT >= 23) {
            return new ActivityOptionsCompatImpl(Api23Impl.makeClipRevealAnimation(source, startX, startY, width, height));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View source, Bitmap thumbnail, int startX, int startY) {
        if (Build.VERSION.SDK_INT >= 16) {
            return new ActivityOptionsCompatImpl(Api16Impl.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View sharedElement, String sharedElementName) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ActivityOptionsCompatImpl(Api21Impl.makeSceneTransitionAnimation(activity, sharedElement, sharedElementName));
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair<View, String>... sharedElements) {
        if (Build.VERSION.SDK_INT < 21) {
            return new ActivityOptionsCompat();
        }
        android.util.Pair<View, String>[] pairs = null;
        if (sharedElements != null) {
            pairs = new android.util.Pair[sharedElements.length];
            for (int i = 0; i < sharedElements.length; i++) {
                pairs[i] = android.util.Pair.create((View) sharedElements[i].first, (String) sharedElements[i].second);
            }
        }
        return new ActivityOptionsCompatImpl(Api21Impl.makeSceneTransitionAnimation(activity, pairs));
    }

    public static ActivityOptionsCompat makeTaskLaunchBehind() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ActivityOptionsCompatImpl(Api21Impl.makeTaskLaunchBehind());
        }
        return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeBasic() {
        if (Build.VERSION.SDK_INT >= 23) {
            return new ActivityOptionsCompatImpl(Api23Impl.makeBasic());
        }
        return new ActivityOptionsCompat();
    }

    private static class ActivityOptionsCompatImpl extends ActivityOptionsCompat {
        private final ActivityOptions mActivityOptions;

        ActivityOptionsCompatImpl(ActivityOptions activityOptions) {
            this.mActivityOptions = activityOptions;
        }

        public Bundle toBundle() {
            return this.mActivityOptions.toBundle();
        }

        public void update(ActivityOptionsCompat otherOptions) {
            if (otherOptions instanceof ActivityOptionsCompatImpl) {
                this.mActivityOptions.update(((ActivityOptionsCompatImpl) otherOptions).mActivityOptions);
            }
        }

        public void requestUsageTimeReport(PendingIntent receiver) {
            if (Build.VERSION.SDK_INT >= 23) {
                Api23Impl.requestUsageTimeReport(this.mActivityOptions, receiver);
            }
        }

        public ActivityOptionsCompat setLaunchBounds(Rect screenSpacePixelRect) {
            if (Build.VERSION.SDK_INT < 24) {
                return this;
            }
            return new ActivityOptionsCompatImpl(Api24Impl.setLaunchBounds(this.mActivityOptions, screenSpacePixelRect));
        }

        public Rect getLaunchBounds() {
            if (Build.VERSION.SDK_INT < 24) {
                return null;
            }
            return Api24Impl.getLaunchBounds(this.mActivityOptions);
        }
    }

    protected ActivityOptionsCompat() {
    }

    public ActivityOptionsCompat setLaunchBounds(Rect screenSpacePixelRect) {
        return this;
    }

    public Rect getLaunchBounds() {
        return null;
    }

    public Bundle toBundle() {
        return null;
    }

    public void update(ActivityOptionsCompat otherOptions) {
    }

    public void requestUsageTimeReport(PendingIntent receiver) {
    }

    static class Api16Impl {
        private Api16Impl() {
        }

        static ActivityOptions makeCustomAnimation(Context context, int enterResId, int exitResId) {
            return ActivityOptions.makeCustomAnimation(context, enterResId, exitResId);
        }

        static ActivityOptions makeScaleUpAnimation(View source, int startX, int startY, int width, int height) {
            return ActivityOptions.makeScaleUpAnimation(source, startX, startY, width, height);
        }

        static ActivityOptions makeThumbnailScaleUpAnimation(View source, Bitmap thumbnail, int startX, int startY) {
            return ActivityOptions.makeThumbnailScaleUpAnimation(source, thumbnail, startX, startY);
        }
    }

    static class Api23Impl {
        private Api23Impl() {
        }

        static ActivityOptions makeClipRevealAnimation(View source, int startX, int startY, int width, int height) {
            return ActivityOptions.makeClipRevealAnimation(source, startX, startY, width, height);
        }

        static ActivityOptions makeBasic() {
            return ActivityOptions.makeBasic();
        }

        static void requestUsageTimeReport(ActivityOptions activityOptions, PendingIntent receiver) {
            activityOptions.requestUsageTimeReport(receiver);
        }
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static ActivityOptions makeSceneTransitionAnimation(Activity activity, View sharedElement, String sharedElementName) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, sharedElement, sharedElementName);
        }

        @SafeVarargs
        static ActivityOptions makeSceneTransitionAnimation(Activity activity, android.util.Pair<View, String>... sharedElements) {
            return ActivityOptions.makeSceneTransitionAnimation(activity, sharedElements);
        }

        static ActivityOptions makeTaskLaunchBehind() {
            return ActivityOptions.makeTaskLaunchBehind();
        }
    }

    static class Api24Impl {
        private Api24Impl() {
        }

        static ActivityOptions setLaunchBounds(ActivityOptions activityOptions, Rect screenSpacePixelRect) {
            return activityOptions.setLaunchBounds(screenSpacePixelRect);
        }

        static Rect getLaunchBounds(ActivityOptions activityOptions) {
            return activityOptions.getLaunchBounds();
        }
    }
}
