package com.myapp.laporankaryawan.ui.rekapan;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.myapp.data.repositroy.LaporanRepository;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.domain.realmobject.LaporanHarianObject;

import org.json.JSONException;

import java.util.List;

public class HalamanPilihRekapanViewModel extends ViewModel {
    private Context context;
    private LiveData<List<LaporanHarianModel>>harianObject;
    public HalamanPilihRekapanViewModel(Context context) {
        this.context = context;
        if(harianObject == null){
            harianObject = new MutableLiveData<>();
        }
    }

    public void setharianrekap(LaporanHarianRekapanRequestData data) {
        try {
           this.harianObject =LaporanRepository.getInstance(context).getLaporanHarianRekapan(data);
        } catch (JSONException e) {
           this.harianObject = new MutableLiveData<>();
        }
    }

    public LiveData<List<LaporanHarianModel>> getHarianObject() {
        return harianObject;
    }
}
