package com.example.asifkhan.customlistview.SQLiteHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.asifkhan.customlistview.models.Pill;
import com.example.asifkhan.customlistview.models.User;

import java.util.ArrayList;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PillsAppDB";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_PILLS = "Pills";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String[] COLUMNS_USER = {KEY_USER_ID, KEY_USER_NAME, KEY_USER_EMAIL,
            KEY_USER_PASSWORD};

    private static final String KEY_PILL_USERID = "userID";
    private static final String KEY_PILL_NAME = "name";
    private static final String KEY_PILL_DATE = "date";
    private static final String[] COLUMNS_PILL = {KEY_PILL_USERID, KEY_PILL_NAME, KEY_PILL_DATE};

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE_USERS = "CREATE TABLE Users ( "
                + "id TEXT PRIMARY KEY, " + "name TEXT, "
                + "email TEXT, " + "password TEXT )";

        String CREATION_TABLE_PILLS = "CREATE TABLE Pills ( "
                + "userID TEXT, " + "name TEXT, "
                + "date TEXT)";

        db.execSQL(CREATION_TABLE_USERS);
        db.execSQL(CREATION_TABLE_PILLS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PILLS);
        this.onCreate(db);

    }

    //Insert One User
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, user.getId());
        values.put(KEY_USER_NAME, user.getName());
        values.put(KEY_USER_EMAIL, user.getEmail());
        values.put(KEY_USER_PASSWORD, user.getPassword());
        // insert
        db.insert(TABLE_USERS,null, values);
        db.close();
    }

    public void addPill(User user, Pill pill) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PILL_USERID, user.getId());
        values.put(KEY_PILL_NAME, pill.getName());
        values.put(KEY_PILL_DATE, pill.getDate());
        // insert
        db.insert(TABLE_PILLS,null, values);
        db.close();
    }

    public boolean deletePill(String pillName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_PILLS, KEY_PILL_NAME + "='" + pillName + "'", null) > 0;
    }

    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Users WHERE email = '" + email +"';";
        Cursor cursor = db.rawQuery(sql, null);
        User user = new User();
        Log.v("DB", cursor.toString());
        // Read data, I simplify cursor in one line
        if (cursor.moveToFirst()) {

            // Get imageData in byte[]. Easy, right?
            user.setId(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_USER_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_USER_PASSWORD)));
        }
        cursor.close();
        db.close();
        return user;
    }

    public ArrayList getAllPills(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Pills WHERE userID = '" + user.getId() +"';";
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Pill> arrayListPills = new ArrayList<>();
        // Read data, I simplify cursor in one line
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String userID = cursor.getString(cursor.getColumnIndex(KEY_PILL_USERID));
                String name = cursor.getString(cursor.getColumnIndex(KEY_PILL_NAME));
                String date = cursor.getString(cursor.getColumnIndex(KEY_PILL_DATE));
                Pill pill = new Pill(name, date);

                arrayListPills.add(pill);
                cursor.moveToNext();
            }
        }
        cursor.close();
        db.close();
        return arrayListPills;
    }
}
