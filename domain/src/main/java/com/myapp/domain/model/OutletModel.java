package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myapp.domain.realmobject.OutletObject;

public class OutletModel {
    @SerializedName("id_outlet")
    @Expose
    private String idOutlet;
    @SerializedName("id_kota")
    @Expose
    private String idKota;
    @SerializedName("nama_outlet")
    @Expose
    private String namaOutlet;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("kota")
    @Expose
    private KotaModel kota;

    public String getIdOutlet() {
        return idOutlet;
    }

    public void setIdOutlet(String idOutlet) {
        this.idOutlet = idOutlet;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNamaOutlet() {
        return namaOutlet;
    }

    public void setNamaOutlet(String namaOutlet) {
        this.namaOutlet = namaOutlet;
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


    public KotaModel getKota() {
        return kota;
    }

    public void setKota(KotaModel kota) {
        this.kota = kota;
    }

    public static OutletModel convertdariobject(OutletObject obj) {

        KotaModel k = new KotaModel();
        k.setCreatedAt(obj.getCreatedAt());
        k.setUpdatedAt(obj.getUpdatedAt());
        k.setNamaKota(obj.getNamaKota());
        k.setIdKota(obj.getIdKota());
        OutletModel t = new OutletModel();
        t.setIdKota(obj.getIdKota());
        t.setCreatedAt(obj.getCreatedAt());
        t.setNamaOutlet(obj.getNamaOutlet());
        t.setIdOutlet(obj.getIdOutlet());
        t.setKota(k);
        t.setUpdatedAt(obj.getUpdatedAt());
        return t;
    }

    @Override
    public String toString() {
        return "OutletModel{" +
                "idOutlet='" + idOutlet + '\'' +
                ", idKota='" + idKota + '\'' +
                ", namaOutlet='" + namaOutlet + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", kota=" + kota +
                '}';
    }
}
