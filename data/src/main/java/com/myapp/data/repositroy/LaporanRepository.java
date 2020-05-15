package com.myapp.data.repositroy;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KaryawanModel;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.domain.realmobject.HomePageObject;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.domain.response.ResponseGetKaryawan;
import com.myapp.domain.response.ResponseGetKota;
import com.myapp.domain.response.ResponseGetLaporanBulanan;
import com.myapp.domain.response.ResponseGetLaporanHarian;
import com.myapp.domain.response.ResponseGetOverview;

import org.json.JSONException;

import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;
import static com.myapp.data.service.RepoFactory.createService;

public  class LaporanRepository {
    public static final String TAG = "LAPORAN :: ";
    private static ApiService service;
    private static LaporanRepository repository;
    private static Realm realm;
    private Context context;

    private LaporanRepository(Context ctx) {
        realm = Realm.getDefaultInstance();
        service = createService(ApiService.class,"laporanKaryawanMantap","laporanKaryawanMantap", ctx);
        context = ctx;
    }

    public synchronized static LaporanRepository getInstance(Context context) {
        if (repository == null) {
            repository = new LaporanRepository(context);
        }
        return repository;
    }
    public static ApiService getService(Context context){
        return createService(ApiService.class,"laporanKaryawanMantap","laporanKaryawanMantap", context);
    }
    public void getDataHomepgae(){

        service.getOverview().enqueue(new Callback<ResponseGetOverview>() {
            @Override
            public void onResponse(Call<ResponseGetOverview> call, Response<ResponseGetOverview> response) {
                try {
                    HomePageObject object = new HomePageObject();
                    Log.e(TAG,response.body().toString());
                if(cek(response.code(),context,"getData Home Page")){


                       object.setId("1");
                       object.setPegawai(response.body().getData().getPegawai());
                       object.setLapBulanan(response.body().getData().getLapBulanan());
                       object.setLapHarian(response.body().getData().getLapHarian());
                       object.setLapMasukBulanan(response.body().getData().getLapMasukBulanan());
                       object.setLapMasukHarian(response.body().getData().getLapMasukHarian());
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.copyToRealmOrUpdate(object);
                        }
                    });

                }else {

                }

                }finally {
                    if(realm != null){
                        realm.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetOverview> call, Throwable t) {
                Log.e(TAG,t.getMessage());
            }
        });


    }
    public void getLaporanHarian(LaporanHarianRequestData laporanHarianRequestData) throws JSONException {
        Gson gson = new Gson();

        Log.e(TAG,gson.toJson(laporanHarianRequestData));
        service.getAllLaporanharian(laporanHarianRequestData).enqueue(new Callback<ResponseGetLaporanHarian>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanHarian> call, Response<ResponseGetLaporanHarian> response) {
                if(cek(response.code(),context,"getData lap harian")){
                    Log.e(TAG,response.body().getData().toString());
                  //  Log.e(TAG,response.toString());
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData().size() >= 1) {
                            try {

                                LaporanHarianObject laporanHarianObject = new LaporanHarianObject();
                                realm.beginTransaction();
                                realm.delete(LaporanHarianObject.class);
                                realm.commitTransaction();

                                for (LaporanHarianModel item : response.body().getData()) {
                                    laporanHarianObject.setIdLaporanharian(item.getIdLaporanharian());
                                    laporanHarianObject.setAlamatLaporanharian(item.getAlamatLaporanharian());
                                    laporanHarianObject.setBuktiLaporanharian(item.getBuktiLaporanharian());
                                    laporanHarianObject.setCreatedAt(item.getCreatedAt());
                                    laporanHarianObject.setFotoUser(item.getUser().getFotoUser());
                                    laporanHarianObject.setIdKota(item.getOutlet().getIdKota());
                                    laporanHarianObject.setIdOutlet(item.getIdOutlet());
                                    laporanHarianObject.setIdUser(item.getIdUser());
                                    laporanHarianObject.setKeteranganLaporanharian(item.getKeteranganLaporanharian());
                                    laporanHarianObject.setLatitudeLaporanharian(item.getLatitudeLaporanharian());
                                    laporanHarianObject.setLongitudeLaporanharian(item.getLongitudeLaporanharian());
                                    laporanHarianObject.setLevelUser(item.getUser().getLevelUser());
                                    laporanHarianObject.setNamaKota(item.getOutlet().getKota().getNamaKota());
                                    laporanHarianObject.setNamaOutlet(item.getOutlet().getNamaOutlet());
                                    laporanHarianObject.setNamaUser(item.getUser().getNamaUser());
                                    laporanHarianObject.setNipUser(item.getUser().getNipUser());
                                    laporanHarianObject.setStatusLaporanharian(item.getStatusLaporanharian());

                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            realm.copyToRealmOrUpdate(laporanHarianObject);
                                        }
                                    });

                                }

                            }finally {
                                if(realm != null){
                                    realm.close();
                                }
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanHarian> call, Throwable t) {
                Log.e(TAG,"gagal ambil laporanharian"+t.getMessage());
            }
        });
    }

    public void getLaporanBulanan(LaporanBulananRequestData laporanHarianRequestData) throws JSONException {

        service.getAllLaporanbulanan(laporanHarianRequestData).enqueue(new Callback<ResponseGetLaporanBulanan>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanBulanan> call, Response<ResponseGetLaporanBulanan> response) {
                Log.e(TAG,response.toString());
                if(cek(response.code(),context,"getData lap harian")){
                    Log.e(TAG,response.body().toString());
                    Log.e(TAG,response.toString());
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData().size() >= 1) {
                            try {

                                LaporanBulananObject laporanBulananObject = new LaporanBulananObject();
                                realm.beginTransaction();
                                realm.delete(LaporanBulananObject.class);
                                realm.commitTransaction();

                                for (LaporanBulananModel item : response.body().getData()) {
                                    laporanBulananObject.setIdLaporanbulanan(item.getIdLaporanbulanan());
                                    laporanBulananObject.setCreatedAt(item.getCreatedAt());
                                    laporanBulananObject.setFotoUser(item.getUser().getFotoUser());
                                    laporanBulananObject.setIdUser(item.getIdUser());
                                    laporanBulananObject.setIsiLaporanbulanan(item.getIsiLaporanbulanan());
                                    laporanBulananObject.setLevelUser(item.getUser().getLevelUser());
                                    laporanBulananObject.setNamaUser(item.getUser().getNamaUser());
                                    laporanBulananObject.setNipUser(item.getUser().getNipUser());
                                    laporanBulananObject.setStatusLaporanbulanan(item.getStatusLaporanbulanan());
                                    laporanBulananObject.setUpdatedAt(item.getUpdatedAt());

                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            realm.copyToRealmOrUpdate(laporanBulananObject);
                                        }
                                    });

                                }

                            }finally {
                                if(realm != null){
                                    realm.close();
                                }
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanBulanan> call, Throwable t) {
                Log.e(TAG,"gagal ambil laporanharian"+t.getMessage());
            }
        });
    }
    public void getDataKaryawan(){
        KaryawanModel loginModel = new KaryawanModel();

        service.getAllKaryawan(loginModel).enqueue(new Callback<ResponseGetKaryawan>() {
            @Override
            public void onResponse(Call<ResponseGetKaryawan> call, Response<ResponseGetKaryawan> response) {
                if(cek(response.code(),context,"getData Home Page")){
//                    Log.e(TAG,response.body().getData().toString());
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")){
                        if(response.body().getData().size() >= 1){
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.delete(KaryawanObject.class);
                                }
                            });

                            for (KaryawanModel item : response.body().getData()){
                                KaryawanObject karyawanObject = new KaryawanObject();
                                karyawanObject.setIdUser(item.getIdUser());
                                karyawanObject.setNamaUser(item.getNamaUser());
                                karyawanObject.setNipUser(item.getNipUser());
                                karyawanObject.setFotoUser(item.getFotoUser());
                                karyawanObject.setLevelUser(item.getLevelUser());
                                karyawanObject.setUsernameUser(item.getUsernameUser());
                                karyawanObject.setPasswordUser(item.getPasswordUser());
                                karyawanObject.setCreatedAt(item.getCreatedAt());

                                karyawanObject.setUpdatedAt(item.getUpdatedAt());
                                realm.executeTransaction(realm -> {
                                    realm.copyToRealmOrUpdate(karyawanObject);
                                });
                            }
                        realm.close();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKaryawan> call, Throwable t) {
                Log.e(TAG,"gagal ambil karyawan"+t.getMessage());
            }
        });
    }
    public void getDataKota(){
        KotaModel kotaModel = new KotaModel();


        service.getAllKota(kotaModel).enqueue(new Callback<ResponseGetKota>() {
            @Override
            public void onResponse(Call<ResponseGetKota> call, Response<ResponseGetKota> response) {
                if(cek(response.code(),context,"getData Home Page")){
//                    Log.e(TAG,response.body().getData().toString());
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")){
                        if(response.body().getData().size() >= 1){
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    realm.delete(KotaObject.class);
                                }
                            });
                            for (KotaModel item : response.body().getData()){
                                KotaObject kotaObject = new KotaObject();
                                kotaObject.setIdKota(item.getIdKota());
                                kotaObject.setCreatedAt(item.getCreatedAt());
                                kotaObject.setNamaKota(item.getNamaKota());
                                kotaObject.setUpdatedAt(item.getUpdatedAt());
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.copyToRealmOrUpdate(kotaObject);
                                    }
                                });
                            }

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKota> call, Throwable t) {

            }
        });
    }
    public void getDataOutlet(){
        KotaModel kotaModel = new KotaModel();

        service.getAllKota(kotaModel).enqueue(new Callback<ResponseGetKota>() {
            @Override
            public void onResponse(Call<ResponseGetKota> call, Response<ResponseGetKota> response) {

            }

            @Override
            public void onFailure(Call<ResponseGetKota> call, Throwable t) {

            }
        });
    }


}
