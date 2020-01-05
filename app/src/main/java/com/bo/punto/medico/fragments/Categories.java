package com.bo.punto.medico.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bo.punto.medico.R;

public class Categories extends Fragment {

    public Categories() {
    }

    public static Categories newInstance() {
        Categories fragment = new Categories();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categories, container, false);
        return root;
    }

}
