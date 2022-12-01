package com.example.da1_poly_n6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.da1_poly_n6.DAOModel.DAOUser;
import com.example.da1_poly_n6.FragmentManager.AccountFrgm;
import com.example.da1_poly_n6.FragmentManager.Account_Fragment;
import com.example.da1_poly_n6.FragmentManager.HomeFrgm;
import com.example.da1_poly_n6.FragmentManager.ProductFrgm;
import com.example.da1_poly_n6.FragmentManager.StoreFrgm;
import com.example.da1_poly_n6.Model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.pageTrangChu);
        loadFragment(new HomeFrgm());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.pageTrangChu:
                fragment = new HomeFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageSanPham:
                fragment = new ProductFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageBanHang:
                fragment = new StoreFrgm();
                loadFragment(fragment);
                return true;

            case R.id.pageTaiKhoan:
                loadFragment(new Account_Fragment());
                return true;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}