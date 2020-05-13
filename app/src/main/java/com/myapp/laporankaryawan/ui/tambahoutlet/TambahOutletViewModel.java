package com.myapp.laporankaryawan.ui.tambahoutlet;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.response.ResponsePostTambahUser;
import com.myapp.laporankaryawan.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahOutletViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    private MutableLiveData<String> namaoutlet = new MutableLiveData<>();
    private MutableLiveData<KotaModel> kotaModel = new MutableLiveData<>();

    public TambahOutletViewModel(Context context) {
        this.context = context;
    }

    public void init(){
        apiService = LaporanRepository.getService(context);
    }
    // TODO: Implement the ViewModel
    public void setOnListener(SendDataListener l){
        this.listener = l;
    }
    public void simpan(View v){
        listener.onStart();
        OutletModel model = new OutletModel();
        apiService.simpanoutlet(model).enqueue(new Callback<ResponsePostTambahUser>() {
            @Override
            public void onResponse(Call<ResponsePostTambahUser> call, Response<ResponsePostTambahUser> response) {
                if(cek(response.code(),context,"SImpan Outlet")){

                }else {

                }
            }

            @Override
            public void onFailure(Call<ResponsePostTambahUser> call, Throwable t) {

            }
        });
    }
}
