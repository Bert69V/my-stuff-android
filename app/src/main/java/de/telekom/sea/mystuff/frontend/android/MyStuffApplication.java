package de.telekom.sea.mystuff.frontend.android;

import android.app.Application;

import lombok.Getter;
import timber.log.Timber;

public class MyStuffApplication extends Application {

    @Getter
    private MyStuffContext stuffContext;

    public static MyStuffApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("onCreate: Enter");
        instance = this;
        stuffContext = new MyStuffContext();
        stuffContext.initWithApplication(this);
        initTimber();

    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
