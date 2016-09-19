package com.example.administrator.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/7.
 */
public class MyBroadcast extends BroadcastReceiver {
    public static final String TAG="MyBroadcase";
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras();

        Log.i(TAG,"onReceiver() executed Name:"+bundle.getString("name"));
    }
}
