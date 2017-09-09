package org.soomro.android.cuco.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.soomro.android.cuco.controller.RestManager;
import org.soomro.android.cuco.model.Currency;
import org.soomro.android.cuco.model.Rate;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by Arshay on 8/20/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "currencyManager";

    // Contacts table name
    public static final String TABLE_CURRENCY = "currency";

    // Contacts Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_CURRENCY_POSTFIX = "currencyPostfix";
    public static final String KEY_CURRENCY_RATE = "currencyRate";
    public static final String KEY_CURRENCY_TIMESTAMP = "currencyTimeStamp";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CURRENCY + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_CURRENCY_POSTFIX + " TEXT,"
                + KEY_CURRENCY_RATE + " REAL, "
                + KEY_CURRENCY_TIMESTAMP + " INTEGER"
                + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CURRENCY);

        // Create tables again
        onCreate(sqLiteDatabase);
    }
}
