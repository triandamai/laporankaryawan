package com.myapp.laporankaryawan.ui.laporanharian;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public  class LaporanHarianFactory implements ViewModelProvider.Factory {
    private Context context;
    public LaporanHarianFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LaporanHarianViewModel.class)) {
            return (T) new LaporanHarianViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
