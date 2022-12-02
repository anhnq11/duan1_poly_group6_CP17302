package com.example.da1_poly_n6.FragmentManager;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Adapter_Package.AdapterTKDT;
import com.example.da1_poly_n6.DAOModel.DAOLuuHD;
import com.example.da1_poly_n6.Model.LuuHoaDon;
import com.example.da1_poly_n6.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TKDoanhThuFrgm extends Fragment {

    EditText edtTuNgay,edtDenNgay,btnThongKe;
    ImageView btnBackTKDT;
    TextView txtTongDoanhThu;
    boolean ipDateStart, ipDateEnd;
    String dateStart, dateEnd;
    DAOLuuHD daoLuuHD;
    RecyclerView recycler_TKDT;
    ArrayList<LuuHoaDon> listHD = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_k_doanh_thu_frgm, container, false);

        btnBackTKDT = view.findViewById(R.id.btnBackTKDT);
        edtTuNgay = view.findViewById(R.id.edtTKDTStart);
        edtDenNgay = view.findViewById(R.id.edtTKDTEnd);
        btnThongKe = view.findViewById(R.id.edtThongKeDoanhThu);
        txtTongDoanhThu = view.findViewById(R.id.txtTongDoanhThu);
        recycler_TKDT = view.findViewById(R.id.recycler_TKDT);

        daoLuuHD = new DAOLuuHD(getContext());

        btnBackTKDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Account_Fragment());
            }
        });

//        Get ngày bắt đầu
        edtTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(calendar.DAY_OF_MONTH);
                int thang = calendar.get(calendar.MONTH);
                int nam = calendar.get(calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        String date = "";
                        calendar.set(mYear, mMonth, mDay);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy 00:00:00");
                        date = simpleDateFormat.format(calendar.getTime());
                        String subDate = date.substring(0,10);
                        edtTuNgay.setText(subDate);
                        dateStart = date;
                        ipDateStart = true;
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

//        Get ngày kết thúc
        edtDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int ngay = calendar.get(calendar.DAY_OF_MONTH);
                int thang = calendar.get(calendar.MONTH);
                int nam = calendar.get(calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        calendar.set(mYear, mMonth, mDay);
                        String date = "";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy 23:59:59");
                        date = simpleDateFormat.format(calendar.getTime());
                        String subDate = date.substring(0,10);
                        edtDenNgay.setText(subDate);
                        dateEnd = date;
                        ipDateEnd = true;
                    }
                }, nam, thang, ngay);
                datePickerDialog.show();
            }
        });

//        Sự kiện Click button Thống kê
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Check trống ngày bắt đầu & ngày kết thúc
                boolean checkDoanhThu = true;
                if (!ipDateStart){
                    checkDoanhThu = false;
                    Toast.makeText(getContext(), "Chọn ngày bắt đầu!", Toast.LENGTH_SHORT).show();
                    edtTuNgay.setError("Vui lòng nhập!");
                }
                if (!ipDateEnd){
                    checkDoanhThu = false;
                    Toast.makeText(getContext(), "Chọn ngày kết thúc!", Toast.LENGTH_SHORT).show();
                    edtDenNgay.setError("Vui lòng nhập!");
                }
                if (checkDoanhThu){
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    Date dDateS = null;
                    try {
                        dDateS = simpleDateFormat.parse(dateStart);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date dDateE = null;
                    try {
                        dDateE = simpleDateFormat.parse(dateEnd);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    Check ngày bắt đầu <= ngày kết thúc
                    if (dDateS.before(dDateE)){
//                        Gọi thống kê tổng doanh thu
                        double doanhThu = daoLuuHD.getTongDoanhThu(dateStart, dateEnd);
                        String fDoanhThu = String.format("%,.0f VNĐ", doanhThu);
                        txtTongDoanhThu.setText(fDoanhThu);
//                        Get ArrayList
                        listHD = daoLuuHD.getDSHoaDon(dateStart, dateEnd);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                        recycler_TKDT.setLayoutManager(layoutManager);
                        AdapterTKDT adapterTKDT = new AdapterTKDT(getContext(), listHD);
                        recycler_TKDT.setAdapter(adapterTKDT);
                    }
                    else {
                        Toast.makeText(getContext(), "Ngày bắt đầu phải nhỏ hơn hoặc bằng ngày kết thúc", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}