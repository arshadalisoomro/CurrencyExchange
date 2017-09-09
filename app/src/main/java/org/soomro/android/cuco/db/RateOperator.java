package org.soomro.android.cuco.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.soomro.android.cuco.model.Rate;

import java.util.List;

/**
 * Created by Arshay on 8/20/2017.
 */

public class RateOperator implements RateOperation {

    private DatabaseHandler mDatabaseHandler;
    private SQLiteDatabase mSqLiteDatabase;

    private Context mContext;

    public RateOperator(){}

    public RateOperator(Context mContext, DatabaseHandler mDatabaseHandler){
        this.mContext = mContext;
        this.mDatabaseHandler = mDatabaseHandler;
    }

    @Override
    public void addRate(Rate rate) {
        mSqLiteDatabase = mDatabaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CURRENCY_POSTFIX, rate.getCurrencyPostfix());
        values.put(DatabaseHandler.KEY_CURRENCY_RATE, rate.getCurrencyRate());
        values.put(DatabaseHandler.KEY_CURRENCY_TIMESTAMP, rate.getCurrencyTimeStamp());

        // Inserting Row
        mSqLiteDatabase.insert(DatabaseHandler.TABLE_CURRENCY, null, values);
        mSqLiteDatabase.close(); // Closing database connection
    }

    @Override
    public double findRate(String code) {
        mSqLiteDatabase = mDatabaseHandler.getReadableDatabase();

        Cursor cursor = null;
        double currencyRate = 0;
        try {
            cursor = mSqLiteDatabase.rawQuery("SELECT " + DatabaseHandler.KEY_CURRENCY_RATE
                    + " FROM " + DatabaseHandler.TABLE_CURRENCY
                    + " WHERE "+ DatabaseHandler.KEY_CURRENCY_POSTFIX +" LIKE '%"+ code +"%'", null);
            Log.e("LIKE_%", "code is " + code);
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                currencyRate = cursor.getDouble(cursor.getColumnIndex(DatabaseHandler.KEY_CURRENCY_RATE));
            }
            cursor.close();
            return currencyRate;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Rate getRate(int id) {
        return null;
    }

    @Override
    public List<Rate> getAllRates() {
        return null;
    }

    @Override
    public int getRatesCount() {
        int rowCount = 0;
        mSqLiteDatabase = mDatabaseHandler.getReadableDatabase();
        String countQuery = "SELECT  * FROM " + DatabaseHandler.TABLE_CURRENCY;
        Cursor cursor = mSqLiteDatabase.rawQuery(countQuery, null);
        if(cursor != null && !cursor.isClosed()){
            rowCount = cursor.getCount();
            cursor.close();
        }

        // return count
        return rowCount;
    }

    @Override
    public int updateRate(Rate rate) {
        mSqLiteDatabase = mDatabaseHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHandler.KEY_CURRENCY_POSTFIX, rate.getCurrencyPostfix());
        values.put(DatabaseHandler.KEY_CURRENCY_RATE, rate.getCurrencyRate());
        values.put(DatabaseHandler.KEY_CURRENCY_TIMESTAMP, rate.getCurrencyTimeStamp());

        // updating row
        return mSqLiteDatabase.update(DatabaseHandler.TABLE_CURRENCY, values, DatabaseHandler.KEY_CURRENCY_POSTFIX + " = ?",
                new String[] { rate.getCurrencyPostfix() });
    }

    @Override
    public void deleteRate(Rate rate) {

    }
}
