package com.myapp.domain.model;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostOutletModel {
    @SerializedName("id_outlet")
    @Expose
    private String idOutlet;
    @SerializedName("id_kota")
    @Expose
    private Integer idKota;
    @SerializedName("nama_outlet")
    @Expose
    private String namaOutlet;
    @SerializedName("nama_kota")
    @Expose
    private String namaKota;

    public String getIdOutlet() {
        return idOutlet;
    }

    public void setIdOutlet(String idOutlet) {
        this.idOutlet = idOutlet;
    }

    public Integer getIdKota() {
        return idKota;
    }

    public void setIdKota(Integer idKota) {
        this.idKota = idKota;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    @Override
    public String toString() {
        return "PostOutletModel{" +
                "idOutlet='" + idOutlet + '\'' +
                ", idKota=" + idKota +
                ", namaOutlet='" + namaOutlet + '\'' +
                ", namaKota='" + namaKota + '\'' +
                '}';
    }

    public boolean isValid(){
        return !TextUtils.isEmpty(namaOutlet) && idKota != null ;
    }
}
