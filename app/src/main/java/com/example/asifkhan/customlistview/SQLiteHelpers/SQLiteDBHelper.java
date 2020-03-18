package com.example.asifkhan.customlistview.SQLiteHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

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

    public void deleteOneUser(User user) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, "id = ?", new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    public User getPlayer(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, // a. table
                COLUMNS, // b. column names
                " email = ?", // c. selections
                new String[] { String.valueOf(email) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), null);

        return user;
    }
}
