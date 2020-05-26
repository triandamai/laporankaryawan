package com.myapp.laporankaryawan.ui.rekapan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.ui.rekapan.bulanan.RekapanBulananKaryawanViewModel;
import com.myapp.laporankaryawan.ui.rekapan.harian.RekapanHarianKaryawanViewModel;

public class RekapanKaryawanFactory implements ViewModelProvider.Factory {
    private Context context;

    public RekapanKaryawanFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RekapanHarianKaryawanViewModel.class)) {
            return (T) new RekapanHarianKaryawanViewModel(context);
        } else if (modelClass.isAssignableFrom(RekapanBulananKaryawanViewModel.class)) {
            return (T) new RekapanBulananKaryawanViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
