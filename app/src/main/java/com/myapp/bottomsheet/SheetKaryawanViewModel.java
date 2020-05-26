package com.myapp.bottomsheet;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.KaryawanObject;

import java.util.List;

import io.realm.Realm;

public class SheetKaryawanViewModel extends ViewModel {
    public LiveData<List<KaryawanObject>> responseGetPendudukRtLiveData;

    private Context context;
    private Realm realm;

    public SheetKaryawanViewModel() {
        realm = Realm.getDefaultInstance();
        LaporanRepository.getInstance(context)
                .getDataKaryawan();
    }

    public void sync() {
        try {
            responseGetPendudukRtLiveData = new RealmLiveResult(realm.where(KaryawanObject.class).findAll());
        } catch (NullPointerException e) {
            responseGetPendudukRtLiveData = new MutableLiveData<>();
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public LiveData<List<KaryawanObject>> getResponseGetPendudukRtLiveData() {
        return responseGetPendudukRtLiveData;
    }

    public void setResponseGetPendudukRtLiveData(LiveData<List<KaryawanObject>> responseGetPendudukRtLiveData) {
        this.responseGetPendudukRtLiveData = responseGetPendudukRtLiveData;
    }
}
