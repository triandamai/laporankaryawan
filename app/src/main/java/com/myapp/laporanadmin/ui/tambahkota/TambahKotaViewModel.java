package com.myapp.laporanadmin.ui.tambahkota;

import android.content.Context;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahKotaViewModel extends ViewModel implements Callback<ResponsePost> {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<KotaModel> kotamodel = new MutableLiveData<>();
    public MutableLiveData<String> tipe = new MutableLiveData<>();

    public TambahKotaViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);

        try {
            kotamodel.getValue().getIdKota();
            kotamodel.getValue().getNamaKota();
            kotamodel.getValue().getCreatedAt();
            kotamodel.getValue().getUpdatedAt();
        }catch (NullPointerException e){
            KotaModel kotaModel = new KotaModel();
            kotaModel.setIdKota("");
            kotaModel.setNamaKota("");
            kotaModel.setUpdatedAt("");
            kotaModel.setCreatedAt("");
            kotamodel.setValue(kotaModel);
        }
    }
    public void setOnSendData(SendDataListener listener){
        this.listener = listener;
    }
    public void simpan(View v){
        listener.onStart();
        KotaModel kota = new KotaModel();





        if(tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
            kota.setIdKota(kotamodel.getValue().getIdKota());
            kota.setNamaKota(kotamodel.getValue().getNamaKota());
            kota.setUpdatedAt("");
            kota.setCreatedAt("");
            apiService.simpankota(kota).enqueue(this);
        }else if(tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))){
            kota.setIdKota(kotamodel.getValue().getIdKota());
            kota.setNamaKota(kotamodel.getValue().getNamaKota());
            kota.setUpdatedAt(kotamodel.getValue().getUpdatedAt());
            kota.setCreatedAt(kotamodel.getValue().getCreatedAt());
            apiService.ubahkota(kota).enqueue(this);
        }else if(tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))){
            apiService.hapuskota(kota).enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if(cek(response.code(),context,"Simpan Kpta")){
            if (response.body().getResponseCode().toString().equalsIgnoreCase("200")){
                MyUser.getInstance(context).setTipeFormKota(null);
                listener.onSuccess(response.body().getResponseMessage());
            }else {
                listener.onFailed(response.body().getResponseMessage());
            }


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
