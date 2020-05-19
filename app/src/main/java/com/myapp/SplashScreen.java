package com.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.data.persistensi.MyUser;
import com.myapp.domain.model.UserModel;
import com.myapp.laporanadmin.RootAdmin;
import com.myapp.laporankaryawan.RootKaryawan;
import com.myapp.login.Login;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        UserModel userModel = MyUser.getInstance(getApplicationContext()).getUser();
        new Handler().postDelayed(() -> {
            if (userModel == null) {
                startActivity(new Intent(SplashScreen.this, Login.class));
                finish();
            } else {
                if (userModel.getLevelUser().equalsIgnoreCase("2")) {
                    startActivity(new Intent(SplashScreen.this, RootAdmin.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashScreen.this, RootKaryawan.class));
                    finish();
                }
            }
        }, 3000);
    }
}
