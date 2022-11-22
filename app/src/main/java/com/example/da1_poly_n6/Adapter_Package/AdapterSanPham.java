package com.example.da1_poly_n6.Adapter_Package;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.da1_poly_n6.FragmentManager.ChiTietSPFrgm;
import com.example.da1_poly_n6.FragmentManager.ChiTietSPSuaFrgm;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.R;

import java.util.ArrayList;

public class AdapterSanPham extends RecyclerView.Adapter<AdapterSanPham.UserViewHolder> {

    private Context context;
    private ArrayList<SanPham> arrayList;
    private CardView cardView;

    public AdapterSanPham(Context context) {
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
                .inflate(R.layout.card_view_sanpham, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        SanPham sanPham = arrayList.get(position);
        holder.TenSanPham.setText(sanPham.getTenSanPham());
        holder.GiaTien.setText(String.valueOf(sanPham.getDonGia()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ChiTietSPFrgm(sanPham));
            }
        });
        holder.add_sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng!", Toast.LENGTH_SHORT).show();
            }
        });
        holder.info_sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ChiTietSPSuaFrgm(sanPham));
            }
        });
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
        private ImageView add_sanpham, info_sanpham;
        private TextView TenSanPham;
        private TextView GiaTien;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview_sanPham);
            info_sanpham = itemView.findViewById(R.id.info_sanpham);
            add_sanpham = itemView.findViewById(R.id.add_sanpham);
            TenSanPham = itemView.findViewById(R.id.ten_sanpham);
            GiaTien = itemView.findViewById(R.id.gia_sanpham);
        }
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
