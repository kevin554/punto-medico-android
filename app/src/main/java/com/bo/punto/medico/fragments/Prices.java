package com.bo.punto.medico.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bo.punto.medico.R;
import com.bo.punto.medico.adapters.PricesAdapter;
import com.bo.punto.medico.models.PriceModality;

import java.util.ArrayList;
import java.util.List;


public class Prices extends Fragment {

    private LinearLayout dotsLayout;
    private List<PriceModality> plans;

    public Prices() {
    }

    public static Prices newInstance() {
        return new Prices();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_prices, container, false);

        if (plans == null) {
            plans = new ArrayList<>();

            plans.add(new PriceModality("BÁSICO", "15 Bs", "2 Fotos", "Por 15 dias de publicación"));
            plans.add(new PriceModality("PREMIUM", "40 Bs", "Email marketing", "Anuncio destacado"));
        }

        ViewPager viewPager = root.findViewById(R.id.view_pager);
        dotsLayout = root.findViewById(R.id.layoutDots);

        bottomProgressDots(0);

        PricesAdapter myViewPagerAdapter = new PricesAdapter(getContext(), plans);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(final int position) {
                bottomProgressDots(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

        });

        viewPager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.viewpager_margin_overlap));
        viewPager.setOffscreenPageLimit(4);

        return root;
    }

    private void bottomProgressDots(int current_index) {
        ImageView[] dots = new ImageView[2];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(getContext());
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.grey_20), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.light_green_600), PorterDuff.Mode.SRC_IN);
        }
    }


}