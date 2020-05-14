package com.myapp.data.repositroy;

import android.content.Context;
import android.util.Log;

import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KaryawanModel;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanModel;
import com.myapp.domain.realmobject.HomePageObject;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.domain.realmobject.LaporanObject;
import com.myapp.domain.response.ResponseGetKaryawan;
import com.myapp.domain.response.ResponseGetKota;
import com.myapp.domain.response.ResponseGetLaporan;
import com.myapp.domain.response.ResponseGetOverview;

import org.json.JSONException;
import org.json.JSONObject;

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
    public void getLaporanHarian(String bulan,String tahun) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put( "bulan_laporanharian" ,bulan);
        jsonObject.put("tahun_laporanharian",tahun);
        service.getAllLaporanharian(jsonObject).enqueue(new Callback<ResponseGetLaporan>() {
            @Override
            public void onResponse(Call<ResponseGetLaporan> call, Response<ResponseGetLaporan> response) {
                if(cek(response.code(),context,"getData lap harian")){
                    Log.e(TAG,response.body().getData().toString());
                    if(response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData().size() >= 1) {
                            try {

                                LaporanObject laporanObject = new LaporanObject();
                                realm.beginTransaction();

                                for (LaporanModel item : response.body().getData()) {
                                    laporanObject.setIdLaporanharian(item.getIdLaporanharian());
                                    laporanObject.setAlamatLaporanharian(item.getAlamatLaporanharian());
                                    laporanObject.setBuktiLaporanharian(item.getBuktiLaporanharian());
                                    laporanObject.setCreatedAt(item.getCreatedAt());
                                    laporanObject.setFotoUser(item.getUser().getFotoUser());
                                    laporanObject.setIdKota(item.getOutlet().getIdKota());
                                    laporanObject.setIdOutlet(item.getIdOutlet());
                                    laporanObject.setIdUser(item.getIdUser());
                                    laporanObject.setKeteranganLaporanharian(item.getKeteranganLaporanharian());
                                    laporanObject.setLatitudeLaporanharian(item.getLatitudeLaporanharian());
                                    laporanObject.setLongitudeLaporanharian(item.getLongitudeLaporanharian());
                                    laporanObject.setLevelUser(item.getUser().getLevelUser());
                                    laporanObject.setNamaKota(item.getOutlet().getKota().getNamaKota());
                                    laporanObject.setNamaOutlet(item.getOutlet().getNamaOutlet());
                                    laporanObject.setNamaUser(item.getUser().getNamaUser());
                                    laporanObject.setNipUser(item.getUser().getNipUser());

                                    realm.executeTransaction(new Realm.Transaction() {
                                        @Override
                                        public void execute(Realm realm) {
                                            realm.copyToRealmOrUpdate(laporanObject);
                                        }
                                    });

                                }
                                realm.commitTransaction();
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
            public void onFailure(Call<ResponseGetLaporan> call, Throwable t) {
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
