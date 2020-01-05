package com.bo.punto.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bo.punto.medico.utils.FragmentListener;

public class Login extends Fragment {

    private static MainActivity.SectionsPagerAdapter.FragmentProfileListener listener;

    public Login() {
    }

    public static Login newInstance() {
        Login fragment = new Login();
        return fragment;
    }

    public static Fragment newInstance(MainActivity.SectionsPagerAdapter.FragmentProfileListener fragmentListener) {
        listener = fragmentListener;
        return newInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_login, container, false);

        TextView tvRecoverPass = root.findViewById(R.id.recover_password);
        TextView tvSignUp = root.findViewById(R.id.sign_up);


        tvRecoverPass.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getContext(), PasswordForgotten.class));
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                listener.onSwitchToNextFragment();
//                startActivity(new Intent(getContext(), Register.class));
            }
        });

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
