package com.myapp.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HomePageObject extends RealmObject {

    @PrimaryKey
    private String id;

    private Integer pegawai;

    private Integer lapHarian;

    private Integer lapBulanan;

    private Integer lapMasukHarian;

    private Integer lapMasukBulanan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

}
