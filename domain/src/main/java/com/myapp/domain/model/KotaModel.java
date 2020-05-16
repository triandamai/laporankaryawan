package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myapp.domain.realmobject.KotaObject;

public class KotaModel {
    @SerializedName("id_kota")
    @Expose
    private String idKota;
    @SerializedName("nama_kota")
    @Expose
    private String namaKota;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;


    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
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

    public static KotaModel convertdariobject(KotaObject object){
        KotaModel kotaModel = new KotaModel();
        kotaModel.setIdKota(object.getIdKota());
        kotaModel.setNamaKota(object.getNamaKota());
        kotaModel.setUpdatedAt(object.getUpdatedAt());
        kotaModel.setCreatedAt(object.getCreatedAt());
        return kotaModel;
    }
}
