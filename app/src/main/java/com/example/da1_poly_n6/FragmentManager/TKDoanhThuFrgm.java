package com.example.da1_poly_n6.FragmentManager;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.da1_poly_n6.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TKDoanhThuFrgm extends Fragment {

    int mYear,mMonth,mDay;
    EditText edtTuNgay,edtDenNgay,edtThongKe;
    ImageView btnBackTKDT;
    int temp=0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_k_doanh_thu_frgm, container, false);

        btnBackTKDT = view.findViewById(R.id.btnBackTKDT);
        edtTuNgay=view.findViewById(R.id.edtTKDTStart);
        edtDenNgay=view.findViewById(R.id.edtTKDTEnd);
        edtThongKe=view.findViewById(R.id.edtThongKeDoanhThu);

        btnBackTKDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new AccountFrgm());
            }
        });

        edtTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        edtDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
                d.show();
            }
        });
        edtThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tuNgay = edtTuNgay.getText().toString();
                String denNgay = edtDenNgay.getText().toString();
                if (tuNgay.isEmpty()||denNgay.isEmpty()){
                    Toast.makeText(getActivity(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    temp++;
                }else {
                    String[] temptungay = tuNgay.split("-");
                    String[] tempdenngay = denNgay.split("-");

                    String newTungay = "";
                    String newdenngay = "";

                    int inttungay = Integer.parseInt(newTungay.concat(temptungay[0]).concat(temptungay[1]).concat(temptungay[2]));
                    int intdenngay = Integer.parseInt(newdenngay.concat(tempdenngay[0]).concat(tempdenngay[1]).concat(tempdenngay[2]));

                    if (inttungay > intdenngay) {
                        Toast.makeText(getActivity(), "Lỗi, từ ngày phải bé hơn đến ngày", Toast.LENGTH_SHORT).show();
                        temp++;
                    }
                }

                if (temp==0){
                    //chờ dao thống kê
                  /*  ThongKeDAO dao = new ThongKeDAO(getActivity());
                    tv.setText("Doanh thu: "+dao.getDoanhThu(tuNgay,denNgay)+" VNĐ");*/
                }else {
                    temp=0;
                }
            }

        });

        return view;
    }

    DatePickerDialog.OnDateSetListener mDateTuNgay= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mYear= year;
            mMonth=month;
            mDay=day;
            GregorianCalendar gregorianCalendar= new GregorianCalendar(mYear,mMonth,mDay);
            edtTuNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
        }
    };

    DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mYear = year;
            mMonth = month;
            mDay = day;
            GregorianCalendar gregorianCalendar = new GregorianCalendar(mYear,mMonth,mDay);
            edtDenNgay.setText(simpleDateFormat.format(gregorianCalendar.getTime()));
        }
    };
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}