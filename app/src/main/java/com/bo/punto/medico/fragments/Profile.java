package com.bo.punto.medico.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bo.punto.medico.R;
import com.bo.punto.medico.activities.PublishNewItem;
import com.bo.punto.medico.utils.FragmentListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Profile extends Fragment {

    private static FragmentListener listener;

    public Profile() {
    }

    public static Profile newInstance() {
        return new Profile();
    }

    public static Fragment newInstance(FragmentListener fragmentListener) {
        listener = fragmentListener;
        return newInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        ImageButton action = root.findViewById(R.id.action);
        FloatingActionButton fab = root.findViewById(R.id.fab);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmLogOut();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), PublishNewItem.class));
            }
        });

        return root;
    }

    private void confirmLogOut() {
        if (getContext() == null) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("¿Desea cerrar su sesión?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                logOut();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }

    private void logOut() {
        listener.onSwitchToNextFragment();
    }

}
