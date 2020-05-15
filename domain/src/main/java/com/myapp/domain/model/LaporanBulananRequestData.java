package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanBulananRequestData {
    @SerializedName("bulan_laporanbulanan")
    @Expose
    private Integer bulanLaporanbulanan;
    @SerializedName("tahun_laporanbulanan")
    @Expose
    private Integer tahunLaporanbulanan;

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
