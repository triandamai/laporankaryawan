package com.myapp.laporankaryawan.ui.detaillaporanbulanankaryawan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.realmobject.LaporanBulananObject;

public class DetailBulananFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanBulananObject obj;

    public DetailBulananFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(DetailBulananViewModel.class)) {
            return (T) new DetailBulananViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
