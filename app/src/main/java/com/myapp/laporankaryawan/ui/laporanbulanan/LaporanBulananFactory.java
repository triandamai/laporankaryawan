package com.myapp.laporankaryawan.ui.laporanbulanan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public  class LaporanBulananFactory implements ViewModelProvider.Factory {
    private Context context;
    public LaporanBulananFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LaporanBulananViewModel.class)) {
            return (T) new LaporanBulananViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
