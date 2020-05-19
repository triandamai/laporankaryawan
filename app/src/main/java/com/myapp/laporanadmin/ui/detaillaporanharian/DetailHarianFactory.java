package com.myapp.laporanadmin.ui.detaillaporanharian;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.LaporanHarianObject;

public class DetailHarianFactory implements ViewModelProvider.Factory {
    private Context context;


    public DetailHarianFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailHarianViewModel.class)) {
            return (T) new DetailHarianViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
