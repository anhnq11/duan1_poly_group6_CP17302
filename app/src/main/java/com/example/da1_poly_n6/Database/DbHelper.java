package com.example.da1_poly_n6.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
// Bảng thể loại
        String createTableTheLoai = "CREATE TABLE THELOAI(maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenLoai TEXT);";
        db.execSQL(createTableTheLoai);
//Bảng sản phẩm
        String createTableSanPham = ("CREATE TABLE SanPham(\n" +
                "MaSanPham INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "image BLOG,\n" +
                "TenSanPham TEXT,\n" +
                "Price double,\n" +
                "MaLoai INTEGER REFERENCES THELOAI(maLoai),\n" +
                "MoTa TEXT\n" +
                ");");
        db.execSQL(createTableSanPham);
// Bảng chức vụ
        String createTableChucVu = "CREATE Table ChucVu(\n" +
                "MaChucVu INTEGER PRIMARY KEy,\n" +
                "TenChucVu TEXT\n" +
                ");";
        db.execSQL(createTableChucVu);
        db.execSQL(InsertChucVu.insert_chucvu);
// Bảng User
        String tableUser = "CREATE Table User (\n" +
                "MaUser INTEGER PRIMARY Key AUTOINCREMENT,\n" +
                "Username TEXT,\n" +
                "ChucVu INTEGR REFERENCES ChucVu(machucvu),\n" +
                "Password TEXT,\n" +
                "SDT INTEGER,\n" +
                "NamSinh INTEGER\n" +
                ");";
        db.execSQL(tableUser);
// Bảng hóa đơn
        String tableHoaDon = "CREATE Table HoaDon (\n" +
                "MaHoaDon INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "MaUser INTEGER REFERENCES User(MaUser),\n" +
                "TenKhachHang TEXT,\n" +
                "SDT INTEGER,\n" +
                "NgayLapHD DATE,\n" +
                "MaGioHang INTEGER\n" +
                ");";
        db.execSQL(tableHoaDon);
// Bảng giỏ hàng
        String tableGioHang = "CREATE Table GioHang (\n" +
                "MaGioHang INTEGER,\n" +
                "MaSanPham INTEGER REFERENCES SanPham(MaSanPham),\n" +
                "SoLuong INTEGER,\n" +
                "Size TEXT,\n" +
                "DonGia DOUBLE\n" +
                ");";
        db.execSQL(tableGioHang);
// Bảng lưu hóa đơn
        String tableLuuHoaDon = "CREATE Table LuuHoaDon (\n" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "MaHoaDon INTEGER REFERENCES HoaDon(MaHoaDon),\n" +
                "TenUser TEXT,\n" +
                " TenKH TEXT,\n" +
                " SDT INTEGER,\n" +
                " MaSanPham INTEGER,\n" +
                "  SoLuong INTEGER,\n" +
                "  DonGia DOUBLE\n" +
                ");";
        db.execSQL(tableLuuHoaDon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropLoaiSP = "drop table if exists THELOAI";
        db.execSQL(dropLoaiSP);
        String dropSanPham = "drop table if exists SanPham";
        db.execSQL(dropSanPham);
        String dropChucVu = "drop table if exists ChucVu";
        db.execSQL(dropChucVu);
        String dropUser = "drop table if exists User";
        db.execSQL(dropUser);
        String dropHoaDon = "drop table if exists HoaDon";
        db.execSQL(dropHoaDon);
        String dropLuuHoaDon = "drop table if exists LuuHoaDon";
        db.execSQL(dropLuuHoaDon);
        String dropGioHang = "drop table if exists GioHang";
        db.execSQL(dropGioHang);
    }

}
