package com.example.da1_poly_n6.FragmentManager;

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
    EditText edtUser, edtName, edtPassword, edtSDT, edNamSinh, btnAdd, btnHuy;
    Spinner spnChucVu;
    DAOChucVu dao;
    DAOUser daoUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nhan_vien_frgm, container, false);
        ImageView btnBackThemNV = view.findViewById(R.id.btnBackThemNV);
        btnBackThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AccountFrgm());
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
        edNamSinh = view.findViewById(R.id.edNamSinh);
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
                user.setNamSinh(Integer.parseInt(edNamSinh.getText().toString()));
                user.setMaChucVu(2);
                //
                if (daoUser.insertUser(user) < 0) {
                    Toast.makeText(getActivity(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
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
}