package com.myapp.laporanadmin.ui.laporanharian;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.LaporanHarianRequestData;

public  class LaporanHarianFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanHarianRequestData laporanHarianRequestData;
    public LaporanHarianFactory(Context context, LaporanHarianRequestData laporanHarianRequestData){
        this.laporanHarianRequestData = laporanHarianRequestData;
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LaporanHarianViewModel.class)) {
            return (T) new LaporanHarianViewModel(context, laporanHarianRequestData);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
