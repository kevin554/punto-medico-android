package com.bo.punto.medico;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bo.punto.medico.adapters.AdapterGridShopProductCard;
import com.bo.punto.medico.models.DataGenerator;
import com.bo.punto.medico.models.ShopProduct;
import com.bo.punto.medico.utils.SpacingItemDecoration;
import com.bo.punto.medico.utils.Tools;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class Catalog extends Fragment {

    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterGridShopProductCard mAdapter;

    public Catalog() {
    }

    public static Catalog newInstance() {
        Catalog catalog = new Catalog();
        return catalog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_shopping_product_grid, container, false);

        parent_view = root.findViewById(R.id.parent_view);

//        initToolbar();
        initComponent(root);

        return root;
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shopping_product_grid);
//        parent_view = findViewById(R.id.parent_view);
//
//        initToolbar();
//        initComponent();
//    }

//    private void initToolbar() {
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setNavigationIcon(R.drawable.ic_menu);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Products");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }

    private void initComponent(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.addItemDecoration(new SpacingItemDecoration(2, Tools.dpToPx(getContext(), 8), true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

        List<ShopProduct> items = DataGenerator.getShoppingProduct(getContext());

        //set data and list adapter
        mAdapter = new AdapterGridShopProductCard(getContext(), items);
        recyclerView.setAdapter(mAdapter);

        // on item list clicked
        mAdapter.setOnItemClickListener(new AdapterGridShopProductCard.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, int position) {
                startActivity(new Intent(getContext(), ItemDetail.class));
//                Snackbar.make(parent_view, "Item " + obj.title + " clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnMoreButtonClickListener(new AdapterGridShopProductCard.OnMoreButtonClickListener() {
            @Override
            public void onItemClick(View view, ShopProduct obj, MenuItem item) {
                Snackbar.make(parent_view, obj.title + " (" + item.getTitle() + ") clicked", Snackbar.LENGTH_SHORT).show();
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_cart_setting, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        } else {
//            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
