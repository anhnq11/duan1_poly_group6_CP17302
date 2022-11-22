package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

public class ChiTietSPSuaFrgm extends Fragment implements View.OnClickListener {

    SanPham sanPham;

    public ChiTietSPSuaFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chi_tiet_s_p_sua_frgm, container, false);
        TextView txtCTSPSuaTenSp = view.findViewById(R.id.txtCTSPSuaTenSp);
        TextView txtCTSPSuaGiaSP = view.findViewById(R.id.txtCTSPSuaGiaSP);
        TextView txtCTSPSuaLoaiSP = view.findViewById(R.id.txtCTSPSuaLoaiSP);
        TextView txtCTSPSuaMoTaSP = view.findViewById(R.id.txtCTSPSuaMoTaSP);
        EditText btnCTSPSuaSua = view.findViewById(R.id.btnCTSPSuaSua);
        EditText btnCTSPSuaXoa = view.findViewById(R.id.btnCTSPSuaXoa);

        txtCTSPSuaTenSp.setText(sanPham.getTenSanPham());
        txtCTSPSuaGiaSP.setText(sanPham.getDonGia() + "");
        txtCTSPSuaLoaiSP.setText("Loại sản phẩm: " + sanPham.getMaLoai());
        txtCTSPSuaMoTaSP.setText(sanPham.getMoTa());

        btnCTSPSuaSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new SuaSPFrgm(sanPham));
            }
        });

        btnCTSPSuaXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.dialog_confirm);
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                TextView dialog_confirm_content = dialog.findViewById(R.id.dialog_confirm_content);
                EditText btnDialogHuy = dialog.findViewById(R.id.btnDialogHuy);
                EditText btnDialogXN = dialog.findViewById(R.id.btnDialogXN);

                dialog_confirm_content.setText("Bạn chắc chắn muốn xóa sản phẩm đã chọn!");

                btnDialogHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Hủy", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

                btnDialogXN.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Đã xóa Sản phẩm", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
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

    @Override
    public void onClick(View v) {

    }
}