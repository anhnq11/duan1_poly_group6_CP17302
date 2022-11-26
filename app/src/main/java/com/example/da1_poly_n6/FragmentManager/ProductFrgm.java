package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.DAOModel.DAOSanPham;
import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class ProductFrgm extends Fragment {
    //thang
    private TextView filter;
    private RecyclerView recycle_caphe;
    private AdapterSanPham adapterSanPham;
    private ArrayList<SanPham> list = new ArrayList<>();
    DbHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_frgm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recycle_caphe = view.findViewById(R.id.recycle_coffe);
        filter = view.findViewById(R.id.id_filter);
        adapterSanPham = new AdapterSanPham(getActivity());
        dbHelper = new DbHelper(getActivity(), "DuAn1", null, 1);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        createData();
    }

    private void dialog() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_filters);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void createData() {
        DAOSanPham daoSanPham = new DAOSanPham(getActivity());
        list.clear();
        list = (ArrayList<SanPham>) daoSanPham.getAllProduct();
        adapterSanPham.setData(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_caphe.setLayoutManager(linearLayoutManager);
        recycle_caphe.setAdapter(adapterSanPham);
        adapterSanPham.notifyDataSetChanged();
    }
}