package com.myapp.laporanadmin.ui.tambahuser;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.PostUserModel;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahUserViewModel extends ViewModel {
    private Context context;
    private SendDataListener listener;
    private ApiService apiService;
    public MutableLiveData<String> nama = new MutableLiveData<>();
    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> ulangpassword = new MutableLiveData<>();

    public TambahUserViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
    }
    public void setOnSendData(SendDataListener listener){
        this.listener = listener;
    }

    public void simpan(View v){
        Log.e("simpan user","tes");
        listener.onStart();
        PostUserModel userModel = new PostUserModel();
        userModel.setNamaUser(nama.getValue());
        userModel.setUsernameUser(username.getValue());
        userModel.setPasswordUser(password.getValue());
        userModel.setLevelUser(1);
        if(userModel.validData()) {
            apiService.simpanuser(userModel).enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    if (cek(response.code(), context, "Tambah")) {
                        listener.onSuccess("Sukes");
                        nama.setValue("");
                        username.setValue("");
                        password.setValue("");
                    } else {
                        listener.onFailed("Gagal " + response.body().getResponseMessage());
                    }

                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }else {
            listener.onFailed("Isi semua data dan pastikan passwor diatas 5 karakter");
        }
    }
    // TODO: Implement the ViewModel
}
