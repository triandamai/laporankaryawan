package com.myapp.laporankaryawan.ui.laporanbulanan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.model.LaporanRequestData;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.realmobject.LaporanHarianObject;

import org.json.JSONException;

import java.util.List;

import io.realm.Realm;

public class LaporanBulananViewModel extends ViewModel {
    private Context context;
    private LiveData<List<LaporanBulananObject>> listLiveData;
    private Realm realm;
    public LaporanBulananViewModel(Context context, LaporanRequestData laporanRequestData) {
        this.context = context;
        this.context = context;
        this.realm = Realm.getDefaultInstance();

        try {
            LaporanRepository.getInstance(context).getLaporanHarian(laporanRequestData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void init(){
        try {
            listLiveData = new RealmLiveResult(realm.where(LaporanBulananObject.class).findAll());
        }catch (NullPointerException e){
            listLiveData = new MutableLiveData<>();
        }
    }

    public LiveData<List<LaporanBulananObject>> getListLiveData() {
        if(listLiveData == null){
            listLiveData = new MutableLiveData<>();
        }
        return listLiveData;
    }
    // TODO: Implement the ViewModel
}
