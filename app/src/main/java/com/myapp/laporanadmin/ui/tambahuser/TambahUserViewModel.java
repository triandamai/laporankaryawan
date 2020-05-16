package com.myapp.laporanadmin.ui.tambahuser;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.PostUserModel;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.response.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahUserViewModel extends ViewModel implements Callback<ResponsePost> {
    private Context context;
    private SendDataListener listener;
    private ApiService apiService;
    public MutableLiveData<String> ulangpassword = new MutableLiveData<>();
    public MutableLiveData<UserModel> usermodel = new MutableLiveData<>();

    public TambahUserViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
    }

    public void setOnSendData(SendDataListener listener) {
        this.listener = listener;
    }

    public void simpan(View v, String tipe) {
        Log.e("simpan user", "tes");
        listener.onStart();
        PostUserModel userModel = new PostUserModel();
        userModel.setNamaUser(usermodel.getValue().getNamaUser());
        userModel.setUsernameUser(usermodel.getValue().getUsernameUser());
        userModel.setPasswordUser(usermodel.getValue().getPasswordUser());
        userModel.setLevelUser(1);
        if (userModel.validData()) {
            if (tipe.equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
                apiService.simpanuser(userModel).enqueue(this);
            } else if (tipe.equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))) {
                apiService.updateuser(userModel).enqueue(this);
            } else if (tipe.equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))) {
                apiService.hapususer(userModel).enqueue(this);
            }

        } else {
            listener.onFailed("Isi semua data dan pastikan passwor diatas 5 karakter");
        }
    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if (cek(response.code(), context, "Tambah")) {
            listener.onSuccess("Sukes");

        } else {
            listener.onFailed("Gagal " + response.body().getResponseMessage());
        }
    }

    @Override
    public void onFailure(Call<ResponsePost> call, Throwable t) {
        listener.onError(t.getMessage());
    }
    // TODO: Implement the ViewModel
}
