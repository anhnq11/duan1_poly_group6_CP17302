package com.example.da1_poly_n6.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

public class ChiTietSPFrgm extends Fragment {

    SanPham sanPham;
    int rdoSizeCheck;
    String sizeCheck;

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

        RadioButton rdoSizeLon = view.findViewById(R.id.rdoSizeLon);
        RadioButton rdoSizeVua = view.findViewById(R.id.rdoSizeVua);
        RadioButton rdoSizeNho = view.findViewById(R.id.rdoSizeNho);

        rdoSizeNho.setChecked(true);
        sizeCheck = null;
        rdoSizeLon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sizeCheck = "L";
                    rdoSizeNho.setChecked(false);
                    rdoSizeVua.setChecked(false);
                }
            }
        });

        rdoSizeVua.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sizeCheck = "V";
                    rdoSizeLon.setChecked(false);
                    rdoSizeNho.setChecked(false);
                }
            }
        });

        rdoSizeNho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sizeCheck = "N";
                    rdoSizeLon.setChecked(false);
                    rdoSizeVua.setChecked(false);
                }
            }
        });

        txtChiTietTenSpTextView.setText(sanPham.getTenSanPham());
        txtChiTietGiaSP.setText(sanPham.getPrice() + "");
        txtChiTietMoTaSP.setText(sanPham.getMota());


        EditText btnChiTietAddToCart = view.findViewById(R.id.btnChiTietAddToCart);
        btnChiTietAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Đã thêm sản phẩm vào giỏ hàng!" + sizeCheck, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}