package com.example.igp.igpmobile.utilities.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.igp.igpmobile.utilities.data.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vimal on 9/12/15.
 */
public class SqliteHelper extends SQLiteOpenHelper {


    private static final String TAG = "IGP:SqliteHelper";

    //DATABASE
    public static final String DATABASE_NAME = "IGP.db";
    public static final int DATABASE_VERSION = 1;

    // TABLE NAME
    private static final String TABLE_USER ="user";

    // TABLE COLUMNS
    private static final String ID ="id";
    private static final String CODE ="code";
    private static final String TITLE ="title";

    // COMMANDS TO CREATE TABLE
    private static final String CREATE_TABLE_USER= "CREATE TABLE "
            + "IF NOT EXISTS "
            + TABLE_USER
            + "("
            + ID + " INTEGER,"
            + CODE + " TEXT,"
            + TITLE + " TEXT"
            + ")" ;

    private static final String DELETE_TABLE_USER = "DROP TABLE IF EXISTS " + TABLE_USER;


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "constructor");
    }

    //database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade");
        db.execSQL(DELETE_TABLE_USER);
        onCreate(db);
    }

    // use it when the user is logged out from the app
    public void deleteDatabase(Context context) {
        context.deleteDatabase(DATABASE_NAME);
    }


    //OTHER UTILITY FUNCTIONS
    public void addUserData(UserData userData){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID, userData.getId());
        values.put(CODE, userData.getCode());
        values.put(TITLE, userData.getTitle());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<UserData> getUserData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_USER;

        List<UserData> userDatas = new ArrayList<>();
        UserData userData;

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                userData = new UserData();
                userData.setId(cursor.getInt(cursor.getColumnIndex(ID)));
                userData.setCode(cursor.getString(cursor.getColumnIndex(CODE)));
                userData.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));

                userDatas.add(userData);
            }while (cursor.moveToNext());
        }
        db.close();
        return userDatas;
    }

    private void deleteTableData(String table, String whereClause){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, whereClause, null);
        db.close();
    }
}
