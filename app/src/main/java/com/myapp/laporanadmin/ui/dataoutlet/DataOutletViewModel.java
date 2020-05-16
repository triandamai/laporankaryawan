package com.myapp.laporanadmin.ui.dataoutlet;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.OutletObject;

import java.util.List;

import io.realm.Realm;

public class DataOutletViewModel extends ViewModel {
    private Context context;
    private LiveData<List<OutletObject>> outletData;
    private Realm realm;
    public DataOutletViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        fetchFromApi();

    }

    public void fetchFromApi() {
        LaporanRepository.getInstance(context).getDataOutlet();
    }

    public void init(){
        try {
//            if(realm == null){
//                realm = Realm.getDefaultInstance();
//            }

            outletData = new RealmLiveResult(realm.where(OutletObject.class).findAll());
        }finally {
            if(realm != null){
              //  realm.close();
            }
        }

    }
    // TODO: Implement the ViewModel

    public LiveData<List<OutletObject>> getOutletData() {
        return outletData;
    }
}
