package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanBulananRequestData {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("bulan_laporanbulanan")
    @Expose
    private Integer bulanLaporanbulanan;
    @SerializedName("tahun_laporanbulanan")
    @Expose
    private Integer tahunLaporanbulanan;
    @SerializedName("status_laporanbulanan")
    @Expose
    private Integer statusLaporanbulanan;

    public Integer getStatusLaporanbulanan() {
        return statusLaporanbulanan;
    }

    public void setStatusLaporanbulanan(Integer statusLaporanbulanan) {
        this.statusLaporanbulanan = statusLaporanbulanan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public Integer getBulanLaporanbulanan() {
        return bulanLaporanbulanan;
    }

    public void setBulanLaporanbulanan(Integer bulanLaporanbulanan) {
        this.bulanLaporanbulanan = bulanLaporanbulanan;
    }

    public Integer getTahunLaporanbulanan() {
        return tahunLaporanbulanan;
    }

    public void setTahunLaporanbulanan(Integer tahunLaporanbulanan) {
        this.tahunLaporanbulanan = tahunLaporanbulanan;
    }
}
