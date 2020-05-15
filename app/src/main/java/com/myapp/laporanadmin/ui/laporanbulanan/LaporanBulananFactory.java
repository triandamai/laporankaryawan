package com.myapp.laporanadmin.ui.laporanbulanan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.LaporanBulananRequestData;

public  class LaporanBulananFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanBulananRequestData laporanHarianRequestData;
    public LaporanBulananFactory(Context context, LaporanBulananRequestData laporanHarianRequestData){
        this.laporanHarianRequestData = laporanHarianRequestData;
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LaporanBulananViewModel.class)) {
            return (T) new LaporanBulananViewModel(context, laporanHarianRequestData);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
