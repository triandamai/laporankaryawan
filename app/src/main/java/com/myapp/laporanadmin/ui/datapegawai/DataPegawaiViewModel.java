package com.myapp.laporanadmin.ui.datapegawai;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.KaryawanObject;

import java.util.List;

import io.realm.Realm;

public class DataPegawaiViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    private LiveData<List<KaryawanObject>> karyawanData ;
    public DataPegawaiViewModel(Context context) {
        this.context = context;
        realm = Realm.getDefaultInstance();
        fetchFromApi();

    }

    public void fetchFromApi() {
        LaporanRepository.getInstance(context).getDataKaryawan();
    }

    // TODO: Implement the ViewModel
    public void init(){
        try {
//            if(realm == null){
//                realm = Realm.getDefaultInstance();
//            }

            karyawanData = new RealmLiveResult(realm.where(KaryawanObject.class).findAllAsync());
        }finally {
            if(realm != null){
             //   realm.close();
            }
        }

    }

    public LiveData<List<KaryawanObject>> getKaryawanData() {
        return karyawanData;
    }
}
