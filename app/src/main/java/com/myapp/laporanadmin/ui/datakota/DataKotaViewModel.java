package com.myapp.laporanadmin.ui.datakota;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.KotaObject;

import java.util.List;

import io.realm.Realm;

public class DataKotaViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    private LiveData<List<KotaObject>> listKota;
    public DataKotaViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        fetchFromApi();

    }

    public void fetchFromApi() {
        LaporanRepository.getInstance(context).getDataKota();
    }

    public void init(){
        try {

            listKota = new RealmLiveResult(realm.where(KotaObject.class).findAll());
        }finally {
            if(realm != null){
                //realm.close();
            }
        }
    }

    public LiveData<List<KotaObject>> getListKota() {
        return listKota;
    }
    // TODO: Implement the ViewModel
}
