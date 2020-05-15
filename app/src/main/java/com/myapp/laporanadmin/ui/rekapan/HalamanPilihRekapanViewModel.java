package com.myapp.laporanadmin.ui.rekapan;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.response.ResponseGetLaporanHarian;
import com.myapp.laporanadmin.callback.RekapanListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class HalamanPilihRekapanViewModel extends ViewModel {
    private Context context;
    private ApiService service;
    private RekapanListener listener;
    public HalamanPilihRekapanViewModel(Context context) {
        this.context = context;
        this.service = LaporanRepository.getService(context);

    }
    public void setRekapanListener(RekapanListener l){
        this.listener = l;
    }
    public void setharianrekap(LaporanHarianRekapanRequestData data) {
        service.getAllLaporanharianRekapan(data).enqueue(new Callback<ResponseGetLaporanHarian>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanHarian> call, Response<ResponseGetLaporanHarian> response) {
                if(cek(response.code(),context,"getData lap harian")){

                    //  Log.e(TAG,response.toString());
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData() != null) {
                            Log.e("HEHE",response.body().getData().toString());
                            listener.onSuccess(response.body().getData());
                        }else {
                            listener.onFailed("Tidak Ada Data");
                        }
                    }else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanHarian> call, Throwable t) {
                Log.e("HEHE","gagal ambil laporanharian"+t.getMessage());
                listener.onError(t.getMessage());

            }
        });

    }


}
