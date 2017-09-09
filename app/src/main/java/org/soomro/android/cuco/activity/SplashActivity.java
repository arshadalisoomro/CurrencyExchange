package org.soomro.android.cuco.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import org.soomro.android.cuco.R;
import org.soomro.android.cuco.controller.RestManager;
import org.soomro.android.cuco.db.DatabaseHandler;
import org.soomro.android.cuco.db.RateOperator;
import org.soomro.android.cuco.model.Currency;
import org.soomro.android.cuco.model.Rate;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SplashActivity extends AppCompatActivity {

    private RestManager mRestManager;
    private Call<List<Currency>> mCurrencyCall;

    private DatabaseHandler mDatabaseHandler;
    private RateOperator mDBRateOperator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mDatabaseHandler = new DatabaseHandler(getApplicationContext());
        mDBRateOperator = new RateOperator(getApplicationContext(), mDatabaseHandler);
        initDecoration();


        initAndFinish();
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
                        Log.e("CURRENCY_COUNT", "is " + mDBRateOperator.getRatesCount());
                        if (mDBRateOperator.getRatesCount() != 0){

                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                            finish();
                        }

                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                String err = "Unable to resolve host \"api-mesh.000webhostapp.com\": No address associated with hostname";
                if (mDBRateOperator.getRatesCount() == 0
                        && t.getMessage().equals(err)){
                    new AlertDialog.Builder(SplashActivity.this, R.style.Theme_AppCompat_Light_Dialog_Alert)
                            .setTitle("No Network!")
                            .setMessage("App needs to connect to an Active Network, please find one and Re-start App")
                            .setCancelable(false)
                            .setIcon(R.mipmap.ic_launcher_round)
                            .setPositiveButton("GOT IT", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    android.os.Process.killProcess(android.os.Process.myPid());
                                }
                            }).create().show();
                }
            }
        });
    }

    private void initDecoration() {
        Window window = this.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //Window w = getWindow(); // in Activity's onCreate() for instance
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // finally change the color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    private void initAndFinish() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initRateCall();
            }
        }, 2340L);
    }
}
