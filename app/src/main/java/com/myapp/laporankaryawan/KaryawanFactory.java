package com.myapp.laporankaryawan;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.laporankaryawan.ui.homepage.HomeKaryawanViewModel;
import com.myapp.laporankaryawan.ui.laporanbulanan.LaporanBulananViewModel;
import com.myapp.laporankaryawan.ui.laporanharian.LaporanHarianViewModel;
import com.myapp.laporankaryawan.ui.resetpassword.ResetPasswordViewModel;
import com.myapp.laporankaryawan.ui.tambahlaporanbulanan.TambahLaporanBulananViewModel;
import com.myapp.laporankaryawan.ui.tambahlaporanharian.TambahLaporanHarianViewModel;
import com.myapp.laporankaryawan.ui.ubahprofil.UbahProfilViewModel;

public class KaryawanFactory implements ViewModelProvider.Factory {
    private Context context;
    private LaporanBulananRequestData laporanBulananRequestData;
    private LaporanHarianRequestData laporanHarianRequestData;

    public KaryawanFactory(Context context, LaporanBulananRequestData laporanBulananRequestData) {
        this.context = context;
        this.laporanBulananRequestData = laporanBulananRequestData;
    }

    public KaryawanFactory(Context context, LaporanHarianRequestData laporanHarianRequestData) {
        this.context = context;
        this.laporanHarianRequestData = laporanHarianRequestData;
    }

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
        } else if (modelClass.isAssignableFrom(LaporanHarianViewModel.class)) {
            return (T) new LaporanHarianViewModel(context, laporanHarianRequestData);
        } else if (modelClass.isAssignableFrom(LaporanBulananViewModel.class)) {
            return (T) new LaporanBulananViewModel(context, laporanBulananRequestData);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }

    }
}
