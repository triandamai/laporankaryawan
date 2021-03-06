package com.myapp.data.repositroy;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonObject;
import com.myapp.data.persistensi.MyUser;
import com.myapp.data.service.ApiService;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanBulananModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.realmobject.HomePageKaryawan;
import com.myapp.domain.realmobject.HomePageObject;
import com.myapp.domain.realmobject.KaryawanObject;
import com.myapp.domain.realmobject.KotaObject;
import com.myapp.domain.realmobject.LaporanBulananObject;
import com.myapp.domain.realmobject.LaporanHarianObject;
import com.myapp.domain.realmobject.OutletObject;
import com.myapp.domain.serialize.ResponseGetKaryawan;
import com.myapp.domain.serialize.ResponseGetKota;
import com.myapp.domain.serialize.ResponseGetLaporanBulanan;
import com.myapp.domain.serialize.ResponseGetLaporanHarian;
import com.myapp.domain.serialize.ResponseGetOutlet;
import com.myapp.domain.serialize.ResponseGetOverview;
import com.myapp.domain.serialize.res.ResponseHomePageKaryawan;

import org.json.JSONException;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.myapp.data.service.ApiHandler.cek;
import static com.myapp.data.service.RepoFactory.createService;

public class LaporanRepository {
    public static final String TAG = "LAPORAN :: ";
    private static ApiService service;
    private static LaporanRepository repository;
    private static Realm realm;
    private Context context;

    private LaporanRepository(Context ctx) {
        realm = Realm.getDefaultInstance();
        service = createService(ApiService.class, "laporanKaryawanMantap", "laporanKaryawanMantap", ctx);
        context = ctx;
    }

    public synchronized static LaporanRepository getInstance(Context context) {
        if (repository == null) {
            repository = new LaporanRepository(context);
        }
        return repository;
    }

    public static ApiService getService(Context context) {
        if (service == null) {
            service = createService(ApiService.class, "laporanKaryawanMantap", "laporanKaryawanMantap", context);
        }
        return service;
    }

    public void getDataHomepage() {

        service.getOverview().enqueue(new Callback<ResponseGetOverview>() {
            @Override
            public void onResponse(Call<ResponseGetOverview> call, Response<ResponseGetOverview> response) {
                try {
                    HomePageObject object = new HomePageObject();
                    Log.e(TAG, response.body().toString());
                    if (cek(response.code(), context, "getData Home Page")) {


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

                    } else {

                    }

                } finally {
                    if (realm != null) {
                        //realm.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetOverview> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });


    }

    public void getDataHomeKaryawan() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id_user", MyUser.getInstance(context).getUser().getIdUser());
        Log.e(TAG, jsonObject.toString());
        service.getOverviewKaryawan(jsonObject).enqueue(new Callback<ResponseHomePageKaryawan>() {
            @Override
            public void onResponse(Call<ResponseHomePageKaryawan> call, Response<ResponseHomePageKaryawan> response) {
                try {

                    //  realm.beginTransaction();
                    if (cek(response.code(), context, "getData Home Page")) {
                        Log.e(TAG, response.body().toString());
                        HomePageKaryawan object = new HomePageKaryawan();
                        object.setId(1);
                        object.setLapHarian(response.body().getData().getLapHarian());
                        object.setLapBulanan(response.body().getData().getLapBulanan());
                        realm.executeTransaction(realm -> {
                            Log.e("simpan", "hehe");
                            realm.copyToRealmOrUpdate(object);
                        });

                    } else {

                    }

                } finally {
                    if (realm != null) {
                        //realm.close();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseHomePageKaryawan> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });


    }

    public void getLaporanHarian(LaporanHarianRequestData laporanHarianRequestData) throws JSONException {

        service.getAllLaporanharian(laporanHarianRequestData).enqueue(new Callback<ResponseGetLaporanHarian>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanHarian> call, Response<ResponseGetLaporanHarian> response) {
                if (cek(response.code(), context, "getData lap harian")) {
                    Log.e(TAG, response.body().toString());

                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
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

                            } finally {
                                if (realm != null) {

                                }
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanHarian> call, Throwable t) {
                Log.e(TAG, "gagal ambil laporanharian" + t.getMessage());
            }
        });
    }

    public void getLaporanBulanan(LaporanBulananRequestData laporanHarianRequestData) throws JSONException {

        service.getAllLaporanbulanan(laporanHarianRequestData).enqueue(new Callback<ResponseGetLaporanBulanan>() {
            @Override
            public void onResponse(Call<ResponseGetLaporanBulanan> call, Response<ResponseGetLaporanBulanan> response) {
                Log.e(TAG, response.toString());
                if (cek(response.code(), context, "getData lap harian")) {
                    Log.e(TAG, response.body().toString());
                    Log.e(TAG, response.toString());
                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
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

                            } finally {
                                if (realm != null) {

                                }
                            }
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseGetLaporanBulanan> call, Throwable t) {
                Log.e(TAG, "gagal ambil laporanharian" + t.getMessage());
            }
        });
    }

    public void getDataKaryawan() {
        UserModel loginModel = new UserModel();

        service.getAllKaryawan(loginModel).enqueue(new Callback<ResponseGetKaryawan>() {
            @Override
            public void onResponse(Call<ResponseGetKaryawan> call, Response<ResponseGetKaryawan> response) {

                if (cek(response.code(), context, "getData Home Page")) {

                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData().size() >= 1) {
                            try {
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        realm.delete(KaryawanObject.class);
                                    }
                                });

                                for (UserModel item : response.body().getData()) {
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
                            } finally {
                                if (realm != null) {

                                }
                            }

                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseGetKaryawan> call, Throwable t) {
                Log.e(TAG, "gagal ambil karyawan" + t.getMessage());
            }
        });
    }

    public void getDataKota() {
        KotaModel kotaModel = new KotaModel();
        service.getAllKota(kotaModel).enqueue(new Callback<ResponseGetKota>() {
            @Override
            public void onResponse(Call<ResponseGetKota> call, Response<ResponseGetKota> response) {
                if (cek(response.code(), context, "getData Home Page")) {

                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        if (response.body().getData().size() >= 1) {
                            try {
                                realm.executeTransaction(realm -> realm.delete(KotaObject.class));
                                for (KotaModel item : response.body().getData()) {
                                    KotaObject kotaObject = new KotaObject();
                                    kotaObject.setIdKota(item.getIdKota());
                                    kotaObject.setCreatedAt(item.getCreatedAt());
                                    kotaObject.setNamaKota(item.getNamaKota());
                                    kotaObject.setUpdatedAt(item.getUpdatedAt());
                                    realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(kotaObject));
                                }
                            } finally {
                                if (realm != null) {

                                }
                            }

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKota> call, Throwable t) {
                Log.e(TAG, "gagal ambil kotan" + t.getMessage());
            }
        });
    }

    public void getDataOutlet() {
        OutletModel outletModel = new OutletModel();

        service.getAllOutlet(outletModel).enqueue(new Callback<ResponseGetOutlet>() {
            @Override
            public void onResponse(Call<ResponseGetOutlet> call, Response<ResponseGetOutlet> response) {
                if (cek(response.code(), context, "getData Home Page")) {

                    if (response.body().getResponseCode().toString().equalsIgnoreCase("200")) {
                        realm.executeTransaction(realm -> realm.delete(OutletObject.class));
                        if (response.body().getData().size() >= 1) {
                            try {

                                for (OutletModel item : response.body().getData()) {
                                    OutletObject outletObject = new OutletObject();
                                    outletObject.setIdOutlet(item.getIdOutlet());
                                    outletObject.setIdKota(item.getIdKota());
                                    outletObject.setNamaOutlet(item.getNamaOutlet());
                                    outletObject.setNamaKota(item.getKota().getNamaKota());
                                    outletObject.setCreatedAt(item.getCreatedAt());
                                    outletObject.setUpdatedAt(item.getUpdatedAt());
                                    realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(outletObject));
                                }
                            } finally {
                                if (realm != null) {

                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGetOutlet> call, Throwable t) {

            }
        });
    }


}
