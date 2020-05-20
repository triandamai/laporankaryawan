package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomePageKaryawanModel {
    @SerializedName("lap_harian")
    @Expose
    private Integer lapHarian;
    @SerializedName("lap_bulanan")
    @Expose
    private Integer lapBulanan;

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

    @Override
    public String toString() {
        return "HomePageKaryawanModel{" +
                "lapHarian=" + lapHarian +
                ", lapBulanan=" + lapBulanan +
                '}';
    }
}
