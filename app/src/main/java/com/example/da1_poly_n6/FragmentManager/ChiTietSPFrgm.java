package com.example.da1_poly_n6.FragmentManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Model.GioHang;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.io.ByteArrayOutputStream;

public class ChiTietSPFrgm extends Fragment {

    SanPham sanPham;
    GioHang gioHang;
    String sizeCheck;
    TextView txtChiTietTenSpTextView, txtChiTietGiaSP, txtChiTietMoTaSP, total, soLuong;
    ImageView img_sp, img_sp1, btnTang, btnGiam;

    public ChiTietSPFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_s_p_frgm, container, false);
        txtChiTietTenSpTextView = view.findViewById(R.id.txtChiTietTenSp);
        txtChiTietGiaSP = view.findViewById(R.id.txtChiTietGiaSP);
        txtChiTietMoTaSP = view.findViewById(R.id.txtChiTietMoTaSP);
        soLuong = view.findViewById(R.id.txtSoLuong);
        img_sp = view.findViewById(R.id.imgCTSanPham);
        img_sp1 = view.findViewById(R.id.imgCTSanPham1);
        total = view.findViewById(R.id.total);
        btnTang = view.findViewById(R.id.btnSoLuongTang);
        btnGiam = view.findViewById(R.id.btnSoLuongGiam);


        final double L = 16000 + sanPham.getPrice();
        final double M = 10000 + sanPham.getPrice();
        final double S = 0 + sanPham.getPrice();

        RadioButton rdoSizeLon = view.findViewById(R.id.rdoSizeLon);
        RadioButton rdoSizeVua = view.findViewById(R.id.rdoSizeVua);
        RadioButton rdoSizeNho = view.findViewById(R.id.rdoSizeNho);

        rdoSizeNho.setChecked(true);
        sizeCheck = null;
        rdoSizeLon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sizeCheck = "L";
                    rdoSizeNho.setChecked(false);
                    rdoSizeVua.setChecked(false);
                    total.setText("" + L);
                }
            }
        });

        rdoSizeVua.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sizeCheck = "V";
                    rdoSizeLon.setChecked(false);
                    rdoSizeNho.setChecked(false);
                    total.setText("" + M);
                }
            }
        });

        rdoSizeNho.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sizeCheck = "N";
                    rdoSizeLon.setChecked(false);
                    rdoSizeVua.setChecked(false);
                    total.setText("" + S);
                }
            }
        });

        txtChiTietTenSpTextView.setText(sanPham.getTenSanPham());
        txtChiTietGiaSP.setText(sanPham.getPrice() + "");
        txtChiTietMoTaSP.setText(sanPham.getMota());
        byte[] productsImage = sanPham.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productsImage, 0, productsImage.length);
        img_sp.setImageBitmap(bitmap);
        img_sp1.setImageBitmap(bitmap);
        total.setText(sanPham.getPrice() + "");

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