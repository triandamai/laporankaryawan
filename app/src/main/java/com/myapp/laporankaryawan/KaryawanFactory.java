package com.myapp.laporankaryawan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.laporankaryawan.ui.homepage.HomeKaryawanViewModel;
import com.myapp.laporankaryawan.ui.resetpassword.ResetPasswordViewModel;
import com.myapp.laporankaryawan.ui.tambahlaporanbulanan.TambahLaporanBulananViewModel;
import com.myapp.laporankaryawan.ui.tambahlaporanharian.TambahLaporanHarianViewModel;
import com.myapp.laporankaryawan.ui.ubahprofil.UbahProfilViewModel;

public class KaryawanFactory implements ViewModelProvider.Factory {
    private Context context;

    public KaryawanFactory(Context context) {

        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HomeKaryawanViewModel.class)) {
            return (T) new HomeKaryawanViewModel(context);
        } else if (modelClass.isAssignableFrom(ResetPasswordViewModel.class)) {
            return (T) new ResetPasswordViewModel(context);
        } else if (modelClass.isAssignableFrom(TambahLaporanBulananViewModel.class)) {
            return (T) new TambahLaporanBulananViewModel(context);
        } else if (modelClass.isAssignableFrom(TambahLaporanHarianViewModel.class)) {
            return (T) new TambahLaporanHarianViewModel(context);
        } else if (modelClass.isAssignableFrom(UbahProfilViewModel.class)) {
            return (T) new UbahProfilViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
