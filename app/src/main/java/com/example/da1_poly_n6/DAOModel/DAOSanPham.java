package com.example.da1_poly_n6.DAOModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.FragmentManager.ThemSPFrgm;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.Model.TheLoai;

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

    public void insertData(byte[] image, String TenSanPham, double Price, int MaLoai, String MoTa) {
        String sql = "INSERT INTO SanPham VALUES (NULL, ?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindBlob(1, image);
        statement.bindString(2, TenSanPham);
        statement.bindDouble(3, Price);
        statement.bindLong(4, MaLoai);
        statement.bindString(5, MoTa);

        statement.executeInsert();
    }

    public ArrayList<SanPham> getAllProduct(int rdoCheck) {
        String sql = null;
        if(rdoCheck == 0){
            sql = "SELECT * FROM SanPham";
        }
        if (rdoCheck == 1){
            sql = "SELECT * FROM SanPham ORDER BY Price ASC";
        }
        if (rdoCheck == 2){
            sql = "SELECT * FROM SanPham ORDER BY MaLoai ASC";
        }
        return getData(sql);
    }

    public ArrayList<SanPham> getData(String sql, String... selectionAGrs) {
        ArrayList<SanPham> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        while (cursor.moveToNext()) {
            byte[] image = cursor.getBlob(1);
            String name = cursor.getString(2);
            double price = cursor.getDouble(3);
            int maLoai = cursor.getInt(4);
            String moTa = cursor.getString(5);
            list.add(new SanPham(image, name, price, maLoai, moTa));
        }
        return list;
    }

//    DAO LOẠI SẢN PHẨM - ANHNQ

//    Thêm Loại Sản phẩm
    public boolean addLSP(TheLoai theLoai) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", theLoai.getTenLoai());
        long check = sqLiteDatabase.insert("THELOAI", null, contentValues);
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }

//    Lấy danh sách Loại Sản phẩm
    public ArrayList<TheLoai> getDSLSP(){
        ArrayList<TheLoai> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THELOAI", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maLoai = cursor.getInt(0);
                String tenLoai = cursor.getString(1);
                list.add(new TheLoai(maLoai, tenLoai));
            }   while (cursor.moveToNext());
        }
        return list;
    }
}
