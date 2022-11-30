package com.example.da1_poly_n6.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.DAOModel.DAOUser;
import com.example.da1_poly_n6.DangNhapAct;
import com.example.da1_poly_n6.Model.User;
import com.example.da1_poly_n6.R;

public class Account_Fragment extends Fragment {
    private LinearLayout userFrgmTaiKhoan, userFrgmDoiMK, userFrgmTKDoanhThu, userFrgmTKNhanVien, userFrgmThemSP, userFrgmThemLSP, userFrgmThemNhanVien, userFrgmDangXuat;
    TextView txtUserName, txtChucVu;
    DAOUser daoUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_frgm, container, false);

        userFrgmTaiKhoan = view.findViewById(R.id.userFrgmTaiKhoan);
        userFrgmDoiMK = view.findViewById(R.id.userFrgmDoiMK);
        userFrgmTKDoanhThu = view.findViewById(R.id.userFrgmTKDoanhThu);
        userFrgmTKNhanVien = view.findViewById(R.id.userFrgmTKNhanVien);
        userFrgmThemSP = view.findViewById(R.id.userFrgmThemSP);
        userFrgmThemLSP = view.findViewById(R.id.userFrgmThemLSP);
        userFrgmThemNhanVien = view.findViewById(R.id.userFrgmThemNhanVien);
        userFrgmDangXuat = view.findViewById(R.id.userFrgmDangXuat);
        txtUserName = view.findViewById(R.id.txtUserName);
        txtChucVu = view.findViewById(R.id.txtChucVu);

        daoUser = new DAOUser(getContext());

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", getActivity().MODE_PRIVATE);
        int maUser = pref.getInt("MA", 0);
        User user = daoUser.getUser(maUser);
        int quyenUser = user.getMaChucVu();

        if (quyenUser == 2) {
            userFrgmThemNhanVien.setVisibility(View.GONE);
            userFrgmTKNhanVien.setVisibility(View.GONE);
            userFrgmThemSP.setVisibility(View.GONE);
            userFrgmThemLSP.setVisibility(View.GONE);
        }

        txtUserName.setText(user.getFullName());
        txtChucVu.setText(user.getTenChucVu());

        userFrgmTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new UserInfoFrgm());
            }
        });

        userFrgmDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new DoiMKFrgm());
            }
        });

        userFrgmTKDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new TKDoanhThuFrgm());
            }
        });

        userFrgmTKNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new TKNhanVienFrgm());
            }
        });

        userFrgmThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ThemSPFrgm());
            }
        });

        userFrgmThemLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ThemLSPFragm());
            }
        });

        userFrgmThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ThemNhanVienFrgm());
            }
        });

        userFrgmDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DangNhapAct.class);
                Toast.makeText(getContext(), "Đăng xuất!", Toast.LENGTH_SHORT).show();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}