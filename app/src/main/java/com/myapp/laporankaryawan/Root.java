package com.myapp.laporankaryawan;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.myapp.laporankaryawan.databinding.ActivityRootBinding;
import com.myapp.laporankaryawan.ui.homepage.HomePage;

public class Root extends AppCompatActivity {
    private ActivityRootBinding binding;
    private HomePage homePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(this,R.layout.activity_root);
       homePage = HomePage.newInstance();
       addFragment(homePage,null);
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
}
