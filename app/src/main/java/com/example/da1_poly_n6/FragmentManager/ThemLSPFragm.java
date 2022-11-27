package com.example.da1_poly_n6.FragmentManager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.da1_poly_n6.DAOModel.DAOSanPham;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.Model.TheLoai;
import com.example.da1_poly_n6.R;

public class ThemLSPFragm extends Fragment {
    
    DAOSanPham daoSanPham;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_l_s_p, container, false);
        
//        Khai báo DAO
        daoSanPham = new DAOSanPham(getContext());

//        Back về màn hình trước
        ImageView btnBackLSP = view.findViewById(R.id.btnBackLSP);
        btnBackLSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AccountFrgm());
            }
        });
//        Ánh xạ cho Editext
        EditText edtAddSPLoai = view.findViewById(R.id.edtAddSPLoai);
//        Ánh xạ Button
        EditText btnLSPHuy = view.findViewById(R.id.btnLSPHuy);
        EditText btnLSPXN = view.findViewById(R.id.btnLSPXN);
//        Sự kiện nút Hủy
        btnLSPHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtAddSPLoai.setText(null);
            }
        });
//        Sự kiện nút thêm
        btnLSPXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strLoai = edtAddSPLoai.getText().toString();
                if (strLoai.isEmpty()){
                    Toast.makeText(getContext(), "Vui lòng nhập Loại sản phẩm", Toast.LENGTH_SHORT).show();
                }
                else {
                    TheLoai theLoai = new TheLoai(strLoai);
                    boolean checkAdd = daoSanPham.addLSP(theLoai);
                    if (checkAdd){
                        Toast.makeText(getContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        edtAddSPLoai.setText(null);
                    }
                    else {
                        Toast.makeText(getContext(), "Fail!", Toast.LENGTH_SHORT).show();
                    }
                }
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