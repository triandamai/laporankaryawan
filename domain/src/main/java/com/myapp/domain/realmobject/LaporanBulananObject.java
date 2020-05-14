package com.myapp.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LaporanBulananObject extends RealmObject {
  @PrimaryKey

  private String idLaporanbulanan;

    private String idUser;

    private String isiLaporanbulanan;

    private String statusLaporanbulanan;

    private String createdAt;

    private String updatedAt;

    private String nipUser;

    private String namaUser;

    private String fotoUser;

    private String levelUser;

    public String getIdLaporanbulanan() {
        return idLaporanbulanan;
    }

    public void setIdLaporanbulanan(String idLaporanbulanan) {
        this.idLaporanbulanan = idLaporanbulanan;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIsiLaporanbulanan() {
        return isiLaporanbulanan;
    }

    public void setIsiLaporanbulanan(String isiLaporanbulanan) {
        this.isiLaporanbulanan = isiLaporanbulanan;
    }

    public String getStatusLaporanbulanan() {
        return statusLaporanbulanan;
    }

    public void setStatusLaporanbulanan(String statusLaporanbulanan) {
        this.statusLaporanbulanan = statusLaporanbulanan;
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

    public String getNipUser() {
        return nipUser;
    }

    public void setNipUser(String nipUser) {
        this.nipUser = nipUser;
    }


    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getFotoUser() {
        return fotoUser;
    }

    public void setFotoUser(String fotoUser) {
        this.fotoUser = fotoUser;
    }

    public String getLevelUser() {
        return levelUser;
    }

    public void setLevelUser(String levelUser) {
        this.levelUser = levelUser;
    }
}

