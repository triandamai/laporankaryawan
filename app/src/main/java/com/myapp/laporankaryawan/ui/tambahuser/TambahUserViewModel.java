package com.myapp.laporankaryawan.ui.tambahuser;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.response.ResponsePostTambahUser;
import com.myapp.laporankaryawan.callback.SendDataListener;

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
        listener.onStart();
        UserModel userModel = new UserModel();
        userModel.setNamaUser(nama.getValue());
        userModel.setUsernameUser(username.getValue());
        userModel.setPasswordUser(password.getValue());
        apiService.simpanuser(userModel).enqueue(new Callback<ResponsePostTambahUser>() {
            @Override
            public void onResponse(Call<ResponsePostTambahUser> call, Response<ResponsePostTambahUser> response) {
                if(cek(response.code(),context,"Tambah")){
                    listener.onSuccess("Sukes");
                }else {
                    listener.onFailed("Gagal "+response.body().getResponseMessage());
                }

            }

            @Override
            public void onFailure(Call<ResponsePostTambahUser> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }
    // TODO: Implement the ViewModel
}
