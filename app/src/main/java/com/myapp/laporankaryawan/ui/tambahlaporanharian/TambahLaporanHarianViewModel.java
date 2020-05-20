package com.myapp.laporankaryawan.ui.tambahlaporanharian;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.domain.serialize.req.RequestSimpanHarian;
import com.myapp.laporanadmin.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahLaporanHarianViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<String> tipe = new MutableLiveData<>();
    public MutableLiveData<RequestSimpanHarian> req = new MutableLiveData<>();
    public MutableLiveData<OutletModel> outletmodel = new MutableLiveData<>();
    public MutableLiveData<String> foto = new MutableLiveData<>();

    public TambahLaporanHarianViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
    }

    public void setListener(SendDataListener listener) {
        this.listener = listener;
    }

    public void simpan() {
        listener.onStart();
        if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
            RequestSimpanHarian simpanHarian = new RequestSimpanHarian();
            simpanHarian.setIdUser(Integer.parseInt(MyUser.getInstance(context).getUser().getIdUser()));
            simpanHarian.setAlamatLaporanharian(req.getValue().getAlamatLaporanharian());
            simpanHarian.setBuktiLaporanharian(req.getValue().getBuktiLaporanharian());
            simpanHarian.setIdOutlet(Integer.parseInt(outletmodel.getValue().getIdOutlet()));
            simpanHarian.setKeteranganLaporanharian(req.getValue().getKeteranganLaporanharian());
            simpanHarian.setLatitudeLaporanharian(req.getValue().getLatitudeLaporanharian());
            simpanHarian.setLongitudeLaporanharian(req.getValue().getLongitudeLaporanharian());

            apiService.tambahlaporanharian(simpanHarian).enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    Log.e("Hasil", response.body().toString());
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        listener.onSuccess(response.body().getResponseMessage());
                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }

                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {
                    listener.onFailed(t.getMessage());
                }
            });
        } else {
        }

    }
}
