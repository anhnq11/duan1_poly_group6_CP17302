package com.example.da1_poly_n6.FragmentManager;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.da1_poly_n6.R;

public class UserInfoFrgm extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_info_frgm, container, false);
        ImageView btnBackUserInfo = view.findViewById(R.id.btnBackUserInfo);
        btnBackUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new Account_Fragment());
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = (getActivity()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}