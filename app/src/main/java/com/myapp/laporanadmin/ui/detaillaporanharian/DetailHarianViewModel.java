package com.myapp.laporanadmin.ui.detaillaporanharian;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.PostProsesLaporanHarian;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class DetailHarianViewModel extends ViewModel {
    private Context context;
    private LaporanHarianObject obj;
    private ApiService apiService;
    private Realm realm;
    private SendDataListener sendDataListener;
    public LiveData<LaporanHarianObject> laporanHarianObjectLiveData;
    public DetailHarianViewModel(Context context, LaporanHarianObject obj) {
        this.context = context;
        this.obj = obj;
        this.realm = Realm.getDefaultInstance();
        this.apiService = LaporanRepository.getService(context);
        getObject();
    }

    public void setSendDataListener(SendDataListener sendDataListener) {
        this.sendDataListener = sendDataListener;
    }

    public void aksi(int s,String idl){
        sendDataListener.onStart();
        PostProsesLaporanHarian post = new PostProsesLaporanHarian();
        post.setIdLaporanharian(idl);
        post.setStatusLaporanharian(s);
        apiService.proseslaporanharian(post).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if(cek(response.code(),context,"Proses Laporan Harian")){
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")){
                        sendDataListener.onSuccess(response.body().getResponseMessage());
                        realm.executeTransactionAsync(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                LaporanHarianObject obj = realm.where(LaporanHarianObject.class).equalTo("idLaporanharian",idl).findFirst();
                                if(obj != null){
                                    obj.setStatusLaporanharian(String.valueOf(s));
                                }
                            }
                        });
                    }else {
                        sendDataListener.onFailed(response.body().getResponseMessage());
                    }

                }else {
                    sendDataListener.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePost> call, Throwable t) {
                sendDataListener.onError(t.getMessage());
            }
        });
    }
    private void getObject() {
     laporanHarianObjectLiveData = new RealmLiveObject(realm.where(LaporanHarianObject.class).equalTo("idLaporanharian",obj.getIdLaporanharian()).findFirst());
    }

    public LiveData<LaporanHarianObject> getLaporanHarianObjectLiveData() {
        return laporanHarianObjectLiveData;
    }
    // TODO: Implement the ViewModel
}
