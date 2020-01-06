package com.bo.punto.medico.utils;

import android.app.Application;

import com.facebook.stetho.Stetho;

import io.realm.Realm;


public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
        Realm.init(this);
        instancia = this;
    }

    private static MyApp instancia;

    public static MyApp getInstancia() {
        return instancia;
    }

}
