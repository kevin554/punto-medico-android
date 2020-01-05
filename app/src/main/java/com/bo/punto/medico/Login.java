package com.bo.punto.medico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bo.punto.medico.utils.FragmentListener;


public class Login extends Fragment {

    private static final int REGISTER_REQUEST = 1;
    private static FragmentListener listener;

    public Login() {
    }

    public static Login newInstance() {
        return new Login();
    }

    public static Fragment newInstance(FragmentListener fragmentListener) {
        listener = fragmentListener;
        return newInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        Button login = root.findViewById(R.id.login);
        TextView tvRecoverPass = root.findViewById(R.id.recover_password);
        TextView tvSignUp = root.findViewById(R.id.sign_up);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSwitchToNextFragment();
            }
        });

        tvRecoverPass.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getContext(), PasswordForgotten.class));
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getContext(), Register.class), REGISTER_REQUEST);
            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REGISTER_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                listener.onSwitchToNextFragment();
            }
        }

    }

}
