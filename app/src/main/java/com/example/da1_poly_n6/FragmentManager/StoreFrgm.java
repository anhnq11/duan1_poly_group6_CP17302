package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Adapter_Package.AdapterGioHang;
import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.DAOModel.DAOGioHang;
import com.example.da1_poly_n6.Model.GioHang;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StoreFrgm extends Fragment {

    RecyclerView recycle_gioHang;
    DAOGioHang daoGioHang;
    ArrayList<GioHang> list;
    TextView txtGHTongTien;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_frgm, container, false);

        EditText edtGHTenKH = view.findViewById(R.id.edtGHTenKH);
        daoGioHang = new DAOGioHang(getContext());
        recycle_gioHang = view.findViewById(R.id.recycle_giohang);
        list = daoGioHang.getGioHang();
        txtGHTongTien = view.findViewById(R.id.txtGHTongTien);


        EditText btnGioHangTT = view.findViewById(R.id.btnGioHangTT);
        btnGioHangTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKH = edtGHTenKH.getText().toString();
//                Kiểm tra nhập tên khách hàng
                if (tenKH.isEmpty()){
                    edtGHTenKH.setHintTextColor(Color.RED);
                    edtGHTenKH.setError("Vui lòng nhập!");
                }
                else {
                    edtGHTenKH.setHintTextColor(Color.BLACK);

//                Tạo hóa đơn

//                    Lấy tên nhân viên
                    SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", getActivity().MODE_PRIVATE);
                    String userName = pref.getString("USERNAME", "");
//                    Lấy ngày tạo hóa đơn
                    Date nowDate = Calendar.getInstance().getTime();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String ngayTaoHD = simpleDateFormat.format(nowDate);

//                Lấy thông tin hóa đơn - Hiển thị lên dialog
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_thanh_toan);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//                    Ánh xạ View

                    EditText btnHoaDonHuy = dialog.findViewById(R.id.btnHoaDonHuy);
                    EditText btnHoaDonXN = dialog.findViewById(R.id.btnHoaDonXN);

                    TextView txtHDTenNV = dialog.findViewById(R.id.txtHDTenNV);
                    TextView txtHDTenKH = dialog.findViewById(R.id.txtHDTenKH);
                    TextView txtHDNgayBan = dialog.findViewById(R.id.txtHDNgayBan);
                    RecyclerView recycle_hoaDon = dialog.findViewById(R.id.recycle_hoaDon);
                    TextView txtHDTongTien = dialog.findViewById(R.id.txtHDTongTien);

//                    Settext cho các View

                    txtHDTenNV.setText(userName);
                    txtHDTenKH.setText(tenKH);
                    txtHDNgayBan.setText(ngayTaoHD);

//                Sự kiện Button Hủy
                    btnHoaDonHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

//                Sự kiện Button Xác nhận -> Chuyển Hóa đơn vào bảng Lưu hóa đơn
                    btnHoaDonXN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialog.show();
                }

            }

        });

        createData();
        return view;
    }

    private void dialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_thanh_toan);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        EditText btnHoaDonHuy = dialog.findViewById(R.id.btnHoaDonHuy);
        EditText btnHoaDonXN = dialog.findViewById(R.id.btnHoaDonXN);
        btnHoaDonHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hủy!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnHoaDonXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Thanh toán thành công!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void createData() {
        daoGioHang = new DAOGioHang(getContext());
        list = daoGioHang.getGioHang();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycle_gioHang.setLayoutManager(linearLayoutManager);
        double tongTien = daoGioHang.tongTienGiohang();
        String outTongTien = String.format("%,.0f", tongTien);
        txtGHTongTien.setText(outTongTien + " VNĐ");
        AdapterGioHang adapterGioHang = new AdapterGioHang(getContext(), list, StoreFrgm.this);
        recycle_gioHang.setAdapter(adapterGioHang);
    }

}