package com.example.da1_poly_n6.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

public class ChiTietSPFrgm extends Fragment {

    SanPham sanPham;

    public ChiTietSPFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_s_p_frgm, container, false);
        TextView txtChiTietTenSpTextView = view.findViewById(R.id.txtChiTietTenSp);
        TextView txtChiTietGiaSP = view.findViewById(R.id.txtChiTietGiaSP);
        TextView txtChiTietMoTaSP = view.findViewById(R.id.txtChiTietMoTaSP);

        txtChiTietTenSpTextView.setText(sanPham.getTenSanPham());
        txtChiTietGiaSP.setText(sanPham.getDonGia() + "");
        txtChiTietMoTaSP.setText(sanPham.getMoTa());


//        Button btnChiTietAddToCart = view.findViewById(R.id.btnChiTietAddToCart);

        return view;
    }
}