package com.myapp.laporanadmin.ui.detaillaporanharian;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailHarianAdminFactory implements ViewModelProvider.Factory {
    private Context context;


    public DetailHarianAdminFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailHarianAdminViewModel.class)) {
            return (T) new DetailHarianAdminViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
