package com.myapp.domain.serialize.req;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestUbahProfil {

    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nip_user")
    @Expose
    private String nipUser;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("foto_user")
    @Expose
    private String fotoUser;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
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

    @Override
    public String toString() {
        return "RequestUbahProfil{" +
                "idUser='" + idUser + '\'' +
                ", nipUser='" + nipUser + '\'' +
                ", namaUser='" + namaUser + '\'' +
                ", fotoUser='" + fotoUser + '\'' +
                '}';
    }
}
