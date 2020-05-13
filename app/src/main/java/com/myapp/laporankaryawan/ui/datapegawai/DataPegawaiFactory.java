package com.myapp.laporankaryawan.ui.datapegawai;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public  class DataPegawaiFactory implements ViewModelProvider.Factory {
    private Context context;
    public DataPegawaiFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DataPegawaiViewModel.class)) {
            return (T) new DataPegawaiViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
