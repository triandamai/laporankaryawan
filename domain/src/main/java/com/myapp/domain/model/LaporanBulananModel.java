package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanBulananModel {
    @SerializedName("id_laporanbulanan")
    @Expose
    private String idLaporanbulanan;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("isi_laporanbulanan")
    @Expose
    private String isiLaporanbulanan;
    @SerializedName("status_laporanbulanan")
    @Expose
    private String statusLaporanbulanan;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("user")
    @Expose
    private UserModel user;

    public String getIdLaporanbulanan() {
        return idLaporanbulanan;
    }

    public void setIdLaporanbulanan(String idLaporanbulanan) {
        this.idLaporanbulanan = idLaporanbulanan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIsiLaporanbulanan() {
        return isiLaporanbulanan;
    }

    public void setIsiLaporanbulanan(String isiLaporanbulanan) {
        this.isiLaporanbulanan = isiLaporanbulanan;
    }

    public String getStatusLaporanbulanan() {
        return statusLaporanbulanan;
    }

    public void setStatusLaporanbulanan(String statusLaporanbulanan) {
        this.statusLaporanbulanan = statusLaporanbulanan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
