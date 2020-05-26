package com.myapp.laporankaryawan.ui.profil;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.UserModel;
import com.myapp.laporanadmin.callback.SendDataListener;

import io.realm.Realm;

public class MenuProfilViewModel extends ViewModel {
    private Context context;
    private ApiService apiService;
    private Realm realm;
    private SendDataListener listener;
    public MutableLiveData<UserModel> user = new MutableLiveData<>();

    public MenuProfilViewModel(Context context) {
        this.context = context;
        this.apiService = LaporanRepository.getService(context);
        this.realm = Realm.getDefaultInstance();
    }


}
