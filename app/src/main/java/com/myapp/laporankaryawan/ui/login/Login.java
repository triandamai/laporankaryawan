package com.myapp.laporankaryawan.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.R;
import com.myapp.laporankaryawan.Root;
import com.myapp.laporankaryawan.callback.SendDataListener;
import com.myapp.laporankaryawan.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    public static String TAG = "Login";
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setIsLoading(false);
        loginViewModel = new ViewModelProvider(this,new LoginFactory(getApplicationContext())).get(LoginViewModel.class);
        binding.setVm(loginViewModel);
        loginViewModel.setSaveListener(sendDataListener);

    }
    private SendDataListener sendDataListener = new SendDataListener() {
        @Override
        public void onStart() {
            binding.setIsLoading(true);
        }

        @Override
        public void onSuccess(String level) {
            binding.setIsLoading(false);
            if(level.equalsIgnoreCase("2")){
            startActivity(new Intent(Login.this, Root.class));
            finish();
            }else {
                Intent intent = new Intent();
                intent.setClassName(Login.this,"");
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
        }
    };
}
