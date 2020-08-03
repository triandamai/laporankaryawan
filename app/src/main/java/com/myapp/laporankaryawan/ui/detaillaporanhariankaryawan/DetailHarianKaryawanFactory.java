package com.myapp.laporankaryawan.ui.detaillaporanhariankaryawan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DetailHarianKaryawanFactory implements ViewModelProvider.Factory {
    private Context context;

    public DetailHarianKaryawanFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailHarianKaryawanViewModel.class)) {
            return (T) new DetailHarianKaryawanViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
