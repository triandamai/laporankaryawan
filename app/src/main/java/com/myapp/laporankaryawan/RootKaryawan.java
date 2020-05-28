package com.myapp.laporankaryawan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.myapp.R;
import com.myapp.databinding.ActivityRootKaryawanBinding;
import com.myapp.laporankaryawan.ui.homepage.HomeKaryawan;
import com.myapp.login.Login;

public class RootKaryawan extends AppCompatActivity {
    private ActivityRootKaryawanBinding binding;
    private HomeKaryawan homeKaryawan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_root_karyawan);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root_karyawan);
        homeKaryawan = HomeKaryawan.newInstance();
        if(savedInstanceState == null){
            addFragment(homeKaryawan,null);
        }
    }
    public void replaceFragment(Fragment f, String TAG){
        getSupportFragmentManager().beginTransaction().replace(R.id.root,f)
                .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                .addToBackStack(TAG)
                .commit();
    }
    public void addFragment(Fragment f,String TAG){
        getSupportFragmentManager().beginTransaction().add(R.id.root,f)
                .setCustomAnimations(android.R.animator.fade_in,android.R.animator.fade_out)
                .addToBackStack(TAG)
                .commit();
    }
    public void onBack(){
        super.onBackPressed();
    }

    public void onSignOut(){

        startActivity(new Intent(RootKaryawan.this, Login.class));
        finish();
    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }

    }
}
