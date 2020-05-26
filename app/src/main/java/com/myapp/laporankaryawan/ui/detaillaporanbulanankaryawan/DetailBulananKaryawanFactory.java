package com.myapp.laporankaryawan.ui.detaillaporanbulanankaryawan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.LaporanBulananObject;

public class DetailBulananKaryawanFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanBulananObject obj;

    public DetailBulananKaryawanFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailBulananKaryawanViewModel.class)) {
            return (T) new DetailBulananKaryawanViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
