package com.myapp.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSimpanBulanan {
    @SerializedName("id_laporanbulanan")
    @Expose
    private String idLaporanbulanan;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("isi_laporanbulanan")
    @Expose
    private String isiLaporanbulanan;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getIsiLaporanbulanan() {
        return isiLaporanbulanan;
    }

    public void setIsiLaporanbulanan(String isiLaporanbulanan) {
        this.isiLaporanbulanan = isiLaporanbulanan;
    }

    public String getIdLaporanbulanan() {
        return idLaporanbulanan;
    }

    public void setIdLaporanbulanan(String idLaporanbulanan) {
        this.idLaporanbulanan = idLaporanbulanan;
    }

    @Override
    public String toString() {
        return "RequestSimpanBulanan{" +
                "idUser=" + idUser +
                ", isiLaporanbulanan='" + isiLaporanbulanan + '\'' +
                '}';
    }
}
