package com.myapp.laporankaryawan.ui.tambahoutlet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public  class TambahOutletFactory implements ViewModelProvider.Factory {
    private Context context;
    public TambahOutletFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TambahOutletViewModel.class)) {
            return (T) new TambahOutletViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
