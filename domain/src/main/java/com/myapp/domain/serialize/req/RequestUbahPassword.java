package com.myapp.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUbahPassword {
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("password_lama")
    @Expose
    private String passwordLama;
    @SerializedName("password_baru")
    @Expose
    private String passwordBaru;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getPasswordLama() {
        return passwordLama;
    }

    public void setPasswordLama(String passwordLama) {
        this.passwordLama = passwordLama;
    }

    public String getPasswordBaru() {
        return passwordBaru;
    }

    public void setPasswordBaru(String passwordBaru) {
        this.passwordBaru = passwordBaru;
    }

    @Override
    public String toString() {
        return "RequestUbahPassword{" +
                "idUser=" + idUser +
                ", passwordLama='" + passwordLama + '\'' +
                ", passwordBaru='" + passwordBaru + '\'' +
                '}';
    }
}
