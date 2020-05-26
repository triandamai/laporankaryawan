package com.myapp.bottomsheet;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.OutletObject;

import java.util.List;

import io.realm.Realm;

public class SheetOutletViewModel extends ViewModel {
    public LiveData<List<OutletObject>> responseGetPendudukRtLiveData;

    private Context context;
    private Realm realm;

    public SheetOutletViewModel() {
        realm = Realm.getDefaultInstance();
        LaporanRepository.getInstance(context)
                .getDataOutlet();
    }

    public void sync() {
        try {
            responseGetPendudukRtLiveData = new RealmLiveResult(realm.where(OutletObject.class).findAll());
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


    public LiveData<List<OutletObject>> getResponseGetPendudukRtLiveData() {
        return responseGetPendudukRtLiveData;
    }


}
