package com.myapp.laporankaryawan.ui.resetpassword;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.persistensi.MyUser;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.domain.serialize.req.RequestUbahPassword;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class ResetPasswordViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private Realm realm;
    private SendDataListener listener;

    public MutableLiveData<RequestUbahPassword> req = new MutableLiveData<>();
    public MutableLiveData<String> repass = new MutableLiveData<>();

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
        requestUbahPassword.setIdUser(Integer.parseInt(MyUser.getInstance(context).getUser().getIdUser()));
        requestUbahPassword.setPasswordLama(req.getValue().getPasswordLama());
        requestUbahPassword.setPasswordBaru(req.getValue().getPasswordBaru());
        if (!req.getValue().getPasswordBaru().equals(repass.getValue())) {
            listener.onFailed("Ulang Password Tidak Sesuai!");
        } else if (
                TextUtils.isEmpty(req.getValue().getPasswordBaru()) ||
                        TextUtils.isEmpty(req.getValue().getPasswordLama()) ||
                        TextUtils.isEmpty(repass.getValue())) {
            listener.onFailed("Isi Dulu Semua");

        } else {
            apiService.ubahpassword(requestUbahPassword).enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    Log.e("Hasil", response.body().toString());
                    if (cek(response.code(), context, "Cek")) {
                        if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                            listener.onSuccess(response.body().getResponseMessage());
                        } else {
                            listener.onFailed(response.body().getResponseMessage());
                        }
                    } else {
                        listener.onFailed(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {
                    listener.onError(t.getMessage());
                }
            });
        }
    }
}
