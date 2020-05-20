package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LaporanHarianRequestData {
    @SerializedName("id_user")
    @Expose
    private Integer iduser;
    @SerializedName("bulan_laporanharian")
    @Expose
    private Integer bulanLaporanharian;
    @SerializedName("tahun_laporanharian")
    @Expose
    private Integer tahunLaporanharian;
    @SerializedName("status_laporanharian")
    @Expose
    private Integer statusLaporanharian;

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
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

    public Integer getStatusLaporanharian() {
        return statusLaporanharian;
    }

    public void setStatusLaporanharian(Integer statusLaporanharian) {
        this.statusLaporanharian = statusLaporanharian;
    }
}
