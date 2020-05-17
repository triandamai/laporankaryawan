package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostProsesLaporanHarian {
    @SerializedName("id_laporanharian")
    @Expose
    private String idLaporanharian;
    @SerializedName("status_laporanharian")
    @Expose
    private Integer statusLaporanharian;

    public String getIdLaporanharian() {
        return idLaporanharian;
    }

    public void setIdLaporanharian(String idLaporanharian) {
        this.idLaporanharian = idLaporanharian;
    }

    public Integer getStatusLaporanharian() {
        return statusLaporanharian;
    }

    public void setStatusLaporanharian(Integer statusLaporanharian) {
        this.statusLaporanharian = statusLaporanharian;
    }
}
