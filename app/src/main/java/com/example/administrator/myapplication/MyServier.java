package com.example.administrator.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/6.
 */
public class MyServier extends Service {
    public static final String TAG="MyService";
    public static MyBind myBind;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle=intent.getExtras();

        Log.i(TAG,"onStartCommand() executed Name: "+bundle.getString("name"));
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind executed");
        return super.onUnbind(intent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Bundle bundle=intent.getExtras();
        Log.i(TAG,"onBind() executed Name: "+bundle.getString("name"));
        myBind=new MyBind();

        return myBind;
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.i(TAG,"unbindService() executed");
        super.unbindService(conn);
    }
    class MyBind extends Binder
    {
        MyBind()
        {

        }
        public  void startDown(String str)
        {
            Log.i(TAG,"startDown() executed STR: "+str);
        }
    }
}
