package com.myapp.laporankaryawan.ui.homepage;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.HomePageObject;

import java.util.Objects;

import io.realm.Realm;

public class HomePageViewModel extends ViewModel {
    private Context context;
    private Realm realm;
    private RealmLiveObject realmLiveObject;

    private  LiveData<HomePageObject> homePageModelLiveData;

    public HomePageViewModel(Context context) {
        this.context = context;
        realm = Realm.getDefaultInstance();

        LaporanRepository.getInstance(context).getDataHomepgae();

    }

    public void init(){
        try {

            homePageModelLiveData = new RealmLiveObject(Objects.requireNonNull(realm.where(HomePageObject.class).findFirst()));
        }catch (NullPointerException e){
            homePageModelLiveData = new MutableLiveData<>();
        }
    }

    public LiveData<HomePageObject> getHomePageModelLiveData() {
        return homePageModelLiveData;
    }

    public void setHomePageModelLiveData(LiveData<HomePageObject> homePageModelLiveData) {
        this.homePageModelLiveData = homePageModelLiveData;
    }

}
