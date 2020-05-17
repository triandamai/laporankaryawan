package com.myapp.laporanadmin.ui.detaillaporanbulanan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.laporanadmin.ui.homepage.HomePageViewModel;

public  class DetailBulananFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanBulananObject obj;
    public DetailBulananFactory(Context context, LaporanBulananObject laporanBulananObject){
        this.obj = laporanBulananObject;
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailBulananViewModel.class)) {
            return (T) new DetailBulananViewModel(context,obj);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
