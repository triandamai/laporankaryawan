package com.myapp.laporankaryawan.ui.tambahlaporanharian;

import android.content.Context;
import android.text.TextUtils;
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

import static com.myapp.data.service.ApiHandler.cek;

public class TambahLaporanHarianViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<String> tipe = new MutableLiveData<>();
    public MutableLiveData<Double> lat = new MutableLiveData<>();
    public MutableLiveData<Double> lng = new MutableLiveData<>();
    public MutableLiveData<String> isi = new MutableLiveData<>();
    public MutableLiveData<OutletModel> outletmodel = new MutableLiveData<>();
    public MutableLiveData<String> foto = new MutableLiveData<>();
    public MutableLiveData<String> alamat = new MutableLiveData<>();
    public MutableLiveData<String> id = new MutableLiveData<>();

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
            try {
                if (outletmodel.getValue().getIdOutlet() != null ||
                        !TextUtils.isEmpty(outletmodel.getValue().getIdOutlet()) ||
                        alamat.getValue() == null ||
                        !TextUtils.isEmpty(alamat.getValue())) {

                    simpanHarian.setIdUser(Integer.parseInt(MyUser.getInstance(context).getUser().getIdUser()));
                    simpanHarian.setAlamatLaporanharian(alamat.getValue());
                    simpanHarian.setBuktiLaporanharian(foto.getValue());
                    simpanHarian.setIdOutlet(Integer.parseInt(outletmodel.getValue().getIdOutlet()));
                    simpanHarian.setKeteranganLaporanharian(isi.getValue());
                    simpanHarian.setLatitudeLaporanharian(lat.getValue());
                    simpanHarian.setLongitudeLaporanharian(lng.getValue());

                    apiService.tambahlaporanharian(simpanHarian).enqueue(new Callback<ResponsePost>() {
                        @Override
                        public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                            Log.e("Hasil", response.body().toString());
                            if (cek(response.code(), context, "Tambah")) {
                                if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                                    listener.onSuccess(response.body().getResponseMessage());
                                } else {
                                    listener.onFailed(response.body().getResponseMessage());
                                }
                            } else {
                                listener.onFailed("Gagal " + response.body().getResponseMessage());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsePost> call, Throwable t) {
                            listener.onFailed(t.getMessage());
                        }
                    });
                } else {
                    listener.onFailed("Mohon Isi semua data");
                }
            } catch (NullPointerException e) {
                listener.onFailed(e.getMessage());
            }
        } else {
            RequestSimpanHarian simpanHarian = new RequestSimpanHarian();
            try {
                if (outletmodel.getValue().getIdOutlet() != null ||
                        !TextUtils.isEmpty(outletmodel.getValue().getIdOutlet()) ||
                        alamat.getValue() == null ||
                        !TextUtils.isEmpty(alamat.getValue())) {
                    simpanHarian.setIdLaporanharian(id.getValue());
                    simpanHarian.setIdUser(Integer.parseInt(MyUser.getInstance(context).getUser().getIdUser()));
                    simpanHarian.setAlamatLaporanharian(alamat.getValue());
                    simpanHarian.setBuktiLaporanharian(foto.getValue());
                    if (outletmodel.getValue().getIdOutlet() != null) {
                        simpanHarian.setIdOutlet(Integer.parseInt(outletmodel.getValue().getIdOutlet()));
                    }

                    simpanHarian.setKeteranganLaporanharian(isi.getValue());
                    simpanHarian.setLatitudeLaporanharian(lat.getValue());
                    simpanHarian.setLongitudeLaporanharian(lng.getValue());

                    apiService.ubahlaporanharian(simpanHarian).enqueue(new Callback<ResponsePost>() {
                        @Override
                        public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                            Log.e("Hasil", response.body().toString());
                            if (cek(response.code(), context, "Tambah")) {
                                if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                                    listener.onSuccess(response.body().getResponseMessage());
                                } else {
                                    listener.onFailed(response.body().getResponseMessage());
                                }
                            } else {
                                listener.onFailed("Gagal " + response.body().getResponseMessage());
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponsePost> call, Throwable t) {
                            listener.onFailed(t.getMessage());
                        }
                    });
                } else {
                    listener.onFailed("Mohon Isi semua data");
                }
            } catch (NullPointerException e) {
                listener.onFailed(e.getMessage());
            }
        }

    }
}
