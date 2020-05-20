package com.myapp.laporanadmin.ui.detaillaporanbulanan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.PostProsesLaporanBulanan;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class DetailBulananViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private Realm realm;
    private SendDataListener sendDataListener;

    public LiveData<LaporanBulananObject> laporanBulananObjectLiveData;

    public DetailBulananViewModel(Context context) {
        this.context = context;

        this.apiService = LaporanRepository.getService(context);
        this.realm = Realm.getDefaultInstance();

    }

    public void setSendDataListener(SendDataListener sendDataListener) {
        this.sendDataListener = sendDataListener;
    }

    public void getObject(String id) {
        laporanBulananObjectLiveData = new RealmLiveObject(realm.where(LaporanBulananObject.class).equalTo("idLaporanbulanan", id).findFirst());
    }

    public void aksi(int s, String idl) {
        sendDataListener.onStart();
        PostProsesLaporanBulanan post = new PostProsesLaporanBulanan();
        post.setIdLaporanbulanan(idl);
        post.setStatusLaporanbulanan(s);
        apiService.laporanbulananproses(post).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if (cek(response.code(), context, "Proses Laporan Harian")) {
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        sendDataListener.onSuccess(response.body().getResponseMessage());
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                LaporanBulananObject obj = realm.where(LaporanBulananObject.class).equalTo("idLaporanbulanan", idl).findFirst();
                                if (obj != null) {
                                    obj.setStatusLaporanbulanan(String.valueOf(s));
                                }
                            }
                        });
                    } else {
                        sendDataListener.onFailed(response.body().getResponseMessage());
                    }

                } else {
                    sendDataListener.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                sendDataListener.onError(t.getMessage());
            }
        });
    }

    public LiveData<LaporanBulananObject> getLaporanBulananObjectLiveData() {
        return laporanBulananObjectLiveData;
    }

}
