package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
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
import android.widget.TextView;

import com.example.da1_poly_n6.Adapter_Package.AdapterGioHang;
import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.math.BigInteger;
import java.util.ArrayList;

public class StoreFrgm extends Fragment {

    private RecyclerView recycle_gioHang;
    private Button btnThanhToan;
    private AdapterGioHang adapterGioHang;
    private ArrayList<SanPham> arrayList = new ArrayList<>();
    private Button btn_accept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_store_frgm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycle_gioHang = view.findViewById(R.id.recycle_giohang);
        btnThanhToan = view.findViewById(R.id.btn_thanhToan);
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        createData();

    }

    private void dialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_thanh_toan);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_accept = dialog.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void createData() {
        arrayList.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        arrayList.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        arrayList.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        arrayList.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        arrayList.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        arrayList.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));

        adapterGioHang = new AdapterGioHang(getActivity());
        adapterGioHang.setData(arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_gioHang.setLayoutManager(linearLayoutManager);
        recycle_gioHang.setAdapter(adapterGioHang);
        adapterGioHang.notifyDataSetChanged();
    }

}