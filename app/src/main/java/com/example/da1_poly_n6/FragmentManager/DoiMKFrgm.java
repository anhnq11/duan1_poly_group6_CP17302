package com.example.da1_poly_n6.FragmentManager;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.da1_poly_n6.DAOModel.DAOUser;
import com.example.da1_poly_n6.Model.User;
import com.example.da1_poly_n6.R;

public class DoiMKFrgm extends Fragment {
    EditText edOldPass, edNewPass, edConfirmPass;
    EditText btnChange, btnCancel;
    DAOUser dao;
    String username, password;
    boolean chkCheck;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doi_m_k_frgm, container, false);
        ImageView btnBackDoiMK = view.findViewById(R.id.btnBackDoiMK);
        btnBackDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Account_Fragment());
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // ánh xạ
        edOldPass = view.findViewById(R.id.edOldPass);
        edNewPass = view.findViewById(R.id.edNewPass);
        edConfirmPass = view.findViewById(R.id.edConfirmPass);
        btnChange = view.findViewById(R.id.btnChange);
        btnCancel = view.findViewById(R.id.btnCancel);
        dao = new DAOUser(getActivity());
        getDataSSR();

        // sự kiện onclick
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate() > 0) {
                    User user = dao.getID(username);
                    String newPass = edNewPass.getText().toString();
                    user.setPassword(newPass);
                    dao.updatePass(user);
                    if (dao.updatePass(user) > 0) {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        resetForm();
                        remmemberUser(username, newPass, chkCheck);
                        getDataSSR();
                    } else {
                        Toast.makeText(getActivity(), "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private int validate() {
        int check = 1;
        if (edOldPass.getText().length() == 0) {
            Toast.makeText(getActivity(), "Nhập mật khẩu cũ", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (edNewPass.getText().length() == 0) {
            Toast.makeText(getActivity(), "Nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
            check = -1;
        } else if (edConfirmPass.getText().length() == 0) {
            Toast.makeText(getActivity(), "Nhập mật khẩu xác nhận", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String newpass = edNewPass.getText().toString();
            String confirmPass = edConfirmPass.getText().toString();
            if (!password.equals(edOldPass.getText().toString())) {
                Toast.makeText(getActivity(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!newpass.equals(confirmPass)) {
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void remmemberUser(String u, String p, boolean status) {
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status) {
            editor.clear();
        } else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", p);
            editor.putBoolean("REMEMBER", true);
        }
        editor.commit();
    }

//    Get Data SharedPreferences
    public void getDataSSR(){
        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        username = pref.getString("USERNAME", "");
        password = pref.getString("PASSWORD", "");
        chkCheck = pref.getBoolean("REMEMBER", false);
    }

//    Reset Edittext
    public void resetForm(){
        edOldPass.setText("");
        edNewPass.setText("");
        edConfirmPass.setText("");
    }

}