package com.myapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;


import com.myapp.databinding.ActivityRootBinding;
import com.myapp.laporanadmin.ui.homepage.HomePage;
import com.myapp.login.Login;


public class Root extends AppCompatActivity {
    private ActivityRootBinding binding;
    private HomePage homePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this, R.layout.activity_root);
       homePage = HomePage.newInstance();
       addFragment(homePage,null);
       // LaporanRepository.getInstance(Root.this).getDataHomepgae();
    }

    public void replaceFragment(Fragment f,String TAG){
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
        startActivity(new Intent(Root.this,Login.class));
        finish();
    }
}
