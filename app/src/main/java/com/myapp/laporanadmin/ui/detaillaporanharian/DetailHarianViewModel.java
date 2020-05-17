package com.myapp.laporanadmin.ui.detaillaporanharian;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.realmobject.LaporanHarianObject;

import io.realm.Realm;

public class DetailHarianViewModel extends ViewModel {
    private Context context;
    private LaporanHarianObject obj;
    private ApiService apiService;
    private Realm realm;

    public LiveData<LaporanHarianObject> laporanHarianObjectLiveData;
    public DetailHarianViewModel(Context context, LaporanHarianObject obj) {
        this.context = context;
        this.obj = obj;
        this.realm = Realm.getDefaultInstance();
        this.apiService = LaporanRepository.getService(context);
        getObject();
    }

    private void getObject() {
     laporanHarianObjectLiveData = new RealmLiveObject(realm.where(LaporanHarianObject.class).equalTo("idLaporanharian",obj.getIdLaporanharian()).findFirst());
    }

    public LiveData<LaporanHarianObject> getLaporanHarianObjectLiveData() {
        return laporanHarianObjectLiveData;
    }
    // TODO: Implement the ViewModel
}
