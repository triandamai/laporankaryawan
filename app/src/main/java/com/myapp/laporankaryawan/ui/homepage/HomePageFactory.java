package com.myapp.laporankaryawan.ui.homepage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.ui.login.LoginViewModel;

public  class HomePageFactory implements ViewModelProvider.Factory {
    private Context context;
    public HomePageFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomePageViewModel.class)) {
            return (T) new HomePageViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
