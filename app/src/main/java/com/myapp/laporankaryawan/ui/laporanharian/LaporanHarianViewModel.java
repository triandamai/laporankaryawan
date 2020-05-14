package com.myapp.laporankaryawan.ui.laporanharian;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.local.RealmLiveResult;
import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.realmobject.LaporanObject;

import org.json.JSONException;

import java.util.List;

import io.realm.Realm;

public class LaporanHarianViewModel extends ViewModel {
    private Context context;
    private LiveData<List<LaporanObject>> listLiveData;
    private Realm realm;
    public LaporanHarianViewModel(Context context,String tahun,String bulan) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();

        try {
            LaporanRepository.getInstance(context).getLaporanHarian(bulan,tahun);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void init(){
        listLiveData = new RealmLiveResult(realm.where(LaporanObject.class).findAll());
    }

    public LiveData<List<LaporanObject>> getListLiveData() {
        return listLiveData;
    }
    // TODO: Implement the ViewModel
}
