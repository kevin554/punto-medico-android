package com.bo.punto.medico;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.bo.punto.medico.models.FragmentObservable;

public class Register extends AppCompatActivity {

    private View parent_view;
    private FragmentObservable fragmentObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        parent_view = findViewById(android.R.id.content);
        fragmentObservable = new FragmentObservable();

        Button signIn = findViewById(R.id.sign_in);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentObservable.setFragment("Profile");
                finish();
            }
        });
    }
}
