package com.example.da1_poly_n6.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String dbName = "DuAn1";
    static final int version = 1;

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(byte[] image){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO IMAGE_PRODUCTS VALUES (NULL, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, image);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Bảng thể loại
        String createTableTheLoai = "CREATE TABLE TheLoai(\n" +
                "MaLoai TEXT,\n" +
                "TenLoai TEXT\n" +
                ");";
        db.execSQL(createTableTheLoai);

        //Bảng size sản phầm
        String createTableSize = "CREATE TABLE Size(\n" +
                "MaSize TEXT,\n" +
                "TenSize TEXT\n" +
                ");";
        db.execSQL(createTableSize);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
