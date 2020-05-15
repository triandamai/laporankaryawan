package com.myapp.laporankaryawan.ui.bottomsheet;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.realmobject.KotaObject;

import java.util.List;

import io.realm.Realm;

public class SheetKotaViewModel extends ViewModel {
    public LiveData<List<KotaObject>> responseGetPendudukRtLiveData;

    private Context context;
    private Realm realm;
    public SheetKotaViewModel(){
        realm = Realm.getDefaultInstance();
        LaporanRepository.getInstance(context)
                .getDataKota();
    }
    public void sync() {
        try {
            responseGetPendudukRtLiveData = new RealmLiveResult(realm.where(KotaObject.class).findAll());
        }catch (NullPointerException e){
            responseGetPendudukRtLiveData = new MutableLiveData<>();
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public LiveData<List<KotaObject>> getResponseGetPendudukRtLiveData() {
        return responseGetPendudukRtLiveData;
    }

    public void setResponseGetPendudukRtLiveData(LiveData<List<KotaObject>> responseGetPendudukRtLiveData) {
        this.responseGetPendudukRtLiveData = responseGetPendudukRtLiveData;
    }
}
