package com.example.da1_poly_n6.DAOModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.GioHang;
import com.example.da1_poly_n6.Model.User;

import java.util.ArrayList;
import java.util.List;

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
        values.put("FullName", user.getFullName());
        values.put("Username", user.getUsername());
        values.put("ChucVu", user.getMaChucVu());
        values.put("Password", user.getPassword());
        values.put("SDT", user.getSDT());
        values.put("NamSinh", user.getNamSinh());

        return database.insert("User", null, values);
    }

    // update User
    public int updatePass(User user) {
        ContentValues values = new ContentValues();
//        values.put("FullName", user.getFullName());
//        values.put("ChucVu", user.getTenChucVu());
        values.put("Password", user.getPassword());
//        values.put("SDT", user.getSDT());
//        values.put("NamSinh", user.getNamSinh());

        return database.update("User", values, "MaUser=?", new String[]{String.valueOf(user.getID_User())});
    }

    public User getID(String id) {
        String sql = "SELECT * FROM User WHERE Username=?";
        List<User> list = getData(sql, id);
        return list.get(0);
    }


    public ArrayList<User> getData(String sql, String... selectionAGrs) {
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sql, selectionAGrs);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int id_user = cursor.getInt(0);
                String fullname = cursor.getString(1);
                String username = cursor.getString(2);
                String password = cursor.getString(3);
                int id_chucVu = cursor.getInt(4);
                String sdt = cursor.getString(5);
                int namSinh = cursor.getInt(6);

                list.add(new User(id_user, fullname, username, password, id_chucVu, sdt, namSinh));
            } while (cursor.moveToNext());
        }
        return list;
    }

    //    Check Đăng nhập tài khoản
    public ArrayList<User> checkLogin(String username, String password) {
        String sql = "SELECT * FROM User WHERE Username=? AND Password=?";
        ArrayList<User> list = getData(sql, username, password);
        return list;
    }

    //    Lấy thông tin User theo ID
    public User getUser(int inputId) {
        User user = null;
        Cursor cursor = database.rawQuery("SELECT User.mauser, User.fullname, User.Username, User.password, ChucVu.MaChucVu, ChucVu.tenchucvu, User.sdt,User.namsinh FROM User, ChucVu WHERE User.ChucVu = ChucVu.machucvu and User.MaUser = ?", new String[]{String.valueOf(inputId)});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String userName = cursor.getString(2);
                String passWord = cursor.getString(3);
                int maChucVu = cursor.getInt(4);
                String tenChucVu = cursor.getString(5);
                String soDT = cursor.getString(6);
                int namSinh = cursor.getInt(7);
                user = new User(maUser, fullName, userName, passWord, maChucVu, tenChucVu, soDT, namSinh);
            } while (cursor.moveToNext());
        }
        return user;
    }

    //check tồn tại user
    public ArrayList<User> checkValidUser(String username) {
        ArrayList<User> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT User.mauser, User.fullname, User.Username, User.password, ChucVu.MaChucVu, ChucVu.tenchucvu, User.sdt,User.namsinh FROM User, ChucVu WHERE User.ChucVu = ChucVu.machucvu and User.Username = ?", new String[]{username});
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                int maUser = cursor.getInt(0);
                String fullName = cursor.getString(1);
                String userName = cursor.getString(2);
                String passWord = cursor.getString(3);
                int maChucVu = cursor.getInt(4);
                String tenChucVu = cursor.getString(5);
                String soDT = cursor.getString(6);
                int namSinh = cursor.getInt(7);
                list.add(new User(maUser, fullName, userName, passWord, maChucVu, tenChucVu, soDT, namSinh));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
