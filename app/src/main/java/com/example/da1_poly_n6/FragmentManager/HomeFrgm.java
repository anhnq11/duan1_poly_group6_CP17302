package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.da1_poly_n6.Adapter_Package.AdapterHome;
import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class HomeFrgm extends Fragment {

    RecyclerView recycler_SPBanChay;
    private AdapterHome adapterHome;
    private ArrayList<SanPham> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frgm, container, false);
        ImageView imgNotifi = view.findViewById(R.id.imgNotifi);
        recycler_SPBanChay = view.findViewById(R.id.recycler_SPBanChay);
        createData();
        imgNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_notifi);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                EditText btnDongNotifi = dialog.findViewById(R.id.btnDongNotifi);
                btnDongNotifi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();

            }
        });

        return view;
    }

    private void createData() {
        list.add(new SanPham(1, "L1", "Bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        list.add(new SanPham(1, "L1", "Cà phê đen", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        list.add(new SanPham(1, "L1", "Cà phê", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        list.add(new SanPham(1, "L1", "Cà phê sữa", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        list.add(new SanPham(1, "L1", "Cà phê bạc sỉu up sỉu dao", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));
        list.add(new SanPham(1, "L1", "Cà phê bạc sỉu", "L", 29000, 1, R.drawable.bac_xiu, "Ngon tuyệt"));

        adapterHome = new AdapterHome(list ,getActivity());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_SPBanChay.setLayoutManager(linearLayoutManager);
        recycler_SPBanChay.setAdapter(adapterHome);
    }
}