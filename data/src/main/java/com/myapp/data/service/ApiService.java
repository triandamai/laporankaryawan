package com.myapp.data.service;

import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanModel;
import com.myapp.domain.model.LoginModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.response.ResponseGetKaryawan;
import com.myapp.domain.response.ResponseGetOverview;
import com.myapp.domain.response.ResponsePostLogin;
import com.myapp.domain.response.ResponsePostTambahUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
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
    Call<ResponsePostLogin> login(@Body LoginModel body);

    @Headers({accept_json,content_type,api_key})
    @POST("auth/register")
    Call<ResponsePostTambahUser> simpanuser(@Body UserModel body);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/add")
    Call<ResponsePostTambahUser> simpanoutlet(@Body OutletModel body);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> getAllKaryawan(@Body LoginModel loginModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> updateuser(@Body UserModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/update_password")
    Call<ResponseGetKaryawan> ubahpassword(@Body UserModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/delete")
    Call<ResponseGetKaryawan> hapususer(@Body UserModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> getAllKota(@Body UserModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/add")
    Call<ResponseGetKaryawan> tambahkota(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/update")
    Call<ResponseGetKaryawan> ubahkota(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/update")
    Call<ResponseGetKaryawan> hapusota(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/data")
    Call<ResponseGetKaryawan> getAllLaporan(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/add")
    Call<ResponseGetKaryawan> tambahlaporan(@Body LaporanModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/proses")
    Call<ResponseGetKaryawan> proseslaporan(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/data")
    Call<ResponseGetKaryawan> getAllOutlet(@Body OutletModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/add")
    Call<ResponseGetKaryawan> tambahoutlet(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/update")
    Call<ResponseGetKaryawan> ubahoutlet(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/delete")
    Call<ResponseGetKaryawan> hapusoutlet(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetKaryawan> getAllLaporanBulanan(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/add")
    Call<ResponseGetKaryawan> tambahlaporanbulanan(@Body KotaModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/proses")
    Call<ResponseGetKaryawan> laporanbulananproses(@Body KotaModel userModel);

    


}
