package com.myapp.data.service;

import com.google.gson.JsonObject;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.domain.model.LoginModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.PostOutletModel;
import com.myapp.domain.model.PostProsesLaporanBulanan;
import com.myapp.domain.model.PostProsesLaporanHarian;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.serialize.ResponseGetKaryawan;
import com.myapp.domain.serialize.ResponseGetKota;
import com.myapp.domain.serialize.ResponseGetLaporanBulanan;
import com.myapp.domain.serialize.ResponseGetLaporanHarian;
import com.myapp.domain.serialize.ResponseGetOutlet;
import com.myapp.domain.serialize.ResponseGetOverview;
import com.myapp.domain.serialize.ResponsePost;
import com.myapp.domain.serialize.ResponsePostLogin;
import com.myapp.domain.serialize.req.RequestSimpanBulanan;
import com.myapp.domain.serialize.req.RequestSimpanHarian;
import com.myapp.domain.serialize.req.RequestUbahPassword;
import com.myapp.domain.serialize.res.ResponseHomePageKaryawan;
import com.myapp.domain.serialize.res.ResponseUbahProfil;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    //    String BASE_URL = "http://192.168.1.3/laporan-karyawan-api/v1/";
//    String BASE_URL = "http://192.168.100.5/laporan-karyawan-api/v1/";
    String BASE_URL = "http://karyawan.rafly.innobiz.id/v1/";

    String accept_urlencoded = "Content-Type: application/x-www-form-urlencoded";
    String accept_json = "Accept: application/json;charset=utf-8";
    String content_type = "Content-Type: application/json;charset=utf-8";
    String api_key = "X-API-KEY: your api key";

    @Headers({accept_json, content_type, api_key})
    @GET("dashboard/data")
    Call<ResponseGetOverview> getOverview();

    @Headers({accept_json, content_type, api_key})
    @POST("dashboard/data_karyawan")
    Call<ResponseHomePageKaryawan> getOverviewKaryawan(@Body JsonObject idUser);

    @Headers({accept_json, content_type, api_key})
    @POST("auth/login")
    Call<ResponsePostLogin> login(@Body LoginModel loginModel);

    @Headers({accept_json, content_type, api_key})
    @POST("auth/register")
    Call<ResponsePost> simpanuser(@Body UserModel userModel);

    @Headers({accept_json, content_type, api_key})
    @POST("outlet/add")
    Call<ResponsePost> simpanoutlet(@Body PostOutletModel outletModel);

    @Headers({accept_json, content_type, api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> getAllKaryawan(@Body UserModel karyawanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("user/update_data")
    Call<ResponsePost> updateuser(@Body UserModel karyawanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("user/update_data")
    Call<ResponseUbahProfil> updateuserkaryawan(@Body UserModel karyawanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("user/update_password")
    Call<ResponsePost> ubahpassword(@Body RequestUbahPassword karyawanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("user/delete")
    Call<ResponsePost> hapususer(@Body UserModel karyawanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("kota/data")
    Call<ResponseGetKota> getAllKota(@Body KotaModel kotaModel);

    @Headers({accept_json, content_type, api_key})
    @POST("kota/add")
    Call<ResponsePost> simpankota(@Body KotaModel kotaModel);

    @Headers({accept_json, content_type, api_key})
    @POST("kota/update")
    Call<ResponsePost> ubahkota(@Body KotaModel kotaModel);

    @Headers({accept_json, content_type, api_key})
    @POST("kota/delete")
    Call<ResponsePost> hapuskota(@Body KotaModel kotaModel);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanharian/data")
    Call<ResponseGetLaporanHarian> getAllLaporanharian(@Body LaporanHarianRequestData laporanHarianRequestData);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanharian/data")
    Call<ResponseGetLaporanHarian> getAllLaporanharianRekapan(@Body LaporanHarianRekapanRequestData laporanHarianRequestData);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanBulanan> getAllLaporanbulanan(@Body LaporanBulananRequestData laporanHarianRequestData);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanBulanan> getAllLaporanbulananRekap(@Body LaporanBulananRequestData laporanHarianRequestData);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanharian/add")
    Call<ResponsePost> tambahlaporanharian(@Body RequestSimpanHarian laporanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanharian/update")
    Call<ResponsePost> ubahlaporanharian(@Body RequestSimpanHarian laporanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanharian/proses")
    Call<ResponsePost> proseslaporanharian(@Body PostProsesLaporanHarian laporanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("outlet/data")
    Call<ResponseGetOutlet> getAllOutlet(@Body OutletModel outletModel);

    @Headers({accept_json, content_type, api_key})
    @POST("outlet/update")
    Call<ResponsePost> ubahoutlet(@Body PostOutletModel outletModel);

    @Headers({accept_json, content_type, api_key})
    @POST("outlet/delete")
    Call<ResponsePost> hapusoutlet(@Body PostOutletModel outletModel);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanbulanan/add")
    Call<ResponsePost> tambahlaporanbulanan(@Body RequestSimpanBulanan laporanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanbulanan/update")
    Call<ResponsePost> ubahlaporanbulanan(@Body RequestSimpanBulanan laporanModel);

    @Headers({accept_json, content_type, api_key})
    @POST("laporanbulanan/proses")
    Call<ResponsePost> laporanbulananproses(@Body PostProsesLaporanBulanan laporanModel);


}
