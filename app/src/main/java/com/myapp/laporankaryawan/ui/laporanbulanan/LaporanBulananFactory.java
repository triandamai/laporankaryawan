package com.myapp.laporankaryawan.ui.laporanbulanan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.LaporanRequestData;

public  class LaporanBulananFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanRequestData laporanRequestData;
    public LaporanBulananFactory(Context context, LaporanRequestData laporanRequestData){
        this.laporanRequestData = laporanRequestData;
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LaporanBulananViewModel.class)) {
            return (T) new LaporanBulananViewModel(context,laporanRequestData);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
