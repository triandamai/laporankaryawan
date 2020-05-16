package com.myapp.laporanadmin.ui.datakota;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporanadmin.ui.datapegawai.DataPegawaiViewModel;

public  class DataKotaFactory implements ViewModelProvider.Factory {
    private Context context;
    public DataKotaFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DataKotaViewModel.class)) {
            return (T) new DataKotaViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
