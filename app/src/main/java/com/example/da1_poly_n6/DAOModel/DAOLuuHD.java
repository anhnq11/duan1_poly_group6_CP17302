package com.example.da1_poly_n6.DAOModel;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.HoaDon;
import com.example.da1_poly_n6.Model.LuuHoaDon;
import com.example.da1_poly_n6.Model.SanPham;

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
        ArrayList<LuuHoaDon> listTKNV = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT User.MaUser, User.fullname, User.username, User.ChucVu, User.SDT, User.namsinh, SUM(LuuHoaDon.soluong * LuuHoaDon.dongia) as doanhThu FROM User left JOIN LuuHoaDon on User.MaUser = LuuHoaDon.mauser GROUP BY User.MaUser", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String userName = cursor.getString(2);
                int chucVu = cursor.getInt(3);
                String userSDT = cursor.getString(4);
                int userNamSinh = cursor.getInt(5);
                double userDoanhThu = cursor.getDouble(6);
                listTKNV.add(new LuuHoaDon(maUser, fullName, userName, chucVu, userSDT, userNamSinh, userDoanhThu));
            }   while (cursor.moveToNext());
        }
        return listTKNV;
    }

//    Lấy tổng doanh thu theo khoảng thời gian
    public double getTongDoanhThu(String dStart, String dEnd, int caseTK, int maUserInput){
        double tongDT = 0;
        Cursor cursor = null;
        if (caseTK == 2){
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ?;", new String[]{dStart, dEnd});
        }
        else {
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ? AND LuuHoaDon.maUser = ?;", new String[]{dStart, dEnd, String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongDT += cursor.getDouble(0);
            }   while (cursor.moveToNext());
        }

        return tongDT;
    }

    //    Lấy tổng doanh thu theo khoảng thời gian
    public double getAllDoanhThu(int caseTK, int maUserInput){
        double tongDT = 0;
        Cursor cursor = null;
        if (caseTK == 2){
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon;", null);
        }
        else {
            cursor = database.rawQuery("SELECT sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon WHERE LuuHoaDon.maUser = ?;", new String[]{String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                tongDT += cursor.getDouble(0);
            }   while (cursor.moveToNext());
        }

        return tongDT;
    }

//    Lấy danh sách Hóa đơn + Doanh thu theo mã hóa đơn
    public ArrayList<LuuHoaDon> getDSHoaDon(String dStart, String dEnd, int caseTk, int maUserInput){
        ArrayList<LuuHoaDon> listHD = new ArrayList<>();
        Cursor cursor = null;
        if (caseTk == 2){
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) " +
                    "FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ? " +
                    "GROUP BY LuuHoaDon.maHoaDon;", new String[]{dStart, dEnd});
        }
        else {
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) " +
                    "FROM LuuHoaDon WHERE NgayLapHD BETWEEN ? AND ?  AND LuuHoaDon.maUser = ? " +
                    "GROUP BY LuuHoaDon.maHoaDon;", new String[]{dStart, dEnd, String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maLuu = cursor.getInt(0);
                String tenKH = cursor.getString(1);
                double thanhTien = cursor.getDouble(2);
                listHD.add(new LuuHoaDon(maLuu, tenKH, thanhTien));
            }   while (cursor.moveToNext());
        }
        return listHD;
    }

    //    Lấy danh sách Hóa đơn + Doanh thu theo mã hóa đơn
    public ArrayList<LuuHoaDon> getAllHoaDon(int caseTk, int maUserInput){
        ArrayList<LuuHoaDon> listHD = new ArrayList<>();
        Cursor cursor = null;
        if (caseTk == 2){
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) FROM LuuHoaDon GROUP BY LuuHoaDon.maHoaDon;", null);
        }
        else {
            cursor = database.rawQuery("SELECT LuuHoaDon.maLuu, LuuHoaDon.tenkhachhang, sum(LuuHoaDon.soluong * LuuHoaDon.dongia) " +
                    "FROM LuuHoaDon WHERE LuuHoaDon.maUser = ? " +
                    "GROUP BY LuuHoaDon.maHoaDon;", new String[]{String.valueOf(maUserInput)});
        }
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maLuu = cursor.getInt(0);
                String tenKH = cursor.getString(1);
                double thanhTien = cursor.getDouble(2);
                listHD.add(new LuuHoaDon(maLuu, tenKH, thanhTien));
            }   while (cursor.moveToNext());
        }
        return listHD;
    }

//    Get SP bán chạy
    public ArrayList<Integer> getTopSP (){
        ArrayList<Integer> listMaSP = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT LuuHoaDon.maSP, SUM(LuuHoaDon.soluong) as tongSL FROM LuuHoaDon GROUP by LuuHoaDon.maSP ORDER BY tongSL DESC LIMIT 4", null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                int maSp = cursor.getInt(0);
                listMaSP.add(maSp);
            }   while (cursor.moveToNext());
        }
        return listMaSP;
    }
}
