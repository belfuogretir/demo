package com.blesh.demo;

import android.app.Service;
import android.bluetooth.BluetoothClass;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.blesh.sdk.util.BleshConstant;
import com.blesh.sdk.util.BleshIntent;

/**
 * Created by belfu on 7.06.2018.
 */

public class BleshHelperService extends Service {



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        isHostAppRunning(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {
            startService(new BleshIntent.Builder("alper", "alper123", "YOUR_INTEGRATION_ID")
                    //.optionalKey("")                     //(Optional)
                    .notificationSmallIcon("small_icon") //small_icon.png (For Notification)
                    //.notificationColor("#890A5B")        //Color Code (For Notification, Optional)
                    .getIntent(this));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        isHostAppRunning(false);

        stopSelf();
    }

    private void isHostAppRunning(boolean status) {
        Intent intent = new Intent(BleshConstant.BLESH_HOST_APP);
        intent.putExtra(BleshConstant.BLESH_HOST_APP_RUNNING_STATUS, status);
        sendBroadcast(intent);
    }
}
