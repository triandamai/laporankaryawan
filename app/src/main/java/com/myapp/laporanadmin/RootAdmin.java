package com.myapp.laporanadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.myapp.R;
import com.myapp.databinding.ActivityRootAdminBinding;
import com.myapp.laporanadmin.ui.homepage.HomePage;
import com.myapp.login.Login;

public class RootAdmin extends AppCompatActivity {
    private ActivityRootAdminBinding binding;
    private HomePage homePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this, R.layout.activity_root_admin);
       homePage = HomePage.newInstance();
       if (savedInstanceState == null){
            addFragment(homePage,null);
       }
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

        startActivity(new Intent(RootAdmin.this,Login.class));
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
