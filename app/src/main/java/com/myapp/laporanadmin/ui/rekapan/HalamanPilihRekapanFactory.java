package com.myapp.laporanadmin.ui.rekapan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class HalamanPilihRekapanFactory implements ViewModelProvider.Factory {
    private Context context;

    public HalamanPilihRekapanFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HalamanPilihRekapanHarianViewModel.class)) {
            return (T) new HalamanPilihRekapanHarianViewModel(context);
        } else if (modelClass.isAssignableFrom(HalamanPilihRekapanBulananViewModel.class)) {
            return (T) new HalamanPilihRekapanBulananViewModel(context);
        }else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
