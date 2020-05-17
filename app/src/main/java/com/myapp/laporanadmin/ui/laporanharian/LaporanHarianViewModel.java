package com.myapp.laporanadmin.ui.laporanharian;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.domain.realmobject.LaporanHarianObject;

import org.json.JSONException;

import java.util.List;

import io.realm.Realm;

public class LaporanHarianViewModel extends ViewModel {
    private Context context;
    private LiveData<List<LaporanHarianObject>> listLiveData;
    private Realm realm;
    public LaporanHarianViewModel(Context context, LaporanHarianRequestData laporanHarianRequestData) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        fetchFromApi(laporanHarianRequestData);

    }

    public void fetchFromApi(LaporanHarianRequestData laporanHarianRequestData) {
        try {
            LaporanRepository.getInstance(context).getLaporanHarian(laporanHarianRequestData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void init(){
       try {

           listLiveData = new RealmLiveResult(realm.where(LaporanHarianObject.class).findAll());
       }finally {
           if(realm != null){

           }
       }
    }

    public void initnotifikasi(){
        try {
            listLiveData = new RealmLiveResult(realm.where(LaporanHarianObject.class).equalTo("statusLaporanharia", "1").findAll());
        }finally {
            if(realm != null){

            }
        }
    }
    public LiveData<List<LaporanHarianObject>> getListLiveData() {
        if(listLiveData == null){
            listLiveData = new MutableLiveData<>();
        }
        return listLiveData;
    }
    // TODO: Implement the ViewModel
}
