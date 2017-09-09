package org.soomro.android.cuco.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.soomro.android.cuco.controller.RestManager;
import org.soomro.android.cuco.db.DatabaseHandler;
import org.soomro.android.cuco.db.RateOperator;
import org.soomro.android.cuco.model.Currency;
import org.soomro.android.cuco.model.Rate;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Arshay on 8/23/2017.
 */

public class ConnectionReceiver extends BroadcastReceiver {

    private boolean offline;

    private RestManager mRestManager;
    private Call<List<Currency>> mCurrencyCall;

    private DatabaseHandler mDatabaseHandler;
    private RateOperator mDBRateOperator;
    private Context context;

    public ConnectionReceiver(Context context){
        this.context = context;
        mDatabaseHandler = new DatabaseHandler(context);
        mDBRateOperator = new RateOperator(context, mDatabaseHandler);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean hasInternet = intent.getBooleanExtra("Internet", false); //handle this

        if (hasInternet) {

            if (offline) {
                try{
                    updateRateCall();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            offline = false;
        } else {
            offline = true;
        }
    }

    private void updateRateCall(){
        //Get data from API service and insert once for the life time of the App
        mRestManager = new RestManager();
        mCurrencyCall = mRestManager.getApiService().getRates();
        mCurrencyCall.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, retrofit2.Response<List<Currency>> response) {
                try{
                    if (null != response.body()){
                        for (Currency currency : response.body()){
                            Log.e("UPDATE_ID", "is "+ mDBRateOperator.updateRate(
                                    new Rate(currency.getAbbr(), currency.getRate(), new Date().getTime())));
                        }
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {

            }
        });
    }
}
