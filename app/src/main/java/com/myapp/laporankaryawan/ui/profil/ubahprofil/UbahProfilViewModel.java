package com.myapp.laporankaryawan.ui.profil.ubahprofil;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.serialize.res.ResponseUbahProfil;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class UbahProfilViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    private ApiService apiService;
    private SendDataListener listener;
    public MutableLiveData<UserModel> usermodel = new MutableLiveData<>();
    public MutableLiveData<String> foto = new MutableLiveData<>();

    public UbahProfilViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
        this.realm = Realm.getDefaultInstance();
    }

    public void setListener(SendDataListener listener) {
        this.listener = listener;
    }

    public void simpan() {
        listener.onStart();
        UserModel userModel = new UserModel();
        userModel.setIdUser(MyUser.getInstance(context).getUser().getIdUser());
        userModel.setNamaUser(usermodel.getValue().getNamaUser());
        userModel.setUsernameUser(null);
        userModel.setPasswordUser(null);
        try {
            if (foto.getValue() == null || TextUtils.isEmpty(foto.getValue())) {
                userModel.setFotoUser(null);
            } else {
                userModel.setFotoUser(foto.getValue());
            }
        } catch (NullPointerException e) {
            userModel.setFotoUser(null);
        }

        userModel.setNipUser(usermodel.getValue().getNipUser());
        userModel.setCreatedAt("");
        userModel.setUpdatedAt("");
        userModel.setLevelUser("1");
        //if (userModel.validData()) {
        apiService.updateuserkaryawan(userModel).enqueue(new Callback<ResponseUbahProfil>() {
            @Override
            public void onResponse(Call<ResponseUbahProfil> call, Response<ResponseUbahProfil> response) {
                Log.e("Hasil", response.body().toString());
                if (cek(response.code(), context, "Ubah Profil")) {
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        listener.onSuccess(response.body().getResponseMessage());
                        MyUser.getInstance(context).setUser(response.body().getData());

                    } else {
                        listener.onFailed(response.body().getResponseMessage());
                    }
                } else {
                    listener.onFailed(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseUbahProfil> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
//        } else {
//            listener.onFailed("Data Tidak Boleh Kosong");
//        }
    }
}
