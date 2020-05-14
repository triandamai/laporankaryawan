package com.myapp.laporankaryawan.ui.login;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.LoginModel;
import com.myapp.domain.response.ResponsePostLogin;
import com.myapp.laporankaryawan.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class LoginViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private SendDataListener listener;

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public LoginViewModel(Context context) {
        this.context = context;

    }
    public void setSaveListener(SendDataListener ltr){
        this.listener = ltr;
    }


    public void login(View v){
        listener.onStart();
        LoginModel loginModel = new LoginModel();
        loginModel.setUsername_nip(username.getValue());
        loginModel.setPassword_user(password.getValue());
        apiService = LaporanRepository.getService(context);
        apiService.login(loginModel).enqueue(new Callback<ResponsePostLogin>() {
            @Override
            public void onResponse(Call<ResponsePostLogin> call, Response<ResponsePostLogin> response) {
                if(cek(response.code(),context,"login")){
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")){
//                        Log.e("login",response.body().toString());
                        MyUser.getInstance(context).setUser(response.body().getData());
                        listener.onSuccess(response.body().getData().getLevelUser());
                    }else {
                        listener.onFailed(response.body().getResponseMessage());
                    }
                }else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponsePostLogin> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

}
