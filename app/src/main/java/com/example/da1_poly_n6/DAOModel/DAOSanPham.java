package com.example.da1_poly_n6.DAOModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.FragmentManager.ThemSPFrgm;
import com.example.da1_poly_n6.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DAOSanPham {
    private SQLiteDatabase database;
    DbHelper dbHelper;

    public DAOSanPham(Context context) {
        dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

    public void insertData(byte[] image, String TenSanPham, double Price, String Size, int MaLoai, String MoTa) {
        String sql = "INSERT INTO SanPham VALUES (NULL, ?,?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, image);
        statement.bindString(2, TenSanPham);
        statement.bindDouble(3, Price);
        statement.bindString(4, Size);
        statement.bindLong(5, MaLoai);
        statement.bindString(6, MoTa);

        statement.executeInsert();
    }
//
//    public List<SanPham> getAll() {
//        String sql = "SELECT * FROM SanPham";
//        return getData(sql);
//    }
//
//    public List<SanPham> getData(String sql, String... selectionAGrs) {
//        ArrayList<SanPham> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery(sql, selectionAGrs);
//        while (cursor.moveToNext()) {
//            SanPham sanPham = new SanPham();
//            byte[] image = cursor.getBlob(0);
//            String NameSP = cursor.getString(1);
//            double price = cursor.getDouble(2);
//            String size = cursor.getString(3);
//            int maLoai = cursor.getInt(4);
//            String moTa = cursor.getString(5);
//            list.add(new SanPham(image, NameSP, price, size, maLoai, moTa));
//        }
//        return list;
//    }

}
