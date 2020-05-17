package com.myapp.data.service;

import com.myapp.domain.model.KotaModel;
import com.myapp.domain.model.LaporanBulananRequestData;
import com.myapp.domain.model.LaporanHarianModel;
import com.myapp.domain.model.LaporanHarianRekapanRequestData;
import com.myapp.domain.model.LaporanHarianRequestData;
import com.myapp.domain.model.LoginModel;
import com.myapp.domain.model.OutletModel;
import com.myapp.domain.model.PostOutletModel;
import com.myapp.domain.model.UserModel;
import com.myapp.domain.response.ResponseGetKaryawan;
import com.myapp.domain.response.ResponseGetKota;
import com.myapp.domain.response.ResponseGetLaporanBulanan;
import com.myapp.domain.response.ResponseGetLaporanHarian;
import com.myapp.domain.response.ResponseGetOutlet;
import com.myapp.domain.response.ResponseGetOverview;
import com.myapp.domain.response.ResponsePost;
import com.myapp.domain.response.ResponsePostLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    //String BASE_URL = "http://192.168.1.19/laporan-karyawan-api/v1/";
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
    Call<ResponsePost> simpanuser(@Body UserModel userModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/add")
    Call<ResponsePost> simpanoutlet(@Body PostOutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponseGetKaryawan> getAllKaryawan(@Body UserModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/data")
    Call<ResponsePost> updateuser(@Body UserModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/update_password")
    Call<ResponsePost> ubahpassword(@Body UserModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("user/delete")
    Call<ResponsePost> hapususer(@Body UserModel karyawanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/data")
    Call<ResponseGetKota> getAllKota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/add")
    Call<ResponsePost> simpankota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/update")
    Call<ResponsePost> ubahkota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("kota/update")
    Call<ResponsePost> hapuskota(@Body KotaModel kotaModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/data")
    Call<ResponseGetLaporanHarian> getAllLaporanharian(@Body LaporanHarianRequestData laporanHarianRequestData);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/data")
    Call<ResponseGetLaporanHarian> getAllLaporanharianRekapan(@Body LaporanHarianRekapanRequestData laporanHarianRequestData);
    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/data")
    Call<ResponseGetLaporanHarian> getAllLaporanharianRekap(@Body LaporanHarianRequestData laporanHarianRequestData);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanBulanan> getAllLaporanbulanan(@Body LaporanBulananRequestData laporanHarianRequestData);
    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanBulanan> getAllLaporanbulananRekap(@Body LaporanHarianRequestData laporanHarianRequestData);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/add")
    Call<ResponsePost> tambahlaporanharian(@Body LaporanHarianModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanharian/proses")
    Call<ResponsePost> proseslaporanharia(@Body LaporanHarianModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/data")
    Call<ResponseGetOutlet> getAllOutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/add")
    Call<ResponsePost> tambahoutlet(@Body OutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/update")
    Call<ResponsePost> ubahoutlet(@Body PostOutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("outlet/delete")
    Call<ResponsePost> hapusoutlet(@Body PostOutletModel outletModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/data")
    Call<ResponseGetLaporanHarian> getAllLaporanBulanan(@Body LaporanHarianModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/add")
    Call<ResponsePost> tambahlaporanbulanan(@Body LaporanHarianModel laporanModel);

    @Headers({accept_json,content_type,api_key})
    @POST("laporanbulanan/proses")
    Call<ResponsePost> laporanbulananproses(@Body LaporanHarianModel laporanModel);




}
