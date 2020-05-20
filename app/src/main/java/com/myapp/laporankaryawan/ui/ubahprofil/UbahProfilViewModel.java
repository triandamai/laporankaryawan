package com.myapp.laporankaryawan.ui.ubahprofil;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        userModel.setIdUser(usermodel.getValue().getIdUser());
        userModel.setNamaUser(usermodel.getValue().getNamaUser());
        userModel.setUsernameUser(null);
        userModel.setPasswordUser(usermodel.getValue().getPasswordUser());
        userModel.setFotoUser(foto.getValue());
        userModel.setNipUser(usermodel.getValue().getNipUser());
        userModel.setCreatedAt("");
        userModel.setUpdatedAt("");
        userModel.setLevelUser("1");
        apiService.updateuser(userModel).enqueue(new Callback<ResponsePost>() {
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
                listener.onError(t.getMessage());
            }
        });
    }
}
