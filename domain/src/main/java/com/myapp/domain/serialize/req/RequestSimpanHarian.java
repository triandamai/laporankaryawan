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
    private Double latitudeLaporanharian;
    @SerializedName("longitude_laporanharian")
    @Expose
    private Double longitudeLaporanharian;
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

    public Double getLatitudeLaporanharian() {
        return latitudeLaporanharian;
    }

    public void setLatitudeLaporanharian(Double latitudeLaporanharian) {
        this.latitudeLaporanharian = latitudeLaporanharian;
    }

    public Double getLongitudeLaporanharian() {
        return longitudeLaporanharian;
    }

    public void setLongitudeLaporanharian(Double longitudeLaporanharian) {
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

    @Override
    public String toString() {
        return "RequestSimpanHarian{" +
                "idUser=" + idUser +
                ", idOutlet=" + idOutlet +
                ", alamatLaporanharian='" + alamatLaporanharian + '\'' +
                ", latitudeLaporanharian=" + latitudeLaporanharian +
                ", longitudeLaporanharian=" + longitudeLaporanharian +
                ", keteranganLaporanharian='" + keteranganLaporanharian + '\'' +
                ", buktiLaporanharian='" + buktiLaporanharian + '\'' +
                '}';
    }
}
