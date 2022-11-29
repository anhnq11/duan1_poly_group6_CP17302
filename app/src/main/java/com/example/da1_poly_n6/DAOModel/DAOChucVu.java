package com.example.da1_poly_n6.DAOModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.ChucVu;
import com.example.da1_poly_n6.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DAOChucVu {
    private SQLiteDatabase database;
    DbHelper dbHelper;

    public DAOChucVu(Context context) {
        dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

    public List<ChucVu> getAll() {
        String sql = "SELECT * FROM ChucVu";
        return getData(sql);
    }

    public List<ChucVu> getData(String sql, String... selectionAGrs) {
        ArrayList<ChucVu> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        while (cursor.moveToNext()) {
            int id_chucVu = cursor.getInt(0);
            String tenChucVu = cursor.getString(1);
            list.add(new ChucVu(id_chucVu, tenChucVu));
        }
        return list;
    }
}
