package com.myapp.laporanadmin.ui.rekapan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public  class HalamanPilihRekapanFactory implements ViewModelProvider.Factory {
    private Context context;

    public HalamanPilihRekapanFactory(Context context){

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HalamanPilihRekapanViewModel.class)) {
            return (T) new HalamanPilihRekapanViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
