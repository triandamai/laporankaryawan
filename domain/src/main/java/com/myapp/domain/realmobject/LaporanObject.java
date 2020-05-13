package com.myapp.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LaporanObject extends RealmObject {
  @PrimaryKey
    private String idLaporanharian;

    private String idUser;

    private String idOutlet;

    private String alamatLaporanharian;

    private String latitudeLaporanharian;

    private String longitudeLaporanharian;

    private String keteranganLaporanharian;

    private String buktiLaporanharian;

    private String statusLaporanharian;

    private String createdAt;

    private String updatedAt;

    private String idKota;

    private String namaOutlet;

    private String nipUser;

    private String usernameUser;

    private String namaUser;

    private String fotoUser;

    private String levelUser;

    private String namaKota;

    public String getIdLaporanharian() {
        return idLaporanharian;
    }

    public void setIdLaporanharian(String idLaporanharian) {
        this.idLaporanharian = idLaporanharian;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdOutlet() {
        return idOutlet;
    }

    public void setIdOutlet(String idOutlet) {
        this.idOutlet = idOutlet;
    }

    public String getAlamatLaporanharian() {
        return alamatLaporanharian;
    }

    public void setAlamatLaporanharian(String alamatLaporanharian) {
        this.alamatLaporanharian = alamatLaporanharian;
    }

    public String getLatitudeLaporanharian() {
        return latitudeLaporanharian;
    }

    public void setLatitudeLaporanharian(String latitudeLaporanharian) {
        this.latitudeLaporanharian = latitudeLaporanharian;
    }

    public String getLongitudeLaporanharian() {
        return longitudeLaporanharian;
    }

    public void setLongitudeLaporanharian(String longitudeLaporanharian) {
        this.longitudeLaporanharian = longitudeLaporanharian;
    }

    public String getKeteranganLaporanharian() {
        return keteranganLaporanharian;
    }

    public void setKeteranganLaporanharian(String keteranganLaporanharian) {
        this.keteranganLaporanharian = keteranganLaporanharian;
    }

    public String getBuktiLaporanharian() {
        return buktiLaporanharian;
    }

    public void setBuktiLaporanharian(String buktiLaporanharian) {
        this.buktiLaporanharian = buktiLaporanharian;
    }

    public String getStatusLaporanharian() {
        return statusLaporanharian;
    }

    public void setStatusLaporanharian(String statusLaporanharian) {
        this.statusLaporanharian = statusLaporanharian;
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

    public String getNipUser() {
        return nipUser;
    }

    public void setNipUser(String nipUser) {
        this.nipUser = nipUser;
    }

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
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

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }
}
