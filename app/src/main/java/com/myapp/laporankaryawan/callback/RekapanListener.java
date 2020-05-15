package com.myapp.laporankaryawan.callback;

import com.myapp.domain.model.LaporanHarianModel;

import java.util.List;

public interface RekapanListener {
    void onStart();
    void onSuccess(List<LaporanHarianModel> laporanHarianModels);
    void onFailed(String message);
    void onError(String message);
}
