package com.bo.punto.medico.models;

import java.util.Observable;

public class FragmentObservable extends Observable {

    private String fragment;

    public FragmentObservable() {
    }

    public String getCarrera() {
        return fragment;
    }

    public void setFragment(String fragment) {
        this.fragment = fragment;

        setChanged();
        notifyObservers(fragment);
    }

}
