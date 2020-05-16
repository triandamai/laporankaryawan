package com.myapp.domain.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostUserModel {
    @SerializedName("username_user")
    @Expose
    private String usernameUser;
    @SerializedName("password_user")
    @Expose
    private String passwordUser;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("level_user")
    @Expose
    private Integer levelUser;
    @Expose
    private String nipUser;

    public String getNipUser() {
        return nipUser;
    }

    public void setNipUser(String nipUser) {
        this.nipUser = nipUser;
    }

    @SerializedName("nip_user")



    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public Integer getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(Integer levelUser) {
        this.levelUser = levelUser;
    }

    public boolean validData() {
        return !TextUtils.isEmpty(usernameUser) && !TextUtils.isEmpty(passwordUser) && passwordUser.length() >= 5;
    }
}
