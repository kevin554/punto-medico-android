package com.bo.punto.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bo.punto.medico.utils.FragmentListener;

public class Profile extends Fragment {

    private static FragmentListener listener;

    public Profile() {
    }

    public static Profile newInstance() {
        Profile fragment = new Profile();
        return fragment;
    }

    public static Fragment newInstance(MainActivity.SectionsPagerAdapter.FragmentProfileListener fragmentListener) {
        listener = fragmentListener;
        return newInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_profile, container, false);
        return root;
    }

//    private void initToolbar() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("View Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Tools.setSystemBarColor(this, R.color.purple_600);
//    }

}
