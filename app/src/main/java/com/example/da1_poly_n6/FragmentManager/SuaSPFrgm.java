package com.example.da1_poly_n6.FragmentManager;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.da1_poly_n6.Adapter_Package.AdapterSanPham;
import com.example.da1_poly_n6.DAOModel.DAOSanPham;
import com.example.da1_poly_n6.Model.SanPham;
import com.example.da1_poly_n6.Model.TheLoai;
import com.example.da1_poly_n6.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class SuaSPFrgm extends Fragment {

    EditText edUpdateTenSP, edUpdateGiaBan, edUpdateMoTa, btnUpdate;
    Spinner spnUpdateLoaiSP;
    SanPham sanPham;
    ImageView imgUpdate;
    DAOSanPham daoSanPham;
    String strTenSP, strMota;
    double strGiaban;
    ArrayList<SanPham> arrayList;
    AdapterSanPham adapter = null;


    public SuaSPFrgm(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sua_s_p_frgm, container, false);
        // ánh xạ
        ImageView btnBackSuaSP = view.findViewById(R.id.btnBackSuaSP);
        EditText btnSuaSPHuy = view.findViewById(R.id.btnSuaSPHuy);
        EditText btnSuaSPXN = view.findViewById(R.id.btnUpdateSp);
        imgUpdate = (ImageView) view.findViewById(R.id.update_img);
        edUpdateTenSP = view.findViewById(R.id.update_tenSP);
        edUpdateGiaBan = view.findViewById(R.id.update_giaBan);
        edUpdateMoTa = view.findViewById(R.id.update_moTa);
        spnUpdateLoaiSP = view.findViewById(R.id.update_spnLoai);
        btnUpdate = view.findViewById(R.id.btnUpdateSp);
        daoSanPham = new DAOSanPham(getContext());
        arrayList = new ArrayList<>();
        adapter = new AdapterSanPham(getActivity(), arrayList);
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
                LayAnh();
            }
        });
        //        Set Data cho spnLoaiSP - AnhNQ
        ArrayList<TheLoai> listLsp = daoSanPham.getDSLSP();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (TheLoai lsp : listLsp) {
            HashMap<String, Object> tl = new HashMap<>();
            tl.put("maLoai", lsp.getMaLoai());
            tl.put("tenLoai", lsp.getTenLoai());
            listHM.add(tl);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(),
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"tenLoai"},
                new int[]{android.R.id.text1});
        spnUpdateLoaiSP.setAdapter(simpleAdapter);


        // set sự kiện
        btnBackSuaSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ChiTietSPSuaFrgm(sanPham));
            }
        });

        btnSuaSPHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hủy!", Toast.LENGTH_SHORT).show();
            }
        });

        btnSuaSPXN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialogUpdate();
            }
        });

        return view;
    }

    private void showDialogUpdate() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView dialog_confirm_content = dialog.findViewById(R.id.dialog_confirm_content);
        EditText btnDialogHuy = dialog.findViewById(R.id.btnDialogHuy);
        EditText btnUpdate = dialog.findViewById(R.id.btnDialogXN);

        dialog_confirm_content.setText("Bạn chắc chắn muốn sửa thông tin sản phẩm đã chọn!");

        btnDialogHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hủy!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        // ánh xạ 1
        strTenSP = edUpdateTenSP.getText().toString();
        strGiaban = Double.parseDouble(edUpdateGiaBan.getText().toString());
        strMota = edUpdateMoTa.getText().toString();
        HashMap<String, Object> hsLoaiSach = (HashMap<String, Object>) spnUpdateLoaiSP.getSelectedItem();
        int maLSP = (int) hsLoaiSach.get("maLoai");

        //Update 
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (checkEdt()){
                   daoSanPham.updateSanPham(imageToByte(imgUpdate), strTenSP, strGiaban, maLSP, strMota, sanPham.getId());
                   dialog.dismiss();
                   Toast.makeText(getActivity(), "Sửa thành công", Toast.LENGTH_SHORT).show();
                   adapter.notifyDataSetChanged();
               }
            }

        });
        dialog.show();
    }



    //Cấp quyền lấy ảnh
    private void LayAnh() {
        //cấp quyền từ người dùng
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
            //cho phép sử dụng
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);//truy cập vào bộ nhớ của máy
            intent.setType("image/*");
            startActivityForResult(intent, 888);
        }
    }

    Bitmap imgChose = null;

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 888 && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                imgChose = BitmapFactory.decodeStream(inputStream); // lấy ảnh từ bộ nhớ
                imgUpdate.setImageBitmap(imgChose);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //image to byte
    private byte[] imageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }

    //replaceFragment
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    //    Check Form
    private boolean checkEdt() {

        boolean checkAdd = true;
        if (strTenSP.isEmpty()) {
            edUpdateTenSP.setError("Vui lòng nhập!");
            edUpdateTenSP.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (edUpdateGiaBan.getText().toString().isEmpty()) {
            edUpdateGiaBan.setError("Vui lòng nhập!");
            edUpdateGiaBan.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (strMota.isEmpty()) {
            edUpdateMoTa.setError("Vui lòng nhập!");
            edUpdateMoTa.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        return checkAdd;
    }

    //    Reset Edittext
    private void resetEdt() {
        imgUpdate.setImageResource(R.drawable.img_add_img);
        edUpdateTenSP.setText("");
        edUpdateTenSP.setHintTextColor(Color.BLACK);
        edUpdateGiaBan.setText("");
        edUpdateGiaBan.setHintTextColor(Color.BLACK);
        edUpdateMoTa.setText("");
        edUpdateMoTa.setHintTextColor(Color.BLACK);
    }
}