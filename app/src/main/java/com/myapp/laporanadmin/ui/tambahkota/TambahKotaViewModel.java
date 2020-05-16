package com.myapp.laporanadmin.ui.tambahkota;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahKotaViewModel extends ViewModel implements Callback<ResponsePost> {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public ObservableField<KotaModel> kotamodel = new ObservableField<>();

    public TambahKotaViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);
        try {
            kotamodel.get().getIdKota();
            kotamodel.get().getNamaKota();
            kotamodel.get().getCreatedAt();
            kotamodel.get().getUpdatedAt();
        }catch (NullPointerException e){
            KotaModel kotaModel = new KotaModel();
            kotaModel.setIdKota("");
            kotaModel.setNamaKota("");
            kotaModel.setUpdatedAt("");
            kotaModel.setCreatedAt("");
            kotamodel.set(kotaModel);
        }
    }
    public void setOnSendData(SendDataListener listener){
        this.listener = listener;
    }
    public void simpan(View v,KotaModel kotaModel,String tipe){
        listener.onStart();
        JSONObject jsonObject = new JSONObject();

        KotaModel kota = new KotaModel();
        kota.setIdKota(kotamodel.get().getIdKota());
        kota.setNamaKota(kotamodel.get().getNamaKota());
        kota.setCreatedAt(kotamodel.get().getCreatedAt());
        kota.setUpdatedAt(kotamodel.get().getUpdatedAt());


        if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
            apiService.simpankota(kota).enqueue(this);
        }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))){
            apiService.ubahkota(kota).enqueue(this);
        }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))){
            apiService.hapuskota(kota).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if(cek(response.code(),context,"Simpan Kpta")){
            listener.onSuccess("Berhasil Menambahkan");

        }else {
            listener.onFailed(response.message());
        }
    }

    @Override
    public void onFailure(Call<ResponsePost> call, Throwable t) {
        listener.onError(t.getMessage());
    }
    // TODO: Implement the ViewModel
}
