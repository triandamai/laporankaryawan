package com.myapp.laporanadmin.ui.tambahuser;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.laporanadmin.callback.SendDataListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;

public class TambahUserViewModel extends ViewModel implements Callback<ResponsePost> {
    private Context context;
    private SendDataListener listener;
    private ApiService apiService;

    public MutableLiveData<UserModel> usermodel = new MutableLiveData<>();
    public MutableLiveData<String> foto = new MutableLiveData<>();
    public MutableLiveData<String> tipe = new MutableLiveData<>();

    public TambahUserViewModel(Context context, SendDataListener sendDataListener) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
        this.listener = sendDataListener;
        try {
            usermodel.getValue().getIdUser();
            usermodel.getValue().getIdUser();
            usermodel.getValue().getFotoUser();
            usermodel.getValue().getNamaUser();
            usermodel.getValue().getPasswordUser();
            usermodel.getValue().getLevelUser();
            usermodel.getValue().getCreatedAt();
            usermodel.getValue().getUpdatedAt();
            usermodel.getValue().getNipUser();
            usermodel.getValue().getUsernameUser();
        } catch (NullPointerException e) {
            UserModel userModel = new UserModel();
            userModel.setIdUser("");
            userModel.setFotoUser("");
            userModel.setNamaUser("");
            userModel.setPasswordUser("");
            userModel.setLevelUser("");
            userModel.setCreatedAt("");
            userModel.setUpdatedAt("");
            userModel.setNipUser("");
            userModel.setUsernameUser("");
            usermodel.setValue(userModel);
        }
    }

    public void setUsermodel(UserModel usermodel) {
        this.usermodel.setValue(usermodel);
    }

    public void simpan(View v) {

        listener.onStart();
        UserModel userModel = new UserModel();

        if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
            try {
                userModel.setIdUser(null);
                userModel.setNamaUser(usermodel.getValue().getNamaUser());
                userModel.setUsernameUser(null);
                userModel.setPasswordUser(usermodel.getValue().getPasswordUser());
                userModel.setFotoUser(foto.getValue());
                userModel.setNipUser(usermodel.getValue().getNipUser());
                userModel.setCreatedAt("");
                userModel.setUpdatedAt("");
                userModel.setLevelUser("1");
                if (userModel.validData() || !TextUtils.isEmpty(usermodel.getValue().getFotoUser())) {
                    apiService.simpanuser(userModel).enqueue(this);
                } else {
                    listener.onFailed("Isi semua data");
                }
            } catch (NullPointerException e) {
                listener.onFailed(e.getMessage());
            }


        } else if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))) {

            userModel.setIdUser(usermodel.getValue().getIdUser());
            userModel.setNamaUser(usermodel.getValue().getNamaUser());
            userModel.setUsernameUser(null);
            userModel.setPasswordUser(usermodel.getValue().getPasswordUser());
            userModel.setFotoUser(foto.getValue());
            userModel.setNipUser(usermodel.getValue().getNipUser());
            userModel.setCreatedAt("");
            userModel.setUpdatedAt("");
            userModel.setLevelUser("1");
            Log.e("Ubah", "Proses" + userModel.toString());
            if (userModel.validData()) {
                apiService.updateuser(userModel).enqueue(this);
            } else {
                listener.onFailed("Isi semua data");
            }

        } else if (tipe.getValue().equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))) {
            userModel.setIdUser(usermodel.getValue().getIdUser());
            userModel.setNamaUser(usermodel.getValue().getNamaUser());
            userModel.setUsernameUser(usermodel.getValue().getUsernameUser());
            userModel.setPasswordUser(usermodel.getValue().getPasswordUser());
            userModel.setFotoUser(foto.getValue());
            userModel.setNipUser(usermodel.getValue().getNipUser());
            userModel.setCreatedAt("");
            userModel.setUpdatedAt("");
            userModel.setLevelUser("1");
            if (userModel.validData()) {
                apiService.hapususer(userModel).enqueue(this);
            } else {
                listener.onFailed("Isi semua data");
            }

        }


    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if (cek(response.code(), context, "Tambah")) {
            Log.e("Hasil", response.body().toString());
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
        listener.onError(t.getMessage());
    }
    // TODO: Implement the ViewModel
}
