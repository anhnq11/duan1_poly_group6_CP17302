package com.example.da1_poly_n6.Adapter_Package;

import android.content.Context;
import android.text.style.AlignmentSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_poly_n6.DAOModel.DAOGioHang;
import com.example.da1_poly_n6.Model.GioHang;
import com.example.da1_poly_n6.Model.LuuHoaDon;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class AdapterTKNV extends RecyclerView.Adapter<AdapterTKNV.ViewHolder>{

    ArrayList<LuuHoaDon> list;
    Context context;

    public AdapterTKNV(Context context, ArrayList<LuuHoaDon> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thongkenhanvien, parent, false);
        return new AdapterTKNV.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LuuHoaDon luuHoaDon = list.get(position);
        String index = "";
        if (position < 9){
            index = "0" + (position + 1);
        }
        else{
            index = String.valueOf(position + 1);
        }
        holder.txtTknvSTT.setText(index);
        holder.txtTknvTenNv.setText(luuHoaDon.getTenUser());
        double doanhThu = luuHoaDon.getThanhTien();
        String outTongTien = String.format("%,.0f", doanhThu);
        holder.txtTknvDoanhThu.setText(outTongTien + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTknvSTT, txtTknvTenNv, txtTknvDoanhThu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTknvSTT = itemView.findViewById(R.id.txtTknvSTT);
            txtTknvTenNv = itemView.findViewById(R.id.txtTknvTenNv);
            txtTknvDoanhThu = itemView.findViewById(R.id.txtTknvDoanhThu);
        }
    }

}
