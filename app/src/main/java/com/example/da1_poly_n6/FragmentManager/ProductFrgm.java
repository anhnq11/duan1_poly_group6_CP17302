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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.DAOModel.DAOSanPham;
import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class ProductFrgm extends Fragment {

    private RecyclerView recyclerProduct;
    private AdapterSanPham adapterSanPham;
    private ArrayList<SanPham> list = new ArrayList<>();
    DAOSanPham daoSanPham;
    int rdoCheck = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_frgm, container, false);
        recyclerProduct = view.findViewById(R.id.recyclerProduct);
        ImageView filterProduct = view.findViewById(R.id.filterProduct);
        daoSanPham = new DAOSanPham(getContext());

        rdoCheck = 0;
        createData(0);

//        Set sự kiện OnClick Filter
        filterProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_filters);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                RadioButton rdoSPAll = dialog.findViewById(R.id.rdoSPAll);
                RadioButton rdoSPGia = dialog.findViewById(R.id.rdoSPGia);
                RadioButton rdoSPTL = dialog.findViewById(R.id.rdoSPTL);

                rdoSPAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            rdoCheck = 0;
                            createData(0);
                            dialog.dismiss();
                        }
                    }
                });

                rdoSPGia.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            rdoCheck = 1;
                            createData(1);
                            dialog.dismiss();
                        }
                    }
                });

                rdoSPTL.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            rdoCheck = 2;
                            createData(2);
                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

        return view;
    }

    private void createData(int rdoCheck) {
        DAOSanPham daoSanPham = new DAOSanPham(getActivity());
        ArrayList<SanPham> list = (ArrayList<SanPham>) daoSanPham.getAllProduct(rdoCheck);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerProduct.setLayoutManager(linearLayoutManager);
        AdapterSanPham adapterSanPham = new AdapterSanPham(getContext(), list);
        recyclerProduct.setAdapter(adapterSanPham);
    }

}