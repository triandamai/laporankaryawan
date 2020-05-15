package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanHarianRekapanRequestData {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("bulan_laporanharian")
    @Expose
    private Integer bulanLaporanharian;
    @SerializedName("tahun_laporanharian")
    @Expose
    private Integer tahunLaporanharian;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

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
