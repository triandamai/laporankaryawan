package com.myapp.laporanadmin.ui.tambahoutlet;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.PostOutletModel;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahOutletViewModel extends ViewModel implements Callback<ResponsePost> {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<PostOutletModel> outletmodel = new MutableLiveData<>();
    public MutableLiveData<KotaModel> kotamodel = new MutableLiveData<>();
    public MutableLiveData<String> tipe = new MutableLiveData<>();

    public TambahOutletViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);

        try {
            outletmodel.getValue().getNamaOutlet();
            outletmodel.getValue().getIdKota();
            outletmodel.getValue().getNamaKota();
            outletmodel.getValue().getIdOutlet();
        } catch (NullPointerException e) {
            PostOutletModel outletModel = new PostOutletModel();
            outletModel.setNamaOutlet("");
            outletModel.setIdKota(0);
            outletModel.setNamaOutlet("");
            outletModel.setIdOutlet("");
            KotaModel kotaModel = new KotaModel();
            kotaModel.setCreatedAt("");
            kotaModel.setUpdatedAt("");
            kotaModel.setNamaKota("");
            kotaModel.setIdKota("");
            outletmodel.setValue(outletModel);
        }
    }


    public void setOnListener(SendDataListener l) {
        this.listener = l;
    }

    public void simpan(View v) {
        listener.onStart();
        PostOutletModel outletModel = new PostOutletModel();


        if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
            outletModel.setIdOutlet("");
            outletModel.setNamaOutlet(outletmodel.getValue().getNamaOutlet());
            outletModel.setIdKota(Integer.parseInt(kotamodel.getValue().getIdKota()));
            if (outletModel.isValid()) {
                apiService.simpanoutlet(outletModel).enqueue(this);
            } else {
                listener.onFailed("Mohon Isi Semua Data!");
            }
        } else if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))) {
            outletModel.setIdOutlet(outletmodel.getValue().getIdOutlet());
            outletModel.setNamaOutlet(outletmodel.getValue().getNamaOutlet());
            outletModel.setIdKota(Integer.parseInt(kotamodel.getValue().getIdKota()));
            if (outletModel.isValid()) {
                apiService.ubahoutlet(outletModel).enqueue(this);
            } else {
                listener.onFailed("Mohon Isi Semua Data!");
            }

        } else if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))) {
            if (outletModel.isValid()) {
                apiService.hapusoutlet(outletModel).enqueue(this);
            } else {
                listener.onFailed("Mohon Isi Semua Data!");
            }

        }

    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if (cek(response.code(), context, "Simpan Outlet")) {
            MyUser.getInstance(context).setTipeFormOutlet(null);
            if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                listener.onSuccess(response.body().getResponseMessage());
            } else {
                listener.onFailed(response.body().getResponseMessage());
            }

        } else {
            listener.onFailed("Gagal Menyimpan ");
        }
    }

    @Override
    public void onFailure(Call<ResponsePost> call, Throwable t) {
        listener.onFailed("Mohon Isi Semua Data!");
    }


}
