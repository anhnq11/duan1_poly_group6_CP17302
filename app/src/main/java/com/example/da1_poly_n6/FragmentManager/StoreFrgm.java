package com.example.da1_poly_n6.FragmentManager;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Adapter_Package.AdapterGioHang;
import com.example.da1_poly_n6.Adapter_Package.AdapterHoaDon;
import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.DAOModel.DAOGioHang;
import com.example.da1_poly_n6.DAOModel.DAOHoaDon;
import com.example.da1_poly_n6.DAOModel.DAOLuuHD;
import com.example.da1_poly_n6.DAOModel.DAOUser;
import com.example.da1_poly_n6.Model.GioHang;
import com.example.da1_poly_n6.Model.HoaDon;
import com.example.da1_poly_n6.Model.LuuHoaDon;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.Model.User;
import com.example.da1_poly_n6.R;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class StoreFrgm extends Fragment {

    RecyclerView recycle_gioHang;
    DAOGioHang daoGioHang;
    DAOUser daoUser;
    DAOHoaDon daoHoaDon;
    DAOLuuHD daoLuuHD;
    ArrayList<GioHang> listGioHang;
    public static TextView txtGHTongTien;
    double tongTien = 0;
    EditText edtGHTenKH;
    ImageView iconRefreshStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store_frgm, container, false);

        edtGHTenKH = view.findViewById(R.id.edtGHTenKH);
        daoGioHang = new DAOGioHang(getContext());
        daoUser = new DAOUser(getContext());
        daoHoaDon = new DAOHoaDon(getContext());
        daoLuuHD = new DAOLuuHD(getContext());
        recycle_gioHang = view.findViewById(R.id.recycle_giohang);
        listGioHang = daoGioHang.getGioHang();
        txtGHTongTien = view.findViewById(R.id.txtGHTongTien);
        iconRefreshStore = view.findViewById(R.id.iconRefreshStore);
        createData();
        iconRefreshStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listGioHang = daoGioHang.getGioHang();
                int listGHSize = listGioHang.size();
                if (listGHSize != 0){
                    Dialog dialog = new Dialog(getActivity());
                    dialog.setContentView(R.layout.dialog_confirm);
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    TextView dialog_confirm_content = dialog.findViewById(R.id.dialog_confirm_content);
                    EditText btnDialogHuy = dialog.findViewById(R.id.btnDialogHuy);
                    EditText btnDialogXN = dialog.findViewById(R.id.btnDialogXN);

                    dialog_confirm_content.setText("B???n ch???c ch???n mu???n x??a s???n ph???m trong gi??? h??ng!");

//                Set Click Button Dialog H???y
                    btnDialogHuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "H???y", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });

                    btnDialogXN.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (int i = 0; i < listGHSize; i++) {
                                daoGioHang.deleteGiohang(listGioHang.get(i));
                            }
                            createData();
                            txtGHTongTien.setText("0 VN??");
                            Toast.makeText(getContext(), "???? x??a gi??? h??ng!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    
                    
                }
            }
        });


        EditText btnGioHangTT = view.findViewById(R.id.btnGioHangTT);
        btnGioHangTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenKH = edtGHTenKH.getText().toString();
                createData();
                if (listGioHang.size() == 0){
                    Toast.makeText(getContext(), "Vui l??ng ch???n s???n ph???m!", Toast.LENGTH_SHORT).show();
                }
                else {
                    //                Ki???m tra nh???p t??n kh??ch h??ng
                    if (tenKH.isEmpty()){
                        edtGHTenKH.setHintTextColor(Color.RED);
                        edtGHTenKH.setError("Vui l??ng nh???p!");
                    }
                    else {
                        edtGHTenKH.setHintTextColor(Color.BLACK);

//                    L???y t??n nh??n vi??n
                        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", getActivity().MODE_PRIVATE);
                        int maUser = pref.getInt("MA", 0);
                        User user = daoUser.getUser(maUser);
                        String fullName = user.getFullName();

//                    L???y ng??y t???o h??a ????n
                        Date nowDate = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                        String ngayTaoHD = simpleDateFormat.format(nowDate);

//                L???y th??ng tin h??a ????n - Hi???n th??? l??n dialog
                        Dialog dialog = new Dialog(getActivity());
                        dialog.setContentView(R.layout.dialog_thanh_toan);
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//                    ??nh x??? View

                        EditText btnHoaDonHuy = dialog.findViewById(R.id.btnHoaDonHuy);
                        EditText btnHoaDonXN = dialog.findViewById(R.id.btnHoaDonXN);

                        TextView txtHDTenNV = dialog.findViewById(R.id.txtHDTenNV);
                        TextView txtHDTenKH = dialog.findViewById(R.id.txtHDTenKH);
                        TextView txtHDNgayBan = dialog.findViewById(R.id.txtHDNgayBan);
                        RecyclerView recycle_hoaDon = dialog.findViewById(R.id.recycle_hoaDon);
                        TextView txtHDTongTien = dialog.findViewById(R.id.txtHDTongTien);

//                    Settext cho c??c View

                        txtHDTenNV.setText(fullName);
                        txtHDTenKH.setText(tenKH);
                        txtHDNgayBan.setText(ngayTaoHD);
                        String outTongTien = String.format("%,.0f", tongTien);
                        txtHDTongTien.setText(outTongTien + "??");

                        listGioHang = daoGioHang.getGioHang();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recycle_hoaDon.setLayoutManager(linearLayoutManager);
                        AdapterHoaDon adapterHoaDon = new AdapterHoaDon(getContext(), listGioHang);
                        recycle_hoaDon.setAdapter(adapterHoaDon);

//                S??? ki???n Button H???y
                        btnHoaDonHuy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

//                S??? ki???n Button X??c nh???n -> Chuy???n H??a ????n v??o b???ng L??u h??a ????n
                        btnHoaDonXN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                            T???o Model HoaDon, Th??m v??o b???ng L??u H??a ????n
                                HoaDon hoaDon = new HoaDon(maUser, tenKH, ngayTaoHD, 1);
                                boolean check = daoHoaDon.addHoaDon(hoaDon);
                                if (!check){
                                    Toast.makeText(getContext(), "Fail!", Toast.LENGTH_SHORT).show();
                                }
                                ArrayList<HoaDon> listHoaDon = daoHoaDon.getHoaDon();
                                int listHDSize = listHoaDon.size();
//                            L???y ra danh s??ch H??a ????n
                                if (listHDSize > 0){
                                    boolean checkLuuHD = true;
                                    for (int i = 0; i < listHDSize; i++) {
                                        HoaDon hoaDonModel = listHoaDon.get(i);
//                                    T???o Model L??u H??a ????n
                                        LuuHoaDon luuHoaDon = new LuuHoaDon(hoaDonModel.getMaHoaDon(),
                                                hoaDonModel.getMaUser(),
                                                hoaDonModel.getTenUser(),
                                                hoaDonModel.getTenKhachHang(),
                                                hoaDonModel.getNgayLapHD(),
                                                hoaDonModel.getMaSP(),
                                                hoaDonModel.getTenSP(),
                                                hoaDonModel.getSoLuong(),
                                                hoaDonModel.getSize(),
                                                hoaDonModel.getDonGia(),
                                                hoaDonModel.getThanhTien());
//                                    L??u h??a ????n v??o b???ng L??u H??a ????n
                                        boolean checkAddHD = daoLuuHD.addLuuHD(luuHoaDon);
                                        if (!checkAddHD){
                                            Toast.makeText(getContext(), "L??u HD Fail!", Toast.LENGTH_SHORT).show();
                                            checkLuuHD = false;
                                        }
                                    }
                                    if (checkLuuHD){
//                                    L??u h??a ????n th??nh c??ng -> X??a th??ng tin gi??? h??ng, h??a ????n
                                        int listGHSize = listHoaDon.size();
                                        if (listGHSize != 0){
                                            for (int i = 0; i < listGHSize; i++) {
                                                daoGioHang.deleteGiohang(listGioHang.get(i));
                                            }
                                        }
                                        if (listHDSize != 0){
                                            for (int i = 0; i < listHDSize; i++) {
                                                daoHoaDon.deleteHoaDon(listHoaDon.get(i));
                                            }
                                        }
                                        createData();
                                        Toast.makeText(getContext(), "Mua h??ng th??nh c??ng!", Toast.LENGTH_SHORT).show();
                                        txtGHTongTien.setText("0 VN??");
                                        dialog.dismiss();
                                    }
                                }
                            }
                        });
                        dialog.show();
                    }
                }
            }
        });

        return view;
    }

    private void createData() {
        daoGioHang = new DAOGioHang(getContext());
        listGioHang = daoGioHang.getGioHang();
        if (listGioHang.size() == 0){
            recycle_gioHang.setVisibility(View.GONE);
        }
        else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            recycle_gioHang.setLayoutManager(linearLayoutManager);

            tongTien = daoGioHang.tongTienGiohang();
            String outTongTien = String.format("%,.0f", tongTien);
            txtGHTongTien.setText(outTongTien + " VN??");

            edtGHTenKH.setText(null);
            edtGHTenKH.setError(null);

            AdapterGioHang adapterGioHang = new AdapterGioHang(getContext(), listGioHang);
            recycle_gioHang.setAdapter(adapterGioHang);
        }
    }

}