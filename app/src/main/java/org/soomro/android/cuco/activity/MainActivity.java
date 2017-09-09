package org.soomro.android.cuco.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.soomro.android.cuco.R;
import org.soomro.android.cuco.db.DatabaseHandler;
import org.soomro.android.cuco.db.RateOperator;
import org.soomro.android.cuco.model.Meta;
import org.soomro.android.cuco.receiver.ConnectionReceiver;
import org.soomro.android.cuco.service.network.ConnectionService;
import org.soomro.android.cuco.utils.Constant;
import org.soomro.android.cuco.utils.Utils;
import org.soomro.android.cuco.views.ResizeableEditText;

public class MainActivity extends AppCompatActivity {

    private EditText mConvertFromEditText;
    private ResizeableEditText mConvertToTextView;

    private DatabaseHandler mDatabaseHandler;
    private RateOperator mDBRateOperator;

    private TextView mConvertFromLbl, mConvertToLbl,
            mConvertFromSymbolTextView, mConvertToSymbolTextView, mConvertFromCurrencyCode, mConvertToCurrencyCode;

    private ConnectionReceiver mConnectionReceiver;
    private Intent intentService = null;
    private String currencyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHandler = new DatabaseHandler(getApplicationContext());
        mDBRateOperator = new RateOperator(getApplicationContext(), mDatabaseHandler);

        // Registering Receiver
        mConnectionReceiver = new ConnectionReceiver(MainActivity.this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectionService.MY_ACTION);
        registerReceiver(mConnectionReceiver, intentFilter);

        mConvertFromLbl = (TextView) findViewById(R.id.convert_from_lbl);
        mConvertFromLbl.setTypeface(Utils.getFontFace(MainActivity.this));

        mConvertToLbl = (TextView) findViewById(R.id.convert_to_lbl);
        mConvertToLbl.setTypeface(Utils.getFontFace(MainActivity.this));
        mConvertToLbl.setSelected(true);

        mConvertToTextView = (ResizeableEditText) findViewById(R.id.convert_to_et);
        mConvertToTextView.setTypeface(Utils.getFontFace(MainActivity.this));

        mConvertFromSymbolTextView = (TextView) findViewById(R.id.convert_from_symbol);
        mConvertFromSymbolTextView.setTypeface(Utils.getFontFace(MainActivity.this));

        mConvertToSymbolTextView = (TextView) findViewById(R.id.convert_to_symbol);
        mConvertToSymbolTextView.setTypeface(Utils.getFontFace(MainActivity.this));

        mConvertFromCurrencyCode = (TextView) findViewById(R.id.convert_from_currency_code);
        mConvertFromCurrencyCode.setTypeface(Utils.getFontFace(MainActivity.this));

        mConvertToCurrencyCode = (TextView) findViewById(R.id.convert_to_currency_code);
        mConvertToCurrencyCode.setTypeface(Utils.getFontFace(MainActivity.this));


        mConvertFromEditText = (EditText) findViewById(R.id.convert_from_et);
        mConvertFromEditText.setTypeface(Utils.getFontFace(MainActivity.this));
        mConvertFromEditText.setHint("0");

        //intent service
        intentService = new Intent(this, ConnectionService.class);
        intentService.putExtra(ConnectionService.TAG_ACTIVITY_NAME, MainActivity.this.getClass().getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_choose : {
                mConvertFromEditText.setText("");
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return  true;
            } case R.id.action_about : {
                mConvertFromEditText.setText("");
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return  true;
            } default : {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(intentService);
        Log.e("Resume", "called");

        //Get Currency code
        SharedPreferences choicePreferences = getSharedPreferences(Constant.CHOICE_PREF, Context.MODE_PRIVATE);
        currencyCode = choicePreferences.getString(Constant.CURRENT_CHOICE_CODE, "AFN");

        mConvertFromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    if (mConvertFromEditText.getText().toString().isEmpty()
                            || mConvertFromEditText.getText().toString().equals("")){
                        return;
                    }
                    double currencyRate = mDBRateOperator.findRate(currencyCode);
                    double result = Utils.round(Double.valueOf(mConvertFromEditText.getText().toString()) * currencyRate, 2);
                    mConvertToTextView.setText(""+result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Meta meta = Utils.getMeta(currencyCode);
        mConvertToLbl.setText(meta.getName());
        mConvertToTextView.setText("0");
        mConvertToSymbolTextView.setText(meta.getSymbol());
        mConvertToCurrencyCode.setText(currencyCode);
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.e("Pause", "called");
        if (intentService != null)
            stopService(intentService);
    }

}
