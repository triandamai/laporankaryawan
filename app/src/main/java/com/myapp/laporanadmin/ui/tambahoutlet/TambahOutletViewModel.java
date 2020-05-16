package com.myapp.laporanadmin.ui.tambahoutlet;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.PostOutletModel;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahOutletViewModel extends ViewModel implements Callback<ResponsePost> {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<OutletModel> outletmodel = new MutableLiveData<>();

    public TambahOutletViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);
    }



    public void setOnListener(SendDataListener l){
        this.listener = l;
    }
    public void simpan(View v,KotaModel kotaModel,String tipe){
        listener.onStart();
        PostOutletModel outletModel = new PostOutletModel();
        outletModel.setIdKota(Integer.parseInt(kotaModel.getIdKota()));
        outletModel.setNamaOutlet(outletmodel.getValue().getNamaOutlet());
        if(outletModel.isValid()) {
            if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
                apiService.simpanoutlet(outletModel).enqueue(this);
            }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))){
                apiService.ubahoutlet(outletmodel).enqueue(this);
            }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))){
                apiService.hapusoutlet(outletmodel).enqueue(this);
            }
        }else {
            listener.onFailed("Mohon Isi Semua Data!");
        }
    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if (cek(response.code(), context, "Simpan Outlet")) {
            Log.e("Message", response.body().getResponseMessage());
            listener.onSuccess(response.body().getResponseMessage());
        } else {
            listener.onFailed("Gagal Menyimpan ");
        }
    }

    @Override
    public void onFailure(Call<ResponsePost> call, Throwable t) {
        listener.onFailed("Mohon Isi Semua Data!");
    }



}
