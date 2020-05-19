package com.myapp.laporanadmin.ui.laporanbulanan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.realmobject.LaporanBulananObject;

import org.json.JSONException;

import java.util.List;

import io.realm.Realm;

public class LaporanBulananViewModel extends ViewModel {
    private Context context;
    private LiveData<List<LaporanBulananObject>> listLiveData;
    private Realm realm;

    public LaporanBulananViewModel(Context context, LaporanBulananRequestData laporanHarianRequestData) {
        this.context = context;
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        fetchFromApi(laporanHarianRequestData);

    }

    public void fetchFromApi(LaporanBulananRequestData laporanHarianRequestData) {
        try {
            LaporanRepository.getInstance(context).getLaporanBulanan(laporanHarianRequestData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public LiveData<List<LaporanBulananObject>> init() {
        try {

            listLiveData = new RealmLiveResult(realm.where(LaporanBulananObject.class).findAll());
        } catch (NullPointerException e) {
            listLiveData = new MutableLiveData<>();
        }
        return listLiveData;

    }

    public LiveData<List<LaporanBulananObject>> initNotifikasi() {
        try {

            listLiveData = new RealmLiveResult(realm.where(LaporanBulananObject.class).equalTo("statusLaporanbulanan", "1").findAll());
        } catch (NullPointerException e) {
            listLiveData = new MutableLiveData<>();
        }
        return listLiveData;

    }

}
