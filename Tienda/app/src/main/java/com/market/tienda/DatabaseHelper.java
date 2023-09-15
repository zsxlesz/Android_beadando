package com.market.tienda;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT, lastName TEXT, firstName TEXT, userName TEXT, cityName TEXT, streetName TEXT, streetNumbers TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int i, int i1) {
        MyDatabase.execSQL("drop Table if exists alluser");
    }

    public Boolean insertData(String email, String password, String lastName, String firstName, String userName, String cityName, String streetName, String streetNumbers){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        contentValues.put("lastName",lastName);
        contentValues.put("firstName",firstName);
        contentValues.put("userName",userName);
        contentValues.put("cityName",cityName);
        contentValues.put("streetName",streetName);
        contentValues.put("streetNumbers",streetNumbers);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }

    public Boolean checkEveryData(String email, String password, String lastName, String firstName, String userName, String cityName, String streetName, String streetNumbers){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ? and lastName = ? and firstName = ? and userName = ? and cityName = ? and streetName = ? and streetNumbers = ?", new String[]{email , password, lastName, firstName, userName , cityName, streetName, streetNumbers});

        if (cursor.getCount() > 0) {
            return true;
        }else{
            return false;
        }

    }

}
