package com.bo.punto.medico.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bo.punto.medico.R;
import com.bo.punto.medico.utils.Tools;
import com.hootsuite.nachos.NachoTextView;
import com.hootsuite.nachos.terminator.ChipTerminatorHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ItemDetail extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PICK_IMAGE = 2;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        imageView = findViewById(R.id.image_1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadImageDialog();
            }
        });

        initToolbar();
        initComponent();

        TextView user = findViewById(R.id.user);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ItemDetail.this, UserProfile.class));
            }
        });
    }

    private void showLoadImageDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Subir una foto");
        builder.setPositiveButton("Seleccionar de la galeria", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dispatchSelectPictureEvent();
            }
        });

        builder.setNegativeButton("Abrir camara", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dispatchTakePictureEvent();
            }
        });

        builder.show();
    }

    private void dispatchTakePictureEvent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchSelectPictureEvent() {
        Intent selectPictureIntent = new Intent();
        selectPictureIntent.setType("image/*");
        selectPictureIntent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(selectPictureIntent, "Seleccione una imagen"), PICK_IMAGE);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initComponent() {
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_1), R.drawable.image_shop_9);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_2), R.drawable.image_shop_10);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_3), R.drawable.image_shop_11);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_4), R.drawable.image_shop_12);
        Tools.displayImageOriginal(this, (ImageView) findViewById(R.id.image_5), R.drawable.image_shop_13);

        NachoTextView et_tag = findViewById(R.id.et_tag);
        List<String> items = new ArrayList<>();
        items.add("Mesas quirurgicas");
        items.add("Incubadoras");
        et_tag.setText(items);
        et_tag.addChipTerminator('\n', ChipTerminatorHandler.BEHAVIOR_CHIPIFY_ALL);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data == null) {
                return;
            }

            Bundle extras = data.getExtras();

            if (extras == null) {
                return;
            }

            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);

            if (imageBitmap == null) {
                return;
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            String encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
        }

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data == null) {
                return;
            }

            Uri imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imageView.setImageBitmap(bitmap);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                byte[] byteArray = outputStream.toByteArray();

                String encodedString = Base64.encodeToString(byteArray, Base64.NO_WRAP);
            } catch (IOException ignored) {

            }
        }
    }

}
