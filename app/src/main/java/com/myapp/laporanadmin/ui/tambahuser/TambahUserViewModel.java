package com.myapp.laporanadmin.ui.tambahuser;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.R;
import com.myapp.data.persistensi.MyUser;
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
    public ObservableField<UserModel> usermodel = new ObservableField<>();
    public MutableLiveData<String> foto = new MutableLiveData<>();

    public TambahUserViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);

        try {
            usermodel.get().getUsernameUser();
            usermodel.get().getUpdatedAt();
            usermodel.get().getCreatedAt();
            usermodel.get().getPasswordUser();
            usermodel.get().getNipUser();
            usermodel.get().getFotoUser();
            usermodel.get().getLevelUser();
            usermodel.get().getNamaUser();
            usermodel.get().getIdUser();
        }catch (NullPointerException e){
            UserModel u = new UserModel();
            u.setNipUser("");
            u.setUpdatedAt("");
            u.setCreatedAt("");
            u.setLevelUser("");
            u.setPasswordUser("");
            u.setNamaUser("");
            u.setFotoUser("");
            u.setIdUser("");
            usermodel.set(u);
        }
    }

    public void setOnSendData(SendDataListener listener) {
        this.listener = listener;
    }

    public void simpan(View v) {
        Log.e("simpan user", "tes");
        listener.onStart();
        UserModel userModel = new UserModel();


            userModel.setIdUser(usermodel.get().getIdUser());
            userModel.setNamaUser(usermodel.get().getNamaUser());
            userModel.setUsernameUser(usermodel.get().getUsernameUser());
            userModel.setPasswordUser(usermodel.get().getPasswordUser());
            userModel.setFotoUser( foto.getValue());
            userModel.setNipUser(usermodel.get().getNipUser());
            userModel.setCreatedAt("");
            userModel.setUpdatedAt("");
            userModel.setLevelUser("1");
            String tipe = MyUser.getInstance(context).getTipeFormUser();
            if (userModel.validData()) {
                if (tipe.equalsIgnoreCase(context.getString(R.string.AKSI_TAMBAH))) {
                    apiService.simpanuser(userModel).enqueue(this);
                } else if (tipe.equalsIgnoreCase(context.getString(R.string.AKSI_UBAH))) {
                    apiService.updateuser(userModel).enqueue(this);
                } else if (tipe.equalsIgnoreCase(context.getString(R.string.AKSI_HAPUS))) {
                    apiService.hapususer(userModel).enqueue(this);
                }

            } else {
                listener.onFailed("Isi semua data");
            }



    }

    @Override
    public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
        if (cek(response.code(), context, "Tambah")) {

            MyUser.getInstance(context).setTipeFormUser(null);
            if (response.body().getResponseCode().toString().equalsIgnoreCase("200")){
                listener.onSuccess(response.body().getResponseMessage());
            }else {
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
