package com.myapp.laporanadmin.ui.datapegawai;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class DataPegawaiViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    private LiveData<List<KaryawanObject>> karyawanData;
    private ApiService apiService;
    private SendDataListener listener;

    public DataPegawaiViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        this.apiService = LaporanRepository.getService(context);
        fetchFromApi();

    }

    public void setSendDataListener(SendDataListener listener) {
        this.listener = listener;

    }

    public void fetchFromApi() {
        LaporanRepository.getInstance(context).getDataKaryawan();
    }

    public void hapus(UserModel model) {
        listener.onStart();
        apiService.hapususer(model).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if (cek(response.code(), context, "Hapus Karyawan")) {
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        listener.onSuccess(response.body().getResponseMessage());
                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                } else {
                    listener.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });

    }

    public void init() {
        try {

            karyawanData = new RealmLiveResult(realm.where(KaryawanObject.class).findAllAsync());
        } finally {
            if (realm != null) {

            }
        }

    }

    public LiveData<List<KaryawanObject>> getKaryawanData() {
        return karyawanData;
    }
}
