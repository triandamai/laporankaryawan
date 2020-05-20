package com.myapp.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HomePageKaryawan extends RealmObject {
    @PrimaryKey
    private Integer id;
    private Integer lapHarian;
    private Integer lapBulanan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
