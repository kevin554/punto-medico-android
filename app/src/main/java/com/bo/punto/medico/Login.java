package com.bo.punto.medico;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Login extends Fragment {

    public Login() {
    }

    public static Login newInstance() {
        Login fragment = new Login();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login, container, false);
        return root;
    }

//    private View parent_view;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        parent_view = findViewById(android.R.id.content);
//    }
}
