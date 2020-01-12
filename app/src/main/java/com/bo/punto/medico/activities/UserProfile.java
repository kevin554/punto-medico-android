package com.bo.punto.medico.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bo.punto.medico.R;
import com.bo.punto.medico.adapters.AdapterGridShopProductCard;
import com.bo.punto.medico.models.DataGenerator;
import com.bo.punto.medico.models.ShopProduct;
import com.bo.punto.medico.utils.SpacingItemDecoration;
import com.bo.punto.medico.utils.Tools;

import java.util.List;


public class UserProfile extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterGridShopProductCard mAdapter;

    public UserProfile() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        loadPosts();
    }

    private void loadPosts() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(this, 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<ShopProduct> items = DataGenerator.getShoppingProduct(this);

        //set data and list adapter
        mAdapter = new AdapterGridShopProductCard(this, items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridShopProductCard.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, int position) {
//                Intent returnIntent = new Intent();
//                returnIntent.putExtra("result", result);
//                setResult(Activity.RESULT_OK,returnIntent);
//                finish();

                // If you don't want to return data:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
    }

}
