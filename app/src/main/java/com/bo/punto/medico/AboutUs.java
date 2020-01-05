package com.bo.punto.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class AboutUs extends Fragment {

    public AboutUs() {
    }

    public static AboutUs newInstance() {
        AboutUs fragment = new AboutUs();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_about_us, container, false);
        return root;
    }

}
