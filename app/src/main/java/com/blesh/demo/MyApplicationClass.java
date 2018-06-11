package com.blesh.demo;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.blesh.sdk.di.component.BleshSdkComponent;

/**
 * Created by belfu on 7.06.2018.
 */

public class MyApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        BleshSdkComponent.init(this);
        mInstance = this;
    }
    public static final String TAG = MyApplicationClass.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static MyApplicationClass mInstance;



    public static synchronized MyApplicationClass getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       // MultiDex.install(this);

    }
}
