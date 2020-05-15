package com.myapp.laporanadmin.ui.tambahkota;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.response.ResponseGetKaryawan;
import com.myapp.laporanadmin.callback.SendDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahKotaViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<String> namakota = new MutableLiveData<>();

    public TambahKotaViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);
    }
    public void setOnSendData(SendDataListener listener){
        this.listener = listener;
    }
    public void simpan(View v){
        listener.onStart();
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("nama_kota",namakota.getValue());
            apiService.simpankota(jsonObject).enqueue(new Callback<ResponseGetKaryawan>() {
                @Override
                public void onResponse(Call<ResponseGetKaryawan> call, Response<ResponseGetKaryawan> response) {
                    if(cek(response.code(),context,"Simpan Kpta")){
                        listener.onSuccess("Berhasil Menambahkan");
                        namakota.setValue("");
                    }else {
                        listener.onFailed(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponseGetKaryawan> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e.getMessage());
        }
    }
    // TODO: Implement the ViewModel
}
