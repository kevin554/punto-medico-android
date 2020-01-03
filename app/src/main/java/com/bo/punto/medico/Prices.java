package com.bo.punto.medico;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;


public class Prices extends Fragment {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private ListView listView;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<PriceModality> plans = new ArrayList<>();

//    private String about_title_array[] = {
//            "BÁSICO",
//            "PREMIUM",
//    };
//    private String about_description_array[] = {
//            "Choose your destination, plan Your trip. Pick the best place for Your holiday",
//            "Select the day, pick Your ticket. We give you the best prices. We guarantee!",
//    };
//    private int about_images_array[] = {
//            R.drawable.img_wizard_1,
//            R.drawable.img_wizard_2
//    };

    public Prices() {
    }

    public static Prices newInstance() {
        Prices fragment = new Prices();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_card_wizard_overlap, container, false);

        plans.add(new PriceModality("BÁSICO", "15 Bs", "2 Fotos", "Por 15 dias de publicación"));
        plans.add(new PriceModality("PREMIUM", "40 Bs", "Email marketing", "Anuncio destacado"));

        viewPager = root.findViewById(R.id.view_pager);
        dotsLayout = root.findViewById(R.id.layoutDots);

        bottomProgressDots(0);

        myViewPagerAdapter = new MyViewPagerAdapter();
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

    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.item_card_wizard, container, false);
            ((TextView) view.findViewById(R.id.title)).setText(plans.get(position).title);

            listView = view.findViewById(R.id.list_view);
            listView.setAdapter(new ArrayAdapter<>(getContext(), R.layout.layout_item_plan_features, R.id.description, plans.get(position).features_array));

//            ((TextView) view.findViewById(R.id.description)).setText(about_description_array[position]);
//            ((ImageView) view.findViewById(R.id.image)).setImageResource(about_images_array[position]);

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return plans.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    class PriceModality {

        public String title;
        public String price;
        public String features_array[];
        public int image;

        public PriceModality(String title, String price, String... features_array) {
            this.title = title;
            this.price = price;
            this.features_array = features_array;
        }
    }

}