package com.bo.punto.medico.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bo.punto.medico.R;
import com.bo.punto.medico.activities.ItemDetail;
import com.bo.punto.medico.adapters.AdapterGridShopProductCard;
import com.bo.punto.medico.models.DataGenerator;
import com.bo.punto.medico.models.ShopProduct;
import com.bo.punto.medico.utils.EndlessRecyclerViewScrollListener;
import com.bo.punto.medico.utils.SpacingItemDecoration;
import com.bo.punto.medico.utils.Tools;

import java.util.List;


public class Catalog extends Fragment {

    private SwipeRefreshLayout contentSwipeRefreshLayout;
    private EndlessRecyclerViewScrollListener scrollListener;
    private RecyclerView recyclerView;
    private AdapterGridShopProductCard mAdapter;
    private EditText searchEditText;

    public Catalog() {
    }

    public static Catalog newInstance() {
        return new Catalog();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_catalog, container, false);

        initComponent(root);
        fetchCatalog();

        return root;
    }

    private void initComponent(View view) {
        searchEditText = view.findViewById(R.id.et_search);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(gridLayoutManager);
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
            }
        });

        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                fetchCatalog();
            }
        };

        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);

        contentSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        contentSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                confirmRefreshCatalog();
            }
        });

        ImageButton imgSearch = view.findViewById(R.id.img_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchCatalog();
            }
        });
    }

    @SuppressLint("ResourceType")
    private void fetchCatalog() {

    }

    private void confirmRefreshCatalog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("¿Desea actualizar el catálogo?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fetchCatalog();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                contentSwipeRefreshLayout.setRefreshing(false);
            }
        });

        builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                contentSwipeRefreshLayout.setRefreshing(false);
            }
        });

        builder.show();
    }

}
