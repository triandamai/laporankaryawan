package com.myapp.laporanadmin.callback;

import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanHarianModel;

import java.util.List;

public interface RekapanListener {
    void onStart();
    void onSuccessHarian(List<LaporanHarianModel> laporanHarianModels);
    void onSuccessBulanan(List<LaporanBulananModel> laporanHarianModels);
    void onFailed(String message);
    void onError(String message);
}
