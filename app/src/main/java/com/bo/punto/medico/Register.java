package com.bo.punto.medico;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        parent_view = findViewById(android.R.id.content);

//        Tools.setSystemBarColor(this, android.R.color.white);
//        Tools.setSystemBarLight(this);
    }
}
