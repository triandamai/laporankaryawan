package com.myapp.laporanadmin.ui.detaillaporanbulanan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.LaporanBulananObject;

public class DetailBulananAdminFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanBulananObject obj;

    public DetailBulananAdminFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailBulananAdminViewModel.class)) {
            return (T) new DetailBulananAdminViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
