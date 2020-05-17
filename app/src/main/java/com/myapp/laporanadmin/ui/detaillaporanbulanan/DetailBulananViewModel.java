package com.myapp.laporanadmin.ui.detaillaporanbulanan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.data.service.ApiService;
import com.myapp.domain.realmobject.LaporanBulananObject;

import io.realm.Realm;

public class DetailBulananViewModel extends ViewModel {
    private Context context;
    private LaporanBulananObject obj;
    private ApiService apiService;
    private Realm realm;
    public LiveData<LaporanBulananObject> laporanBulananObjectLiveData;

    public DetailBulananViewModel(Context context, LaporanBulananObject obj) {
        this.context = context;
        this.obj = obj;
        this.apiService = LaporanRepository.getService(context);
        this.realm = Realm.getDefaultInstance();
        getObject();
    }

    public void getObject() {
        laporanBulananObjectLiveData = new RealmLiveObject(realm.where(LaporanBulananObject.class).equalTo("idLaporanbulanan",obj.getIdLaporanbulanan()).findFirst());
    }
//    public void aksi
    public LiveData<LaporanBulananObject> getLaporanBulananObjectLiveData() {
        return laporanBulananObjectLiveData;
    }
    // TODO: Implement the ViewModel
}
