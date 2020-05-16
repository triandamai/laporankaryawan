package com.myapp.laporanadmin.ui.tambahkota;

import android.content.Context;
import android.view.View;

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
    public MutableLiveData<KotaModel> kotamodel = new MutableLiveData<>();

    public TambahKotaViewModel(Context context) {
        this.context = context;
        apiService = LaporanRepository.getService(context);
    }
    public void setOnSendData(SendDataListener listener){
        this.listener = listener;
    }
    public void simpan(View v,KotaModel kotaModel,String tipe){
        listener.onStart();
        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject.put("nama_kota",kotamodel.getValue().getNamaKota());

            if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
                apiService.simpankota(jsonObject).enqueue(this);
            }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))){
                apiService.ubahkota(kotamodel.getValue()).enqueue(this);
            }else if(tipe.equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))){
                apiService.hapuskota(kotamodel.getValue()).enqueue(this);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onError(e.getMessage());
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
