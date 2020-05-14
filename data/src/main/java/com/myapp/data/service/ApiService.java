package com.myapp.data.service;

import com.myapp.domain.model.KaryawanModel;
import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanModel;
import com.myapp.domain.model.LaporanRequestData;
import com.myapp.domain.model.LoginModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.PostUserModel;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.response.ResponseGetKaryawan;
import com.myapp.domain.response.ResponseGetKota;
import com.myapp.domain.response.ResponseGetLaporanBulanan;
import com.myapp.domain.response.ResponseGetLaporanHarian;
import com.myapp.domain.response.ResponseGetOutlet;
import com.myapp.domain.response.ResponseGetOverview;
import com.myapp.domain.response.ResponsePost;
import com.myapp.domain.response.ResponsePostLogin;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
//    String BASE_URL = "http://192.168.1.22/laporan-karyawan-api/v1/";
    String BASE_URL = "http://192.168.100.5/laporan-karyawan-api/v1/";

    String accept_urlencoded = "Content-Type: application/x-www-form-urlencoded";
    String accept_json = "Accept: application/json;charset=utf-8";
    String content_type = "Content-Type: application/json;charset=utf-8";
    String api_key = "X-API-KEY: your api key";

    @Headers({accept_json, content_type,api_key})
    @GET("dashboard/data")
    Call<ResponseGetOverview> getOverview();

    @Headers({accept_json,content_type,api_key})
    @POST("auth/login")
    Call<ResponsePostLogin> login(@Body LoginModel loginModel);

    @Headers({accept_json,content_type,api_key})
    @POST("auth/register")
    Call<ResponsePost> simpanuser(@Body PostUserModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/add")
    Call<ResponsePost> simpanoutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> getAllKaryawan(@Body KaryawanModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> updateuser(@Body KaryawanModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/update_password")
    Call<ResponseGetKaryawan> ubahpassword(@Body KaryawanModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/delete")
    Call<ResponseGetKaryawan> hapususer(@Body KaryawanModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/data")
    Call<ResponseGetKota> getAllKota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/add")
    Call<ResponseGetKaryawan> simpankota(@Body JSONObject kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/update")
    Call<ResponseGetKaryawan> ubahkota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/update")
    Call<ResponseGetKaryawan> hapusota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/data")
    Call<ResponseGetLaporanHarian> getAllLaporanharian(@Body LaporanRequestData laporanRequestData);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanBulanan> getAllLaporanbulanan(@Body LaporanRequestData laporanRequestData);


    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/add")
    Call<ResponseGetKaryawan> tambahlaporanharian(@Body LaporanModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/proses")
    Call<ResponseGetKaryawan> proseslaporanharia(@Body LaporanModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/data")
    Call<ResponseGetOutlet> getAllOutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/add")
    Call<ResponseGetKaryawan> tambahoutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/update")
    Call<ResponseGetKaryawan> ubahoutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/delete")
    Call<ResponseGetKaryawan> hapusoutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanHarian> getAllLaporanBulanan(@Body LaporanModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/add")
    Call<ResponseGetKaryawan> tambahlaporanbulanan(@Body LaporanModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/proses")
    Call<ResponseGetKaryawan> laporanbulananproses(@Body LaporanModel laporanModel);




}
