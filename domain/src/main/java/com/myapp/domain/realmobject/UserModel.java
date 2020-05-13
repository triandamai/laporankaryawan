package com.myapp.domain.realmobject;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserModel extends RealmObject {
    @PrimaryKey
    private String idUser;

    private String nipUser;

    private String usernameUser;

    private String passwordUser;

    private String namaUser;

    private String fotoUser;

    private String levelUser;

    private String createdAt;

    private String updatedAt;
}
