package com.bo.punto.medico.models;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Item extends RealmObject {

    private String name;
    private String salePrice;
    private String regularPrice;
    private String added;
    private String status;
    private String phone;
    private String cellphone;
    private int seenAccount;
    private double rating;
    private String description;
    // Declare one-to-many relationships
    private RealmList<Comment> comments;
    private RealmList<Image> images;

}
