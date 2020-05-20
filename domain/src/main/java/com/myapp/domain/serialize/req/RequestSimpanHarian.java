package com.myapp.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestSimpanHarian {

    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("id_outlet")
    @Expose
    private Integer idOutlet;
    @SerializedName("alamat_laporanharian")
    @Expose
    private String alamatLaporanharian;
    @SerializedName("latitude_laporanharian")
    @Expose
    private Integer latitudeLaporanharian;
    @SerializedName("longitude_laporanharian")
    @Expose
    private Integer longitudeLaporanharian;
    @SerializedName("keterangan_laporanharian")
    @Expose
    private String keteranganLaporanharian;
    @SerializedName("bukti_laporanharian")
    @Expose
    private String buktiLaporanharian;

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdOutlet() {
        return idOutlet;
    }

    public void setIdOutlet(Integer idOutlet) {
        this.idOutlet = idOutlet;
    }

    public String getAlamatLaporanharian() {
        return alamatLaporanharian;
    }

    public void setAlamatLaporanharian(String alamatLaporanharian) {
        this.alamatLaporanharian = alamatLaporanharian;
    }

    public Integer getLatitudeLaporanharian() {
        return latitudeLaporanharian;
    }

    public void setLatitudeLaporanharian(Integer latitudeLaporanharian) {
        this.latitudeLaporanharian = latitudeLaporanharian;
    }

    public Integer getLongitudeLaporanharian() {
        return longitudeLaporanharian;
    }

    public void setLongitudeLaporanharian(Integer longitudeLaporanharian) {
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
}
