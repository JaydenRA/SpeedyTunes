package androidx.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.contextaware.ContextAware;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.contextaware.OnContextAvailableListener;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnNewIntentProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.ContextCompat;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.os.BuildCompat;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import androidx.tracing.Trace;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ComponentActivity extends androidx.core.app.ComponentActivity implements ContextAware, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, ActivityResultCaller, OnConfigurationChangedProvider, OnTrimMemoryProvider, OnNewIntentProvider, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, MenuHost {
    private static final String ACTIVITY_RESULT_TAG = "android:support:activity-result";
    private final ActivityResultRegistry mActivityResultRegistry;
    private int mContentLayoutId;
    final ContextAwareHelper mContextAwareHelper;
    private ViewModelProvider.Factory mDefaultFactory;
    private boolean mDispatchingOnMultiWindowModeChanged;
    private boolean mDispatchingOnPictureInPictureModeChanged;
    private final LifecycleRegistry mLifecycleRegistry;
    private final MenuHostHelper mMenuHostHelper;
    private final AtomicInteger mNextLocalRequestCode;
    private final OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final CopyOnWriteArrayList<Consumer<Configuration>> mOnConfigurationChangedListeners;
    private final CopyOnWriteArrayList<Consumer<MultiWindowModeChangedInfo>> mOnMultiWindowModeChangedListeners;
    private final CopyOnWriteArrayList<Consumer<Intent>> mOnNewIntentListeners;
    private final CopyOnWriteArrayList<Consumer<PictureInPictureModeChangedInfo>> mOnPictureInPictureModeChangedListeners;
    private final CopyOnWriteArrayList<Consumer<Integer>> mOnTrimMemoryListeners;
    final SavedStateRegistryController mSavedStateRegistryController;
    private ViewModelStore mViewModelStore;

    static final class NonConfigurationInstances {
        Object custom;
        ViewModelStore viewModelStore;

        NonConfigurationInstances() {
        }
    }

    public ComponentActivity() {
        this.mContextAwareHelper = new ContextAwareHelper();
        this.mMenuHostHelper = new MenuHostHelper(new ComponentActivity$$ExternalSyntheticLambda2(this));
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        SavedStateRegistryController create = SavedStateRegistryController.create(this);
        this.mSavedStateRegistryController = create;
        this.mOnBackPressedDispatcher = new OnBackPressedDispatcher(new Runnable() {
            public void run() {
                try {
                    ComponentActivity.super.onBackPressed();
                } catch (IllegalStateException e) {
                    if (!TextUtils.equals(e.getMessage(), "Can not perform this action after onSaveInstanceState")) {
                        throw e;
                    }
                }
            }
        });
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mActivityResultRegistry = new ActivityResultRegistry() {
            public <I, O> void onLaunch(int requestCode, ActivityResultContract<I, O> contract, I input, ActivityOptionsCompat options) {
                Bundle optionsBundle;
                Bundle optionsBundle2;
                final int i = requestCode;
                ActivityResultContract<I, O> activityResultContract = contract;
                I i2 = input;
                ComponentActivity activity = ComponentActivity.this;
                final ActivityResultContract.SynchronousResult<O> synchronousResult = activityResultContract.getSynchronousResult(activity, i2);
                if (synchronousResult != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            AnonymousClass2.this.dispatchResult(i, synchronousResult.getValue());
                        }
                    });
                    return;
                }
                Intent intent = activityResultContract.createIntent(activity, i2);
                if (intent.getExtras() != null && intent.getExtras().getClassLoader() == null) {
                    intent.setExtrasClassLoader(activity.getClassLoader());
                }
                if (intent.hasExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE)) {
                    Bundle optionsBundle3 = intent.getBundleExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
                    intent.removeExtra(ActivityResultContracts.StartActivityForResult.EXTRA_ACTIVITY_OPTIONS_BUNDLE);
                    optionsBundle = optionsBundle3;
                } else if (options != null) {
                    optionsBundle = options.toBundle();
                } else {
                    optionsBundle = null;
                }
                if (ActivityResultContracts.RequestMultiplePermissions.ACTION_REQUEST_PERMISSIONS.equals(intent.getAction())) {
                    String[] permissions = intent.getStringArrayExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS);
                    if (permissions == null) {
                        permissions = new String[0];
                    }
                    ActivityCompat.requestPermissions(activity, permissions, i);
                    Bundle bundle = optionsBundle;
                } else if (ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST.equals(intent.getAction())) {
                    IntentSenderRequest request = (IntentSenderRequest) intent.getParcelableExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_INTENT_SENDER_REQUEST);
                    try {
                        optionsBundle2 = optionsBundle;
                        try {
                            ActivityCompat.startIntentSenderForResult(activity, request.getIntentSender(), requestCode, request.getFillInIntent(), request.getFlagsMask(), request.getFlagsValues(), 0, optionsBundle);
                        } catch (IntentSender.SendIntentException e) {
                            e = e;
                        }
                    } catch (IntentSender.SendIntentException e2) {
                        e = e2;
                        optionsBundle2 = optionsBundle;
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                AnonymousClass2.this.dispatchResult(i, 0, new Intent().setAction(ActivityResultContracts.StartIntentSenderForResult.ACTION_INTENT_SENDER_REQUEST).putExtra(ActivityResultContracts.StartIntentSenderForResult.EXTRA_SEND_INTENT_EXCEPTION, e));
                            }
                        });
                        Bundle bundle2 = optionsBundle2;
                    }
                    Bundle bundle22 = optionsBundle2;
                } else {
                    ActivityCompat.startActivityForResult(activity, intent, i, optionsBundle);
                }
            }
        };
        this.mOnConfigurationChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnTrimMemoryListeners = new CopyOnWriteArrayList<>();
        this.mOnNewIntentListeners = new CopyOnWriteArrayList<>();
        this.mOnMultiWindowModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mOnPictureInPictureModeChangedListeners = new CopyOnWriteArrayList<>();
        this.mDispatchingOnMultiWindowModeChanged = false;
        this.mDispatchingOnPictureInPictureModeChanged = false;
        if (getLifecycle() != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                getLifecycle().addObserver(new LifecycleEventObserver() {
                    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                        if (event == Lifecycle.Event.ON_STOP) {
                            Window window = ComponentActivity.this.getWindow();
                            View decor = window != null ? window.peekDecorView() : null;
                            if (decor != null) {
                                Api19Impl.cancelPendingInputEvents(decor);
                            }
                        }
                    }
                });
            }
            getLifecycle().addObserver(new LifecycleEventObserver() {
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        ComponentActivity.this.mContextAwareHelper.clearAvailableContext();
                        if (!ComponentActivity.this.isChangingConfigurations()) {
                            ComponentActivity.this.getViewModelStore().clear();
                        }
                    }
                }
            });
            getLifecycle().addObserver(new LifecycleEventObserver() {
                public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                    ComponentActivity.this.ensureViewModelStore();
                    ComponentActivity.this.getLifecycle().removeObserver(this);
                }
            });
            create.performAttach();
            SavedStateHandleSupport.enableSavedStateHandles(this);
            if (19 <= Build.VERSION.SDK_INT && Build.VERSION.SDK_INT <= 23) {
                getLifecycle().addObserver(new ImmLeaksCleaner(this));
            }
            getSavedStateRegistry().registerSavedStateProvider(ACTIVITY_RESULT_TAG, new ComponentActivity$$ExternalSyntheticLambda1(this));
            addOnContextAvailableListener(new ComponentActivity$$ExternalSyntheticLambda0(this));
            return;
        }
        throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.");
    }

    /* renamed from: lambda$new$0$androidx-activity-ComponentActivity  reason: not valid java name */
    public /* synthetic */ Bundle m0lambda$new$0$androidxactivityComponentActivity() {
        Bundle outState = new Bundle();
        this.mActivityResultRegistry.onSaveInstanceState(outState);
        return outState;
    }

    /* renamed from: lambda$new$1$androidx-activity-ComponentActivity  reason: not valid java name */
    public /* synthetic */ void m1lambda$new$1$androidxactivityComponentActivity(Context context) {
        Bundle savedInstanceState = getSavedStateRegistry().consumeRestoredStateForKey(ACTIVITY_RESULT_TAG);
        if (savedInstanceState != null) {
            this.mActivityResultRegistry.onRestoreInstanceState(savedInstanceState);
        }
    }

    public ComponentActivity(int contentLayoutId) {
        this();
        this.mContentLayoutId = contentLayoutId;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        this.mSavedStateRegistryController.performRestore(savedInstanceState);
        this.mContextAwareHelper.dispatchOnContextAvailable(this);
        super.onCreate(savedInstanceState);
        ReportFragment.injectIfNeededIn(this);
        if (BuildCompat.isAtLeastT()) {
            this.mOnBackPressedDispatcher.setOnBackInvokedDispatcher(Api33Impl.getOnBackInvokedDispatcher(this));
        }
        int i = this.mContentLayoutId;
        if (i != 0) {
            setContentView(i);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        Lifecycle lifecycle = getLifecycle();
        if (lifecycle instanceof LifecycleRegistry) {
            ((LifecycleRegistry) lifecycle).setCurrentState(Lifecycle.State.CREATED);
        }
        super.onSaveInstanceState(outState);
        this.mSavedStateRegistryController.performSave(outState);
    }

    public final Object onRetainNonConfigurationInstance() {
        NonConfigurationInstances nc;
        Object custom = onRetainCustomNonConfigurationInstance();
        ViewModelStore viewModelStore = this.mViewModelStore;
        if (viewModelStore == null && (nc = (NonConfigurationInstances) getLastNonConfigurationInstance()) != null) {
            viewModelStore = nc.viewModelStore;
        }
        if (viewModelStore == null && custom == null) {
            return null;
        }
        NonConfigurationInstances nci = new NonConfigurationInstances();
        nci.custom = custom;
        nci.viewModelStore = viewModelStore;
        return nci;
    }

    @Deprecated
    public Object onRetainCustomNonConfigurationInstance() {
        return null;
    }

    @Deprecated
    public Object getLastCustomNonConfigurationInstance() {
        NonConfigurationInstances nc = (NonConfigurationInstances) getLastNonConfigurationInstance();
        if (nc != null) {
            return nc.custom;
        }
        return null;
    }

    public void setContentView(int layoutResID) {
        initViewTreeOwners();
        super.setContentView(layoutResID);
    }

    public void setContentView(View view) {
        initViewTreeOwners();
        super.setContentView(view);
    }

    public void setContentView(View view, ViewGroup.LayoutParams params) {
        initViewTreeOwners();
        super.setContentView(view, params);
    }

    public void addContentView(View view, ViewGroup.LayoutParams params) {
        initViewTreeOwners();
        super.addContentView(view, params);
    }

    private void initViewTreeOwners() {
        ViewTreeLifecycleOwner.set(getWindow().getDecorView(), this);
        ViewTreeViewModelStoreOwner.set(getWindow().getDecorView(), this);
        ViewTreeSavedStateRegistryOwner.set(getWindow().getDecorView(), this);
        ViewTreeOnBackPressedDispatcherOwner.set(getWindow().getDecorView(), this);
    }

    public Context peekAvailableContext() {
        return this.mContextAwareHelper.peekAvailableContext();
    }

    public final void addOnContextAvailableListener(OnContextAvailableListener listener) {
        this.mContextAwareHelper.addOnContextAvailableListener(listener);
    }

    public final void removeOnContextAvailableListener(OnContextAvailableListener listener) {
        this.mContextAwareHelper.removeOnContextAvailableListener(listener);
    }

    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        if (featureId != 0) {
            return true;
        }
        super.onPreparePanel(featureId, view, menu);
        this.mMenuHostHelper.onPrepareMenu(menu);
        return true;
    }

    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        if (featureId != 0) {
            return true;
        }
        super.onCreatePanelMenu(featureId, menu);
        this.mMenuHostHelper.onCreateMenu(menu, getMenuInflater());
        return true;
    }

    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (super.onMenuItemSelected(featureId, item)) {
            return true;
        }
        if (featureId == 0) {
            return this.mMenuHostHelper.onMenuItemSelected(item);
        }
        return false;
    }

    public void onPanelClosed(int featureId, Menu menu) {
        this.mMenuHostHelper.onMenuClosed(menu);
        super.onPanelClosed(featureId, menu);
    }

    public void addMenuProvider(MenuProvider provider) {
        this.mMenuHostHelper.addMenuProvider(provider);
    }

    public void addMenuProvider(MenuProvider provider, LifecycleOwner owner) {
        this.mMenuHostHelper.addMenuProvider(provider, owner);
    }

    public void addMenuProvider(MenuProvider provider, LifecycleOwner owner, Lifecycle.State state) {
        this.mMenuHostHelper.addMenuProvider(provider, owner, state);
    }

    public void removeMenuProvider(MenuProvider provider) {
        this.mMenuHostHelper.removeMenuProvider(provider);
    }

    public void invalidateMenu() {
        invalidateOptionsMenu();
    }

    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    public ViewModelStore getViewModelStore() {
        if (getApplication() != null) {
            ensureViewModelStore();
            return this.mViewModelStore;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    /* access modifiers changed from: package-private */
    public void ensureViewModelStore() {
        if (this.mViewModelStore == null) {
            NonConfigurationInstances nc = (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nc != null) {
                this.mViewModelStore = nc.viewModelStore;
            }
            if (this.mViewModelStore == null) {
                this.mViewModelStore = new ViewModelStore();
            }
        }
    }

    public ViewModelProvider.Factory getDefaultViewModelProviderFactory() {
        if (this.mDefaultFactory == null) {
            this.mDefaultFactory = new SavedStateViewModelFactory(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        return this.mDefaultFactory;
    }

    public CreationExtras getDefaultViewModelCreationExtras() {
        MutableCreationExtras extras = new MutableCreationExtras();
        if (getApplication() != null) {
            extras.set(ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY, getApplication());
        }
        extras.set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        extras.set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        if (!(getIntent() == null || getIntent().getExtras() == null)) {
            extras.set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, getIntent().getExtras());
        }
        return extras;
    }

    public void onBackPressed() {
        this.mOnBackPressedDispatcher.onBackPressed();
    }

    public final OnBackPressedDispatcher getOnBackPressedDispatcher() {
        return this.mOnBackPressedDispatcher;
    }

    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }

    @Deprecated
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Deprecated
    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Deprecated
    public void startIntentSenderForResult(IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, Bundle options) throws IntentSender.SendIntentException {
        super.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!this.mActivityResultRegistry.dispatchResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Deprecated
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (!this.mActivityResultRegistry.dispatchResult(requestCode, -1, new Intent().putExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSIONS, permissions).putExtra(ActivityResultContracts.RequestMultiplePermissions.EXTRA_PERMISSION_GRANT_RESULTS, grantResults)) && Build.VERSION.SDK_INT >= 23) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> contract, ActivityResultRegistry registry, ActivityResultCallback<O> callback) {
        return registry.register("activity_rq#" + this.mNextLocalRequestCode.getAndIncrement(), this, contract, callback);
    }

    public final <I, O> ActivityResultLauncher<I> registerForActivityResult(ActivityResultContract<I, O> contract, ActivityResultCallback<O> callback) {
        return registerForActivityResult(contract, this.mActivityResultRegistry, callback);
    }

    public final ActivityResultRegistry getActivityResultRegistry() {
        return this.mActivityResultRegistry;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Iterator<Consumer<Configuration>> it = this.mOnConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(newConfig);
        }
    }

    public final void addOnConfigurationChangedListener(Consumer<Configuration> listener) {
        this.mOnConfigurationChangedListeners.add(listener);
    }

    public final void removeOnConfigurationChangedListener(Consumer<Configuration> listener) {
        this.mOnConfigurationChangedListeners.remove(listener);
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Iterator<Consumer<Integer>> it = this.mOnTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(Integer.valueOf(level));
        }
    }

    public final void addOnTrimMemoryListener(Consumer<Integer> listener) {
        this.mOnTrimMemoryListeners.add(listener);
    }

    public final void removeOnTrimMemoryListener(Consumer<Integer> listener) {
        this.mOnTrimMemoryListeners.remove(listener);
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Iterator<Consumer<Intent>> it = this.mOnNewIntentListeners.iterator();
        while (it.hasNext()) {
            it.next().accept(intent);
        }
    }

    public final void addOnNewIntentListener(Consumer<Intent> listener) {
        this.mOnNewIntentListeners.add(listener);
    }

    public final void removeOnNewIntentListener(Consumer<Intent> listener) {
        this.mOnNewIntentListeners.remove(listener);
    }

    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        if (!this.mDispatchingOnMultiWindowModeChanged) {
            Iterator<Consumer<MultiWindowModeChangedInfo>> it = this.mOnMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new MultiWindowModeChangedInfo(isInMultiWindowMode));
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        this.mDispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
            this.mDispatchingOnMultiWindowModeChanged = false;
            Iterator<Consumer<MultiWindowModeChangedInfo>> it = this.mOnMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new MultiWindowModeChangedInfo(isInMultiWindowMode, newConfig));
            }
        } catch (Throwable th) {
            this.mDispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    public final void addOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> listener) {
        this.mOnMultiWindowModeChangedListeners.add(listener);
    }

    public final void removeOnMultiWindowModeChangedListener(Consumer<MultiWindowModeChangedInfo> listener) {
        this.mOnMultiWindowModeChangedListeners.remove(listener);
    }

    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        if (!this.mDispatchingOnPictureInPictureModeChanged) {
            Iterator<Consumer<PictureInPictureModeChangedInfo>> it = this.mOnPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new PictureInPictureModeChangedInfo(isInPictureInPictureMode));
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        this.mDispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
            this.mDispatchingOnPictureInPictureModeChanged = false;
            Iterator<Consumer<PictureInPictureModeChangedInfo>> it = this.mOnPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                it.next().accept(new PictureInPictureModeChangedInfo(isInPictureInPictureMode, newConfig));
            }
        } catch (Throwable th) {
            this.mDispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    public final void addOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> listener) {
        this.mOnPictureInPictureModeChangedListeners.add(listener);
    }

    public final void removeOnPictureInPictureModeChangedListener(Consumer<PictureInPictureModeChangedInfo> listener) {
        this.mOnPictureInPictureModeChangedListeners.remove(listener);
    }

    public void reportFullyDrawn() {
        try {
            if (Trace.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            if (Build.VERSION.SDK_INT > 19) {
                super.reportFullyDrawn();
            } else if (Build.VERSION.SDK_INT == 19 && ContextCompat.checkSelfPermission(this, "android.permission.UPDATE_DEVICE_STATS") == 0) {
                super.reportFullyDrawn();
            }
        } finally {
            Trace.endSection();
        }
    }

    static class Api19Impl {
        private Api19Impl() {
        }

        static void cancelPendingInputEvents(View view) {
            view.cancelPendingInputEvents();
        }
    }

    static class Api33Impl {
        private Api33Impl() {
        }

        static OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity) {
            return activity.getOnBackInvokedDispatcher();
        }
    }
}
