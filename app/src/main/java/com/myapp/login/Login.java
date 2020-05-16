package com.myapp.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.myapp.R;
import com.myapp.laporanadmin.Root;
import com.myapp.databinding.ActivityLoginBinding;
import com.myapp.laporanadmin.callback.SendDataListener;
import com.myapp.laporankaryawan.ui.beranda.Main;

public class Login extends AppCompatActivity {
    public static String TAG = "Login";
    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private MaterialAlertDialogBuilder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setIsLoading(false);
        loginViewModel = new ViewModelProvider(this, new LoginFactory(getApplicationContext())).get(LoginViewModel.class);
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
            if (level.equalsIgnoreCase("2")) {
                startActivity(new Intent(Login.this, Root.class));
                finish();
            } else {
                startActivity(new Intent(Login.this, Main.class));
                finish();
            }
        }

        @Override
        public void onFailed(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }

        @Override
        public void onError(String message) {
            binding.setIsLoading(false);
            dialogGagal(message);
        }
    };

    protected void dialogGagal(String message) {

        builder = new MaterialAlertDialogBuilder(Login.this, R.style.dialog);
        builder.create();
        builder.setTitle("Oops!");
        builder.setMessage(message);
        builder.setPositiveButton("Oke", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

}
