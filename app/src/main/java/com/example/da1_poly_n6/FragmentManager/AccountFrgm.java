package com.example.da1_poly_n6.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.da1_poly_n6.Package_Activity.ImageProducts;
import com.example.da1_poly_n6.R;

public class AccountFrgm extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_frgm, container, false);

        LinearLayout userFrgmTaiKhoan = view.findViewById(R.id.userFrgmTaiKhoan);
        LinearLayout userFrgmDoiMK = view.findViewById(R.id.userFrgmDoiMK);
        LinearLayout userFrgmTKDoanhThu = view.findViewById(R.id.userFrgmTKDoanhThu);
        LinearLayout userFrgmTKNhanVien = view.findViewById(R.id.userFrgmTKNhanVien);
        LinearLayout userFrgmThemSP = view.findViewById(R.id.userFrgmThemSP);
        LinearLayout userFrgmSuaSP = view.findViewById(R.id.userFrgmSuaSP);
        LinearLayout userFrgmThemLSP = view.findViewById(R.id.userFrgmThemLSP);
        LinearLayout userFrgmThemNhanVien = view.findViewById(R.id.userFrgmThemNhanVien);
        LinearLayout userFrgmDangXuat = view.findViewById(R.id.userFrgmDangXuat);

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

        userFrgmSuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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