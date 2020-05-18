package com.myapp.laporanadmin.ui.dataoutlet;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.PostOutletModel;
import com.myapp.domain.realmobject.OutletObject;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class DataOutletViewModel extends ViewModel {
    private Context context;
    private LiveData<List<OutletObject>> outletData;
    private Realm realm;
    private ApiService apiService;
    private SendDataListener listener;

    public DataOutletViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        this.apiService = LaporanRepository.getService(context);
        fetchFromApi();

    }

    public void setSendDataListener(SendDataListener listener) {
        this.listener = listener;

    }

    public void fetchFromApi() {
        LaporanRepository.getInstance(context).getDataOutlet();
    }

    public void hapus(OutletModel model) {
        listener.onStart();
        PostOutletModel outletModel = new PostOutletModel();
        outletModel.setIdKota(Integer.parseInt(model.getIdKota()));
        outletModel.setNamaOutlet(model.getNamaOutlet());
        outletModel.setIdOutlet(model.getIdOutlet());
        apiService.hapusoutlet(outletModel).enqueue(new Callback<ResponsePost>() {
            @Override
            public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                if (cek(response.code(), context, "Hapus Outlet")) {
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


            outletData = new RealmLiveResult(realm.where(OutletObject.class).findAll());
        } finally {
            if (realm != null) {

            }
        }

    }

    public LiveData<List<OutletObject>> getOutletData() {
        return outletData;
    }
}
