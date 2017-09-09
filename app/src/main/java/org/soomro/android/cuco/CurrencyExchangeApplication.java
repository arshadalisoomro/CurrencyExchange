package org.soomro.android.cuco;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import org.soomro.android.cuco.controller.RestManager;
import org.soomro.android.cuco.db.DatabaseHandler;
import org.soomro.android.cuco.db.RateOperator;
import org.soomro.android.cuco.model.Currency;
import org.soomro.android.cuco.model.Meta;
import org.soomro.android.cuco.model.Rate;
import org.soomro.android.cuco.utils.Constant;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Arshay on 8/23/2017.
 */

public class CurrencyExchangeApplication extends Application{

    private RestManager mRestManager;
    private Call<List<Currency>> mCurrencyCall;

    private DatabaseHandler mDatabaseHandler;
    private RateOperator mDBRateOperator;

    @Override
    public void onCreate() {
        super.onCreate();
        mDatabaseHandler = new DatabaseHandler(getApplicationContext());
        mDBRateOperator = new RateOperator(getApplicationContext(), mDatabaseHandler);

        //Check if all data is inserted first time or not
        Log.e("CURRENCY_COUNT_ONCREATE", "is " + mDBRateOperator.getRatesCount());
        if (mDBRateOperator.getRatesCount() != Constant.CURRENCY_LIMIT){

            SharedPreferences choicePreferences = getSharedPreferences(Constant.CHOICE_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = choicePreferences.edit();
            editor.putInt(Constant.CURRENT_CHOICE, 1);

            editor.putString(Constant.CURRENT_CHOICE_CODE, "AFN");
            editor.apply();

            initRateCall();
        }
    }

    private void initRateCall(){
        //Get data from API service and insert once for the life time of the App
        mRestManager = new RestManager();
        mCurrencyCall = mRestManager.getApiService().getRates();
        mCurrencyCall.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, retrofit2.Response<List<Currency>> response) {
                try{
                    if (null != response.body()){
                        for (Currency currency : response.body()){
                            mDBRateOperator.addRate(new Rate(currency.getAbbr(), currency.getRate(), new Date().getTime()));
                        }
                        Log.e("CURRENCY_COUNT", "is " + mDBRateOperator.getRatesCount());

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
