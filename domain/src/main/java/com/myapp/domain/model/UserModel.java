package com.myapp.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserModel {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nip_user")
    @Expose
    private String nipUser;
    @SerializedName("username_user")
    @Expose
    private String usernameUser;
    @SerializedName("password_user")
    @Expose
    private String passwordUser;
    @SerializedName("nama_user")
    @Expose
    private String namaUser;
    @SerializedName("foto_user")
    @Expose
    private String fotoUser;
    @SerializedName("level_user")
    @Expose
    private String levelUser;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public String getUsernameUser() {
        return usernameUser;
    }

    public void setUsernameUser(String usernameUser) {
        this.usernameUser = usernameUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
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


    @Override
    public String toString() {
        return "UserModel{" +
                "idUser='" + idUser + '\'' +
                ", nipUser='" + nipUser + '\'' +
                ", usernameUser='" + usernameUser + '\'' +
                ", passwordUser='" + passwordUser + '\'' +
                ", namaUser='" + namaUser + '\'' +
                ", fotoUser='" + fotoUser + '\'' +
                ", levelUser='" + levelUser + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
//                ", deletedAt=" + deletedAt +
                '}';
    }
}
