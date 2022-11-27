package com.example.da1_poly_n6.DAOModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.User;

public class DAOUser {
    private SQLiteDatabase database;

    public DAOUser(Context context) {
        DbHelper dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getReadableDatabase();
        database = dbHelper.getWritableDatabase();
    }

    // insert User
    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put("Username", user.getUsername());
        values.put("ChucVu", user.getTenChucVu());
        values.put("Password", user.getPassword());
        values.put("SDT", user.getSDT());
        values.put("NamSinh", user.getNamSinh());

        return database.insert("User", null, values);
    }

    // update User
    public int update(User user){
        ContentValues values = new ContentValues();
        values.put("Username", user.getUsername());
        values.put("ChucVu", user.getTenChucVu());
        values.put("Password", user.getPassword());
        values.put("SDT", user.getSDT());
        values.put("NamSinh", user.getNamSinh());

        return database.update("User", values, "MaUser=?", new String[]{String.valueOf(user.getID_User())});

    }
}
