package com.myapp.laporankaryawan.ui.tambahlaporanbulanan;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.domain.serialize.req.RequestSimpanBulanan;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahLaporanBulananViewModel extends ViewModel {
    private ApiService apiService;
    private Realm realm;
    private Context context;
    private SendDataListener listener;
    public MutableLiveData<RequestSimpanBulanan> req = new MutableLiveData<>();
    public MutableLiveData<String> tipe = new MutableLiveData<>();


    public TambahLaporanBulananViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);

    }

    public void setListener(SendDataListener listener) {
        this.listener = listener;
    }

    public void simpan() {
        listener.onStart();
        if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
            RequestSimpanBulanan requestSimpanBulanan = new RequestSimpanBulanan();
            requestSimpanBulanan.setIdUser(Integer.parseInt(MyUser.getInstance(context).getUser().getIdUser()));
            requestSimpanBulanan.setIsiLaporanbulanan(req.getValue().getIsiLaporanbulanan());
            apiService.tambahlaporanbulanan(requestSimpanBulanan).enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    Log.e("Hasil", response.body().toString());
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        listener.onSuccess(response.body().getResponseMessage());
                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {
                    listener.onFailed(t.getMessage());
                }
            });
        } else {
            RequestSimpanBulanan requestSimpanBulanan = new RequestSimpanBulanan();
            requestSimpanBulanan.setIdUser(Integer.parseInt(MyUser.getInstance(context).getUser().getIdUser()));
            requestSimpanBulanan.setIsiLaporanbulanan(req.getValue().getIsiLaporanbulanan());
            apiService.tambahlaporanbulanan(requestSimpanBulanan).enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    Log.e("Hasil", response.body().toString());
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        listener.onSuccess(response.body().getResponseMessage());
                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {
                    listener.onFailed(t.getMessage());
                }
            });
        }

    }
}
