package com.example.da1_poly_n6.DAOModel;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.HoaDon;
import com.example.da1_poly_n6.Model.LuuHoaDon;

import java.util.ArrayList;

public class DAOLuuHD {

    private SQLiteDatabase database;
    DbHelper dbHelper;

    //    Khởi tạo Constructor
    public DAOLuuHD(Context context){
        dbHelper = new DbHelper(context, "DuAn1", null, 1);
        database = dbHelper.getWritableDatabase();
        database = dbHelper.getReadableDatabase();
    }

//    Thêm Lưu Hóa đơn
    public boolean addLuuHD(LuuHoaDon luuHoaDon){
        ContentValues values = new ContentValues();
        values.put("maHoaDon", luuHoaDon.getMaHoaDon());
        values.put("maUser", luuHoaDon.getMaUser());
        values.put("tenUser", luuHoaDon.getTenUser());
        values.put("tenKhachHang", luuHoaDon.getTenKhachHang());
        values.put("NgayLapHD", luuHoaDon.getNgayLapHD());
        values.put("maSP", luuHoaDon.getMaSP());
        values.put("tenSP", luuHoaDon.getTenSP());
        values.put("soLuong", luuHoaDon.getSoLuong());
        values.put("size", luuHoaDon.getSize());
        values.put("donGia", luuHoaDon.getDonGia());
        values.put("thanhTien", luuHoaDon.getThanhTien());
        long check = database.insert("LuuHoaDon", null, values);
        if (check == -1){
            return false;
        }
        else {
            return true;
        }
    }

//    Thống kê nhân viên và doanh thu theo từng nhân viên
    public ArrayList<LuuHoaDon> tkNhanVien(){
        ArrayList<LuuHoaDon> listNhanVien = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT User.mauser," +
                " User.FullName," +
                " User.username," +
                " ChucVu.TenChucVu," +
                " User.sdt," +
                " User.namsinh," +
                " Sum(LuuHoaDon.thanhtien) as doanhThu" +
                " FROM LuuHoaDon, User, ChucVu " +
                "WHERE LuuHoaDon.mauser = User.MaUser AND User.ChucVu = ChucVu.MaChucVu " +
                "GROUP by LuuHoaDon.maUser", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String userName = cursor.getString(2);
                String chucVu = cursor.getString(3);
                String userSDT = cursor.getString(4);
                int userNamSinh = cursor.getInt(5);
                double userDoanhThu = cursor.getDouble(6);
                listNhanVien.add(new LuuHoaDon(maUser, fullName, userName, chucVu, userSDT, userNamSinh, userDoanhThu));
            }   while (cursor.moveToNext());
        }
        return listNhanVien;
    }

}
