package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
//    @SerializedName("deleted_at")
//    @Expose
//    private Object deletedAt;

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

//    public Object getDeletedAt() {
//        return deletedAt;
//    }
//
//    public void setDeletedAt(Object deletedAt) {
//        this.deletedAt = deletedAt;
//    }
}
