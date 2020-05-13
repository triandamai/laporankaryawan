package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("username_nip")
    @Expose
    private String username_nip;
    @SerializedName("password_user")
    @Expose
    private String password_user;


    public String getUsername_nip() {
        return username_nip;
    }

    public void setUsername_nip(String username_nip) {
        this.username_nip = username_nip;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "username_nip='" + username_nip + '\'' +
                ", password_user='" + password_user + '\'' +
                '}';
    }
}
