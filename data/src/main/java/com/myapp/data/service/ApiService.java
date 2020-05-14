package com.myapp.data.service;

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
    String BASE_URL = "http://192.168.1.22/laporan-karyawan-api/v1/";

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
}
