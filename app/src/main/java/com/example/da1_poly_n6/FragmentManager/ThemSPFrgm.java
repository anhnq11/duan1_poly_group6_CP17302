package com.example.da1_poly_n6.FragmentManager;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.da1_poly_n6.DAOModel.DAOSanPham;
import com.example.da1_poly_n6.Database.DbHelper;
import com.example.da1_poly_n6.Model.TheLoai;
import com.example.da1_poly_n6.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class ThemSPFrgm extends Fragment {

    private ImageView AddImg;
    private EditText edName, edPrice, edMoTa, btnAddSP,btnHuySP;
    Spinner spnLoaiSP;
    private DAOSanPham daoSanPham;
    final int REQUEST_CODE_GALLERY = 999;

    String strTenSP, strGiaban, strMota;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_s_p_frgm, container, false);
        //ánh xạ
        daoSanPham = new DAOSanPham(getActivity());
        ImageView btnBackThemSP = view.findViewById(R.id.btnBackThemSP);
        AddImg = view.findViewById(R.id.add_image);
        edName = view.findViewById(R.id.edNameSP);
        edPrice = view.findViewById(R.id.edPrice);
        edMoTa = view.findViewById(R.id.edMoTa);
        spnLoaiSP = view.findViewById(R.id.spnLoaiSP);
        btnAddSP = view.findViewById(R.id.btnAcceptSP);
        btnHuySP = view.findViewById(R.id.btnHuySp);

        btnBackThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Account_Fragment());
            }
        });
        AddImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);
                LayAnh();
            }
        });

//        Set Data cho spnLoaiSP - AnhNQ
        ArrayList<TheLoai> listLsp = daoSanPham.getDSLSP();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (TheLoai lsp : listLsp){
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
        spnLoaiSP.setAdapter(simpleAdapter);

//        Set sự kiện Click Button Thêm
        btnAddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strTenSP = edName.getText().toString();
                strGiaban = edPrice.getText().toString();
                strMota = edMoTa.getText().toString();

                HashMap<String, Object> hsLoaiSach = (HashMap<String, Object>) spnLoaiSP.getSelectedItem();
                int maLSP = (int) hsLoaiSach.get("maLoai");

                if (checkEdt()){
                    daoSanPham.insertData(imageToByte(AddImg), strTenSP, Double.parseDouble(strGiaban), maLSP, strMota);
                    Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    resetEdt();
                }
            }
        });

//        Set sự kiện Click Button Hủy
        btnHuySP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetEdt();
            }
        });
        return view;
    }

    private byte[] imageToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }

    private void LayAnh() {
        //cấp quyền từ người dùng
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 999);
            //cho phép sử dụng
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK);//truy cập vào bộ nhớ của máy
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_CODE_GALLERY);
        }
    }

    Bitmap imgChose = null;

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                imgChose = BitmapFactory.decodeStream(inputStream); // lấy ảnh từ bộ nhớ
                AddImg.setImageBitmap(imgChose);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    Reset Edittext
    private void resetEdt(){
        AddImg.setImageResource(R.drawable.img_add_img);
        edName.setText("");
        edName.setHintTextColor(Color.BLACK);
        edPrice.setText("");
        edPrice.setHintTextColor(Color.BLACK);
        edMoTa.setText("");
        edMoTa.setHintTextColor(Color.BLACK);
    }

//    Check Form
    private boolean checkEdt(){

        boolean checkAdd = true;
        if (strTenSP.isEmpty()){
            edName.setError("Vui lòng nhập!");
            edName.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (strGiaban.isEmpty()){
            edPrice.setError("Vui lòng nhập!");
            edPrice.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        if (strMota.isEmpty()){
            edMoTa.setError("Vui lòng nhập!");
            edPrice.setHintTextColor(Color.RED);
            checkAdd = false;
        }

        return checkAdd;
    }

}