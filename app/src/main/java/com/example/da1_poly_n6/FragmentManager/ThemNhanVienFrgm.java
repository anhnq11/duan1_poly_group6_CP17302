package com.example.da1_poly_n6.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.da1_poly_n6.DAOModel.DAOChucVu;
import com.example.da1_poly_n6.DAOModel.DAOUser;
import com.example.da1_poly_n6.Model.ChucVu;
import com.example.da1_poly_n6.Model.User;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ThemNhanVienFrgm extends Fragment {
    EditText edtUser, edtName, edtPassword, edtSDT, edtNamSinh, btnAdd, btnHuy;
    DAOUser daoUser;
    String strUsername;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nhan_vien_frgm, container, false);
        ImageView btnBackThemNV = view.findViewById(R.id.btnBackThemNV);
        btnBackThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Account_Fragment());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ánh xạ
        edtName = view.findViewById(R.id.edTenNhanVien);
        edtUser = view.findViewById(R.id.edUsername);
        edtPassword = view.findViewById(R.id.edPassword);
        edtSDT = view.findViewById(R.id.edSDT);
        edtNamSinh = view.findViewById(R.id.edNamSinh);
        btnAdd = view.findViewById(R.id.AddNhanVien);
        daoUser = new DAOUser(getActivity());

        //xử lý sự kiện
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setFullName(edtName.getText().toString());
                user.setUsername(edtUser.getText().toString());
                user.setPassword(edtPassword.getText().toString());
                user.setSDT(edtSDT.getText().toString());
                user.setMaChucVu(2);
                // check username đã tồn tài
                strUsername = edtUser.getText().toString();
                ArrayList<User> arrayList = daoUser.checkValidUser(strUsername);
                if (arrayList.size() != 0) {
                    // so sánh nếu có thì không cho insert
                    Toast.makeText(getActivity(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
                //insert
                if (checkEdt()) {
                    //Câu insert
                    if (daoUser.insertUser(user) < 0) {
                        Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        resetEdt();
                    }
                }
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //    Reset Edittext
    private void resetEdt() {
        edtName.setText("");
        edtName.setHintTextColor(Color.BLACK);
        edtUser.setText("");
        edtUser.setHintTextColor(Color.BLACK);
        edtPassword.setText("");
        edtPassword.setHintTextColor(Color.BLACK);
        edtSDT.setText("");
        edtSDT.setHintTextColor(Color.BLACK);
        edtNamSinh.setText("");
        edtNamSinh.setHintTextColor(Color.BLACK);
    }
//    Check Form

    private boolean checkEdt() {

        boolean checkAdd = true;

        if (edtName.getText().toString().isEmpty()) {
            edtName.setError("Vui lòng nhập!");
            edtName.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (edtUser.getText().toString().isEmpty()) {
            edtUser.setError("Vui lòng nhập!");
            edtUser.setHintTextColor(Color.RED);
            checkAdd = false;
        }
//        if (user.getUsername().equals(edtUser.getText().toString())) {
//            Toast.makeText(getActivity(), "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
//            checkAdd = false;
//        }
        if (edtPassword.getText().toString().isEmpty()) {
            edtPassword.setError("Vui lòng nhập!");
            edtPassword.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        if (edtSDT.getText().toString().isEmpty()) {
            edtSDT.setError("Vui lòng nhập!");
            edtSDT.setHintTextColor(Color.RED);
            checkAdd = false;
        }


        if (edtNamSinh.getText().toString().isEmpty()) {
            edtNamSinh.setError("Vui lòng nhập!");
            edtNamSinh.setHintTextColor(Color.RED);
            checkAdd = false;
        }
        try {
            Integer.parseInt(edtNamSinh.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(), "Năm sinh phải là số", Toast.LENGTH_SHORT).show();
        }

        return checkAdd;
    }

}