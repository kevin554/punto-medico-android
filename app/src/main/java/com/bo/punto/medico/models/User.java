package com.bo.punto.medico.models;

import io.realm.RealmList;
import io.realm.RealmObject;


public class User extends RealmObject {

    private String name;
    private String user;
    private String email;
    private String phone;
    private String cellphone;
    private String website;
    // Declare one-to-many relationships
    private RealmList<Item> items;

}
