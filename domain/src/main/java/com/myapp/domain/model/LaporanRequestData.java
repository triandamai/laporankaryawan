package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanRequestData {
    @SerializedName("bulan_laporanharian")
    @Expose
    private Integer bulanLaporanharian;
    @SerializedName("tahun_laporanharian")
    @Expose
    private Integer tahunLaporanharian;

    public Integer getBulanLaporanharian() {
        return bulanLaporanharian;
    }

    public void setBulanLaporanharian(Integer bulanLaporanharian) {
        this.bulanLaporanharian = bulanLaporanharian;
    }

    public Integer getTahunLaporanharian() {
        return tahunLaporanharian;
    }

    public void setTahunLaporanharian(Integer tahunLaporanharian) {
        this.tahunLaporanharian = tahunLaporanharian;
    }
}
