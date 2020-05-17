package com.myapp.laporanadmin.ui.detaillaporanharian;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.laporanadmin.ui.homepage.HomePageViewModel;

public  class DetailHarianFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanHarianObject obj;
    public DetailHarianFactory(Context context, LaporanHarianObject laporanHarianObject){
        this.obj = laporanHarianObject;
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailHarianViewModel.class)) {
            return (T) new DetailHarianViewModel(context,obj);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
