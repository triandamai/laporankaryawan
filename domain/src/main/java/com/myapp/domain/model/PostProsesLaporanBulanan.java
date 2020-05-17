package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostProsesLaporanBulanan {
    @SerializedName("id_laporanbulanan")
    @Expose
    private String idLaporanbulanan;
    @SerializedName("status_laporanbulanan")
    @Expose
    private Integer statusLaporanbulanan;

    public String getIdLaporanbulanan() {
        return idLaporanbulanan;
    }

    public void setIdLaporanbulanan(String idLaporanbulanan) {
        this.idLaporanbulanan = idLaporanbulanan;
    }

    public Integer getStatusLaporanbulanan() {
        return statusLaporanbulanan;
    }

    public void setStatusLaporanbulanan(int statusLaporanbulanan) {
        this.statusLaporanbulanan = statusLaporanbulanan;
    }
}
