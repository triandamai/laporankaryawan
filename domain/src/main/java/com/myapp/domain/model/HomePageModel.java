package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class HomePageModel extends RealmObject {

    @SerializedName("pegawai")
    @Expose
    private Integer pegawai;
    @SerializedName("lap_harian")
    @Expose
    private Integer lapHarian;
    @SerializedName("lap_bulanan")
    @Expose
    private Integer lapBulanan;
    @SerializedName("lap_masuk_harian")
    @Expose
    private Integer lapMasukHarian;
    @SerializedName("lap_masuk_bulanan")
    @Expose
    private Integer lapMasukBulanan;

    public Integer getPegawai() {
        return pegawai;
    }

    public void setPegawai(Integer pegawai) {
        this.pegawai = pegawai;
    }

    public Integer getLapHarian() {
        return lapHarian;
    }

    public void setLapHarian(Integer lapHarian) {
        this.lapHarian = lapHarian;
    }

    public Integer getLapBulanan() {
        return lapBulanan;
    }

    public void setLapBulanan(Integer lapBulanan) {
        this.lapBulanan = lapBulanan;
    }

    public Integer getLapMasukHarian() {
        return lapMasukHarian;
    }

    public void setLapMasukHarian(Integer lapMasukHarian) {
        this.lapMasukHarian = lapMasukHarian;
    }

    public Integer getLapMasukBulanan() {
        return lapMasukBulanan;
    }

    public void setLapMasukBulanan(Integer lapMasukBulanan) {
        this.lapMasukBulanan = lapMasukBulanan;
    }

    @Override
    public String toString() {
        return "HomePageModel{" +
                "pegawai=" + pegawai +
                ", lapHarian=" + lapHarian +
                ", lapBulanan=" + lapBulanan +
                ", lapMasukHarian=" + lapMasukHarian +
                ", lapMasukBulanan=" + lapMasukBulanan +
                '}';
    }
}
