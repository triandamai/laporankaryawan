package com.myapp.laporanadmin.ui.dataoutlet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporanadmin.ui.datapegawai.DataPegawaiViewModel;

public  class DataOutletFactory implements ViewModelProvider.Factory {
    private Context context;
    public DataOutletFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DataOutletViewModel.class)) {
            return (T) new DataOutletViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
