package com.example.da1_poly_n6.DAOModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.da1_poly_n6.Database.DbHelper;
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
        values.put("ChucVu", user.getTenChucVu());
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
        String sql = "SELECT * FROM User WHERE MaUser=?";
        List<User> list = getData(sql, id);
        return list.get(0);
    }

    public List<User> getAllUser() {
        String sql = "SELECT User.MaUser, User.FullName, User.Username, ChucVu.TenChucVu,User.Password,User.SDT,User.NamSinh FROM User, ChucVu WHERE User.MaChucVu = ChucVu.MaChucVu";
        return getData(sql);
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

    public int checkLogin(String username, String password) {
        String sql = "SELECT * FROM User WHERE Username=? AND Password=?";
        List<User> list = getData(sql, username, password);
        if (list.size() == 0)
            return -1;
        return 1;
    }
}
