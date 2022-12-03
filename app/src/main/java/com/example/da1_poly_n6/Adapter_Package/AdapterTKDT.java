package com.example.da1_poly_n6.Adapter_Package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_poly_n6.Model.HoaDon;
import com.example.da1_poly_n6.Model.LuuHoaDon;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class AdapterTKDT extends RecyclerView.Adapter<AdapterTKDT.ViewHolder>{

    ArrayList<LuuHoaDon> listHoaDon;
    Context context;

    public AdapterTKDT(Context context, ArrayList<LuuHoaDon> listHoaDon){
        this.context = context;
        this.listHoaDon = listHoaDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thongkedoanhthu, parent, false);
        return new AdapterTKDT.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LuuHoaDon hoaDon = listHoaDon.get(position);
        holder.txtTkdtTenSP.setText(hoaDon.getTenKhachHang());
        double doanhThu = hoaDon.getThanhTien();
        String outDoanhThu = String.format("%,.0f", doanhThu);
        String subDoanhThu = outDoanhThu.substring(0, (outDoanhThu.length() - 4));
        holder.txtTkdtThanhTien.setText(subDoanhThu + "K VNĐ");

        if (position == (listHoaDon.size() - 1)){
            holder.bottomViewTkdt.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTkdtTenSP, txtTkdtThanhTien;
        View bottomViewTkdt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTkdtTenSP = itemView.findViewById(R.id.txtTkdtTenSP);
            txtTkdtThanhTien = itemView.findViewById(R.id.txtTkdtThanhTien);
            bottomViewTkdt = itemView.findViewById(R.id.bottomViewTkdt);
        }
    }
}
