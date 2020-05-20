package com.myapp.laporankaryawan.ui.homepage;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.HomePageKaryawan;

import io.realm.Realm;

public class HomeKaryawanViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    private LiveData<HomePageKaryawan> data;

    public HomeKaryawanViewModel(Context context) {
        this.context = context;
        fetchFromApi();
    }

    public void fetchFromApi() {
        LaporanRepository.getInstance(context).getDataHomeKaryawan();
    }

    public void init() {
        try {
            data = new RealmLiveObject((realm.where(HomePageKaryawan.class).findFirst()));
        } catch (NullPointerException e) {
            data = new MutableLiveData<>();
        }
    }

    public LiveData<HomePageKaryawan> getHomePageModelLiveData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }
}