package com.myapp.laporankaryawan.ui.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    public static String TAG = "Login";
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this,new LoginFactory(getApplicationContext())).get(LoginViewModel.class);
    }
}
