package com.myapp.laporanadmin.ui.homepage;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveObject;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.HomePageObject;

import io.realm.Realm;

public class HomePageViewModel extends ViewModel {
    private Context context;
    private Realm realm;

    private  LiveData<HomePageObject> homePageModelLiveData;

    public HomePageViewModel(Context context) {
        this.context = context;
        realm = Realm.getDefaultInstance();

        LaporanRepository.getInstance(context).getDataHomepgae();

    }

    public void init(){
        try {
            homePageModelLiveData = new RealmLiveObject((realm.where(HomePageObject.class).findFirst()));
        }catch (NullPointerException e){
            homePageModelLiveData = new MutableLiveData<>();
        }
    }

    public LiveData<HomePageObject> getHomePageModelLiveData() {
        if (homePageModelLiveData == null){
            homePageModelLiveData = new MutableLiveData<>();
        }
        return homePageModelLiveData;
    }

}
