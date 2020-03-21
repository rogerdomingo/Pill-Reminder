package com.example.asifkhan.customlistview.SQLiteHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.asifkhan.customlistview.models.User;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PillsAppDB";
    private static final String TABLE_USERS = "Users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String[] COLUMNS = { KEY_ID, KEY_NAME, KEY_EMAIL,
            KEY_PASSWORD };

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Users ( "
                + "id TEXT PRIMARY KEY, " + "name TEXT, "
                + "email TEXT, " + "password TEXT )";

        db.execSQL(CREATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        this.onCreate(db);

    }

    //Insert One User
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.getId());
        values.put(KEY_NAME, user.getName());
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_PASSWORD, user.getPassword());
        // insert
        db.insert(TABLE_USERS,null, values);
        //Important! Sempre tancar la connexó a la DB
        db.close();
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
            user.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
            user.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
        }
        cursor.close();
        db.close();
        return user;
    }
}
