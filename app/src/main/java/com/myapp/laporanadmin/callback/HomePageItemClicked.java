package com.myapp.laporanadmin.callback;

import android.view.View;

public interface HomePageItemClicked {
    void dataUser(View v);

    void dataKota(View v);

    void dataOutlet(View v);

    void Pegawai(View v);

    void Harian(View v);

    void Bulanan(View v);

    void LogOut(View v);

    void ResetPassword(View v);

    void notifikasiBulanan(View v);

    void notifikasiHarian(View v);

    void RekapLaporan(View v);
}
