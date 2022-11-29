package com.example.da1_poly_n6.Adapter_Package;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_poly_n6.R;

public class AdapterHoaDon extends RecyclerView.Adapter<AdapterGioHang.ViewHolder>{

    @NonNull
    @Override
    public AdapterGioHang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGioHang.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtHDTenSP, txtHDDonGia, txtHDSL;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHDTenSP = itemView.findViewById(R.id.txtHDTenSP);
            txtHDDonGia = itemView.findViewById(R.id.txtHDDonGia);
            txtHDSL = itemView.findViewById(R.id.txtHDSL);
        }
    }
}
