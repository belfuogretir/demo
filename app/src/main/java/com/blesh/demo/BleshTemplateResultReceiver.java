package com.blesh.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.blesh.sdk.util.BleshConstant;

/**
 * Created by belfu on 7.06.2018.
 */

public class BleshTemplateResultReceiver extends BroadcastReceiver {
    private static final String TAG = "BleshTemplate";



    @Override
    public void onReceive(Context context, Intent intent) {


        if (intent.getAction().equals(BleshConstant.BLESH_TEMPLATE_RESULT_ACTION)) {

            String actionType = intent.getStringExtra(BleshConstant.BLESH_ACTION_TYPE);
            String actionValue = intent.getStringExtra(BleshConstant.BLESH_ACTION_VALUE);

            if (actionType != null && actionValue != null) {

                Log.w(TAG, "received action type:" + actionType);
                Log.w(TAG, "received action value:" + actionValue);
            }
        }
    }
}
