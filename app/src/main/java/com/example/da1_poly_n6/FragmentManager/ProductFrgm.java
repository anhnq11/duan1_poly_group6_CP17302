package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;
import java.util.ArrayList;

public class ProductFrgm extends Fragment {
    //thang

    private TextView filter;
    private TextView close;
    private RecyclerView recycle_caphe;
    private AdapterSanPham adapterSanPham;
    private ArrayList<SanPham> arrayList = new ArrayList<>();
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
        close = view.findViewById(R.id.tv_close);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
        createData();
    }

    private void dialog() {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog_filter, null);
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(alertLayout);
        dialog.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
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

        adapterSanPham = new AdapterSanPham(getActivity());
        adapterSanPham.setData(arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_caphe.setLayoutManager(linearLayoutManager);
        recycle_caphe.setAdapter(adapterSanPham);
        adapterSanPham.notifyDataSetChanged();
    }
}