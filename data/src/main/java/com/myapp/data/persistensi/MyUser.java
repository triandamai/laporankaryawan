package com.myapp.data.persistensi;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.myapp.domain.model.UserModel;

public class MyUser {
    private static String TAG = "PERSISTENSI ::";
    private static MyUser myUser;
    private static   SharedPreferences sharedPreferences ;
    private static SharedPreferences.Editor editor;
    private  Context context;
    private MyUser(Context ctx){
        context = ctx;
        sharedPreferences = ctx.getSharedPreferences("data_laporan",0);
        editor = sharedPreferences.edit();
    }
    public static MyUser getInstance(Context context) {
        if(myUser == null){
            myUser = new MyUser(context);
        }
        return myUser;
    }



    public void setUser( UserModel user) {
        try {

            Gson gson = new Gson();
            editor.putString("userdata", gson.toJson(user));
            editor.apply();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage().toString());
        }
    }


    public  UserModel getUser() {
        try {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("userdata", "");
            UserModel user = gson.fromJson(json, UserModel.class);
            return user;
        } catch (NullPointerException e) {
            Log.e(TAG, e.getMessage().toString());
            return null;
        }
    }
    public void setTipeFormUser(String tipe){
        editor.putString("tambahuser",tipe);
        editor.apply();
    }
    public String getTipeFormUser(){
        return sharedPreferences.getString("tambahuser","tambah");
    }
    public void setTipeFormKota(String tipe){
        editor.putString("tambahkota",tipe);
        editor.apply();
    }
    public String getTipeFormKota(){
        return sharedPreferences.getString("tambakota","tambah");
    }
    public void setTipeFormOutlet(String tipe){
        editor.putString("tambaoutlet",tipe);
        editor.apply();
    }
    public String getTipeFormOutlet(){
        return sharedPreferences.getString("tambahoutlet","tambah");
    }
    public  void signOut() {
        editor.remove("userdata");
        editor.apply();
    }
}
