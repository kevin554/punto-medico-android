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
        Catalog fragment = new Catalog();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_catalog, container, false);

        parent_view = root.findViewById(R.id.parent_view);
//        root.clearFocus();

        initComponent(root);

        return root;
    }

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

}
