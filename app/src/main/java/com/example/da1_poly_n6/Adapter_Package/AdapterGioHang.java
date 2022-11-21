package com.example.da1_poly_n6.Adapter_Package;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.UserViewHolder> {

    private Context context;
    private ArrayList<SanPham> arrayList;
    private CardView cardView;

    public AdapterGioHang(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<SanPham> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_gio_hang, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SanPham sanPham = arrayList.get(position);
        holder.name.setText(sanPham.getTenSanPham());
        holder.price.setText(String.valueOf(sanPham.getDonGia()));
    }

    @Override
    public int getItemCount() {
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView btn_tru, btn_cong;
        private TextView name;
        private TextView price;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view_gioHang);
            btn_cong = itemView.findViewById(R.id.btn_cong);
            btn_tru = itemView.findViewById(R.id.btn_tru);
            name = itemView.findViewById(R.id.name_SP);
            price = itemView.findViewById(R.id.price_SP);
        }
    }
}
