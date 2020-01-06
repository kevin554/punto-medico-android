package com.bo.punto.medico.models;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Category extends RealmObject {

    private String name;
    // Declare one-to-many relationships
    private RealmList<Subcategory> subcategories;

}
