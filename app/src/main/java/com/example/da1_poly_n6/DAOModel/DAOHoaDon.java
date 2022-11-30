package com.example.da1_poly_n6.DAOModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.HoaDon;

import java.util.ArrayList;

public class DAOHoaDon {

    private SQLiteDatabase database;
    DbHelper dbHelper;

    //    Khởi tạo Constructor
    public DAOHoaDon(Context context){
        dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

//    Tạo Hóa đơn
    public boolean addHoaDon(HoaDon hoaDon){
        ContentValues values = new ContentValues();
        values.put("MaUser", hoaDon.getMaUser());
        values.put("TenKhachHang", hoaDon.getTenKhachHang());
        values.put("NgayLapHD", hoaDon.getNgayLapHD());
        values.put("MaGioHang", hoaDon.getMaGiohang());
        long check = database.insert("HoaDon", null, values);
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public ArrayList<HoaDon> getHoaDon(){
        ArrayList<HoaDon> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT HoaDon.MaHoaDon, User.mauser, User.username, HoaDon.tenkhachhang, HoaDon.ngaylaphd, SanPham.tensanpham, GioHang.soluong, GioHang.size, GioHang.dongia, (GioHang.soluong * GioHang.dongia) as ThanhTien FROM HoaDon, GioHang, SanPham, User WHERE HoaDon.magiohang = GioHang.MaGioHang and GioHang.masanpham = SanPham.MaSanPham and HoaDon.mauser = User.MaUser", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maHoaDon = cursor.getInt(0);
                int maUser = cursor.getInt(1);
                String userName = cursor.getString(2);
                String tenKH = cursor.getString(3);
                String ngayLapHD = cursor.getString(4);
                String tenSP = cursor.getString(5);
                int soLuong = cursor.getInt(6);
                String size = cursor.getString(7);
                double donGia = cursor.getDouble(8);
                double thanhTien = cursor.getDouble(9);
                list.add(new HoaDon(maHoaDon, maUser, userName, tenKH, ngayLapHD, tenSP, soLuong, size, donGia, thanhTien));
            }   while (cursor.moveToNext());
        }
        return list;
    }
}
