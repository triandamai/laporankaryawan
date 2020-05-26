package com.myapp.laporankaryawan.callback;

import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.realmobject.LaporanHarianObject;

public interface OnViewClicked {
    void onUpdateBulanan(LaporanBulananObject l);

    void onUpdateHarian(LaporanHarianObject l);
}
