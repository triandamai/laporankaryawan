package com.myapp.laporanadmin.ui.rekapan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporanadmin.ui.rekapan.bulanan.RekapanBulananAdminViewModel;
import com.myapp.laporanadmin.ui.rekapan.harian.RekapanHarianAdminViewModel;

public class RekapanFactory implements ViewModelProvider.Factory {
    private Context context;

    public RekapanFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RekapanHarianAdminViewModel.class)) {
            return (T) new RekapanHarianAdminViewModel(context);
        } else if (modelClass.isAssignableFrom(RekapanBulananAdminViewModel.class)) {
            return (T) new RekapanBulananAdminViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
