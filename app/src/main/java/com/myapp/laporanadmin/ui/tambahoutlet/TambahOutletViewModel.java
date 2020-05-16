package com.myapp.laporanadmin.ui.tambahoutlet;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
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
    public ObservableField<PostOutletModel> outletmodel = new ObservableField<>();

    public TambahOutletViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);

        try {
            outletmodel.get().getNamaOutlet();
            outletmodel.get().getIdKota();
        }catch (NullPointerException e){
            PostOutletModel outletModel = new PostOutletModel();
            outletModel.setNamaOutlet("");
            outletModel.setIdKota(0);
            outletmodel.set(outletModel);
        }
    }



    public void setOnListener(SendDataListener l){
        this.listener = l;
    }
    public void simpan(View v,KotaModel kotaModel){
        listener.onStart();
        PostOutletModel outletModel = new PostOutletModel();
        if (kotaModel == null){
            outletModel.setIdKota(outletmodel.get().getIdKota());
        }else {
            outletModel.setIdKota(Integer.parseInt(kotaModel.getIdKota()));
        }
        outletModel.setNamaOutlet(outletmodel.get().getNamaOutlet());
        String  tipe = MyUser.getInstance(context).getTipeFormOutlet();
        if(outletModel.isValid()) {
            if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
                apiService.simpanoutlet(outletModel).enqueue(this);
            }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))){
                apiService.ubahoutlet(outletModel).enqueue(this);
            }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))){
                apiService.hapusoutlet(outletModel).enqueue(this);
            }
        }else {
            listener.onFailed("Mohon Isi Semua Data!");
        }
    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if (cek(response.code(), context, "Simpan Outlet")) {
            Log.e("Message", response.body().getResponseMessage());
            MyUser.getInstance(context).setTipeFormOutlet(null);
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
