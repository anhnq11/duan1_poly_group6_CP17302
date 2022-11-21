package com.example.da1_poly_n6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.da1_poly_n6.Package_Activity.ImageProducts;

public class MHChaoAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mhchao);

        Intent intent = new Intent(MHChaoAct.this, DangNhapAct.class);

        Thread timer = new Thread() {
            public void run(){
                try {
                    sleep(3000);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}