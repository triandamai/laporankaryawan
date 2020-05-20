package com.myapp.laporanadmin.ui.tambahuser;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TambahUserFactory implements ViewModelProvider.Factory {
    private Context context;

    public TambahUserFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TambahUserViewModel.class)) {
            return (T) new TambahUserViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
