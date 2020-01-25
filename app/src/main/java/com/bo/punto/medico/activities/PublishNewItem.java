package com.bo.punto.medico.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bo.punto.medico.R;
import com.bo.punto.medico.utils.FileUtil;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import id.zelory.compressor.Compressor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class PublishNewItem extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_FIRST_IMAGE = 1;
    private static final int PICK_SECOND_IMAGE = 2;
    private static final int PICK_THIRD_IMAGE = 3;
    private static final int PICK_FOURTH_IMAGE = 4;

    private ImageView imageView1, imageView2, imageView3, imageView4;
    private TextInputEditText titleET, descET, tagsET, salePriceET, regularPriceET, cellphoneET, addressET;
    private TextInputLayout titleTIL, salePriceTIL, cellphoneTIL, addressTIL;
    private Spinner categorySpinner;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_new_item);

        initComponents();
        initSpinners();
        initToolbar();
    }

    private void initComponents() {
        titleET = findViewById(R.id.titleET);
        descET = findViewById(R.id.descET);
        tagsET = findViewById(R.id.tagsET);
        salePriceET = findViewById(R.id.salePriceET);
        regularPriceET = findViewById(R.id.regularPriceET);
        cellphoneET = findViewById(R.id.cellphoneET);
        addressET = findViewById(R.id.addressET);

        titleTIL = findViewById(R.id.titleTIL);
        cellphoneTIL = findViewById(R.id.cellphoneTIL);
        addressTIL = findViewById(R.id.addressTIL);

        salePriceTIL = findViewById(R.id.salePriceTIL);
        imageView1 = findViewById(R.id.image_1);
        imageView2 = findViewById(R.id.image_2);
        imageView3 = findViewById(R.id.image_3);
        imageView4 = findViewById(R.id.image_4);

        radioGroup = findViewById(R.id.itemState);
        Button submit = findViewById(R.id.submitBtn);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        submit.setOnClickListener(this);

        imageView3.setVisibility(View.GONE);
        imageView4.setVisibility(View.GONE);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Crear un anuncio");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initSpinners() {
        categorySpinner = findViewById(R.id.spinnerCategory);

        String[] items = new String[] {"Equipos medicos", "Equipos de laboratorio",
                "Equipos de odontologia", "Equipos de estetica", "Insumos medicos y de laboratorio",
                "Insumos de odontologia", "Respuestas y accesorios", "Servicios y otros",
                "SELECCIONE UNA CATEGORIA"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items) {

            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"SELECCIONE UNA CATEGORIA"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }

        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adapter.add("Item 1");
//        adapter.add("Item 2");
//        adapter.add("Hint to be displayed");

        categorySpinner.setAdapter(adapter);
        categorySpinner.setSelection(adapter.getCount()); //display hint
    }

    private void validateForm() {
        titleTIL.setError(null);
        salePriceTIL.setError(null);
        cellphoneTIL.setError(null);
        addressTIL.setError(null);

        boolean isValid = true;

        String title = titleET.getText().toString().trim();
        String desc = descET.getText().toString().trim();
        String tags = tagsET.getText().toString().trim();
        String salePriceStr = salePriceET.getText().toString().trim();
        String regularPriceStr = regularPriceET.getText().toString().trim();
        String cellphone = cellphoneET.getText().toString().trim();
        String address = addressET.getText().toString().trim();
        String category = categorySpinner.getSelectedItem().toString();
        int radioButtonId = radioGroup.getCheckedRadioButtonId();

        String firstImage = getStringImage(imageView1.getDrawable());
        String secondImage = getStringImage(imageView2.getDrawable());
        String thirdImage = getStringImage(imageView3.getDrawable());
        String fourthImage = getStringImage(imageView4.getDrawable());

        /* THERE IS NOTHING SELECTED */
        if (categorySpinner.getSelectedItemPosition() == categorySpinner.getCount()) {
            Toast.makeText(this, "Debe seleccionar una categoria.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (title.isEmpty()) {
            titleTIL.setError("Debe colocar un título.");
            isValid = false;
        }

        if (salePriceStr.isEmpty()) {
            salePriceTIL.setError("Debe colocar un precio de venta.");
            isValid = false;
        }

        if (cellphone.isEmpty()) {
            cellphoneTIL.setError("Debe colocar un teléfono de referencia.");
            isValid = false;
        }

        if (address.isEmpty()) {
            addressTIL.setError("Debe colocar su ciudad.");
            isValid = false;
        }

        if (radioButtonId == -1) {
            Toast.makeText(this, "Debe especificar el estado del equipo.", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        String itemState = "";

        switch (radioButtonId) {
            case R.id.radio_new:
                itemState = "";
                break;

            case R.id.radio_used:
                itemState = "";
                break;
        }

        if (!isValid) {
            return;
        }
    }

    private void dispatchSelectPictureEvent(int requestCode) {
        Intent selectPictureIntent = new Intent();
        selectPictureIntent.setType("image/*");
        selectPictureIntent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(selectPictureIntent, "Seleccione una imagen"), requestCode);
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCodeIsFromPickImage(requestCode) && resultCode == RESULT_OK) {
            if (data == null) {
                return;
            }

            Uri imageUri = data.getData();

            try {
                File imageFile = FileUtil.from(this, imageUri);

                Disposable subscribe = new Compressor(this)
                        .compressToFileAsFlowable(imageFile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<File>() {
                            @Override
                            public void accept(File file) {
                                Bitmap imageBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

                                switch (requestCode) {
                                    case PICK_FIRST_IMAGE:
                                        imageView1.setImageBitmap(imageBitmap);
                                        break;

                                    case PICK_SECOND_IMAGE:
                                        imageView2.setImageBitmap(imageBitmap);
                                        break;

                                    case PICK_THIRD_IMAGE:
                                        imageView3.setImageBitmap(imageBitmap);
                                        break;

                                    case PICK_FOURTH_IMAGE:
                                        imageView4.setImageBitmap(imageBitmap);
                                        break;
                                }

                                String size = String.format("Size : %s", getReadableFileSize(file.length()));
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                Toast.makeText(PublishNewItem.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (IOException ignored) {

            }
        }
    }

    private boolean requestCodeIsFromPickImage(int requestCode) {
        return requestCode == PICK_FIRST_IMAGE || requestCode == PICK_SECOND_IMAGE ||
                requestCode == PICK_THIRD_IMAGE || requestCode == PICK_FOURTH_IMAGE;
    }

    public String getReadableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }

        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public String getStringImage(Bitmap imageBitmap){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        byte[] byteArray = outputStream.toByteArray();

        return Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }

    public String getStringImage(Drawable drawable) {
        try {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            return getStringImage(bitmap);
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_1:
                dispatchSelectPictureEvent(PICK_FIRST_IMAGE);
                break;

            case R.id.image_2:
                dispatchSelectPictureEvent(PICK_SECOND_IMAGE);
                break;

            case R.id.image_3:
                dispatchSelectPictureEvent(PICK_THIRD_IMAGE);
                break;

            case R.id.image_4:
                dispatchSelectPictureEvent(PICK_FOURTH_IMAGE);
                break;

            case R.id.submitBtn:
                validateForm();
                break;
        }
    }

}
