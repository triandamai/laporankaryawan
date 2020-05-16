package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myapp.domain.realmobject.LaporanHarianObject;

public class LaporanHarianModel {
    @SerializedName("id_laporanharian")
    @Expose
    private String idLaporanharian;
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("id_outlet")
    @Expose
    private String idOutlet;
    @SerializedName("alamat_laporanharian")
    @Expose
    private String alamatLaporanharian;
    @SerializedName("latitude_laporanharian")
    @Expose
    private String latitudeLaporanharian;
    @SerializedName("longitude_laporanharian")
    @Expose
    private String longitudeLaporanharian;
    @SerializedName("keterangan_laporanharian")
    @Expose
    private String keteranganLaporanharian;
    @SerializedName("bukti_laporanharian")
    @Expose
    private String buktiLaporanharian;
    @SerializedName("status_laporanharian")
    @Expose
    private String statusLaporanharian;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    @SerializedName("user")
    @Expose
    private UserModel user;
    @SerializedName("outlet")
    @Expose
    private OutletModel outlet;

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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public OutletModel getOutlet() {
        return outlet;
    }

    public void setOutlet(OutletModel outlet) {
        this.outlet = outlet;
    }
    public static LaporanHarianModel covertdariobject(LaporanHarianObject o){
        LaporanHarianModel l = new LaporanHarianModel();
        UserModel u = new UserModel();
        u.setCreatedAt(o.getCreatedAt());
        u.setUpdatedAt(o.getUpdatedAt());
        u.setNipUser(o.getNipUser());
        u.setLevelUser(o.getLevelUser());
        u.setPasswordUser("tidak ditampilkan");
        u.setUsernameUser("tidak ditampilkan");
        u.setFotoUser(o.getFotoUser());
        u.setNamaUser(o.getNamaUser());
        u.setIdUser(o.getIdUser());

        KotaModel k = new KotaModel();
        k.setCreatedAt(o.getCreatedAt());
        k.setUpdatedAt(o.getUpdatedAt());
        k.setNamaKota(o.getNamaKota());
        k.setIdKota(o.getIdKota());

        OutletModel t = new OutletModel();
        t.setIdKota(o.getIdKota());
        t.setCreatedAt(o.getCreatedAt());
        t.setNamaOutlet(o.getNamaOutlet());
        t.setIdOutlet(o.getIdOutlet());
        t.setKota(k);
        t.setUpdatedAt(o.getUpdatedAt());




        l.setAlamatLaporanharian(o.getAlamatLaporanharian());
        l.setBuktiLaporanharian(o.getBuktiLaporanharian());
        l.setCreatedAt(o.getCreatedAt());
        l.setIdLaporanharian(o.getIdLaporanharian());
        l.setIdOutlet(o.getIdOutlet());
        l.setIdUser(o.getIdUser());
        l.setKeteranganLaporanharian(o.getKeteranganLaporanharian());
        l.setLatitudeLaporanharian(o.getLatitudeLaporanharian());
        l.setLongitudeLaporanharian(o.getLongitudeLaporanharian());
        l.setOutlet(t);
        l.setStatusLaporanharian(o.getStatusLaporanharian());
        l.setUpdatedAt(o.getUpdatedAt());
        l.setUser(u);
        return l;

    }
}
