package com.example.da1_poly_n6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DangNhapAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        EditText edtTenDangNhap = findViewById(R.id.edtTenDangNhap);
        EditText edtMatKhau = findViewById(R.id.edtMatKhau);
        CheckBox chkNhoMK = findViewById(R.id.chkNhoMK);
        ImageView btnDangNhap = findViewById(R.id.btnDangNhap);

        List<Object> list;
        list = readAccount();
        if(list.size() > 0){
            if ((boolean)list.get(3)){
                edtTenDangNhap.setText(list.get(0).toString());
                edtMatKhau.setText(list.get(2).toString());
                chkNhoMK.setChecked((boolean) list.get(3));
            }
        }

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtTenDangNhap.getText().toString();
                String password = edtMatKhau.getText().toString();
                boolean saveAcc = chkNhoMK.isChecked();

                boolean checkLogin = true;
                if (userName.length() == 0){
                    Toast.makeText(DangNhapAct.this, "Chưa nhập tên đăng nhập!", Toast.LENGTH_SHORT).show();
                    checkLogin = false;
                }

                if (password.length() == 0){
                    Toast.makeText(DangNhapAct.this, "Chưa nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                    checkLogin = false;
                }

                if (checkLogin){
//                    ArrayList<ThuThuModel> list = thuThuDao.checkDangNhap(userName, password);
//                Nếu tồn tại tài khoản thì lư
                        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("userName", userName);
//                        editor.putString("fullName", list.get(0).getHoTen());
                        editor.putString("passWord", password);
                        editor.putBoolean("saveAcc", saveAcc);
                        editor.commit();
                        Intent intent = new Intent(DangNhapAct.this, MainActivity.class);
                        intent.putExtra("userName", userName);
//                        intent.putExtra("fullName", list.get(0).getHoTen());
                        Toast.makeText(DangNhapAct.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DangNhapAct.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(DangNhapAct.this, "Tên đăng nhập hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
//                }
            }

        });

        closeKeyboard();
    }

    public List<Object> readAccount(){
        List<Object> list = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        list.add(sharedPreferences.getString("userName", ""));
        list.add(sharedPreferences.getString("fullName", ""));
        list.add(sharedPreferences.getString("passWord", ""));
        list.add(sharedPreferences.getBoolean("saveAcc", false));
        return list;
    }
    // ẩn bàn phím
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}