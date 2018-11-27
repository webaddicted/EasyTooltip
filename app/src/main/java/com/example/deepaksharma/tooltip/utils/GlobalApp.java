package com.example.deepaksharma.tooltip.utils;

import android.app.Application;
import android.content.Context;

public class GlobalApp extends Application {
private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PreferenceUtil.init(this);
    }

    public Context getInstance(){
        return mContext;
    }
}
