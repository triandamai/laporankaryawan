package com.myapp.laporankaryawan.ui.resetpassword;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.domain.serialize.req.RequestUbahPassword;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private Realm realm;
    private SendDataListener listener;

    public ResetPasswordViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
        this.realm = Realm.getDefaultInstance();

    }

    public void setListener(SendDataListener listener) {
        this.listener = listener;
    }

    public void ubah() {
        listener.onStart();
        RequestUbahPassword requestUbahPassword = new RequestUbahPassword();
        apiService.ubahpassword(requestUbahPassword).enqueue(new Callback<ResponsePost>() {
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
