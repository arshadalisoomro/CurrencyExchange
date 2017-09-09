package org.soomro.android.cuco.service.network;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class ConnectionService extends Service {

    // Constant
    public final static String MY_ACTION = "MY_ACTION";
    public static String TAG_ACTIVITY_NAME = "activity_name";

    private int interval;

    private String activity_name;

    private Timer mTimer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try{
            interval = 10;

            activity_name = intent.getStringExtra(TAG_ACTIVITY_NAME);

            mTimer = new Timer();
            mTimer.scheduleAtFixedRate(new CheckForConnection(), 0, interval * 1000);
        }catch (Exception e){
            e.printStackTrace();
        }



        return super.onStartCommand(intent, flags, startId);
    }

    private class CheckForConnection extends TimerTask {
        @Override
        public void run() {
            Intent intent = new Intent();
            intent.setAction(MY_ACTION);
            intent.putExtra("Internet", isOnline());
            sendBroadcast(intent);

        }
    }

    @Override
    public void onDestroy() {
        mTimer.cancel();
        super.onDestroy();
    }

    public boolean isOnline() {

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        } else {
            return false;
        }

    }

}