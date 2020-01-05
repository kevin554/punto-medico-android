package com.bo.punto.medico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.bo.punto.medico.R;
import com.bo.punto.medico.models.PriceModality;

import java.util.List;


public class PricesAdapter extends PagerAdapter {

    private List<PriceModality> plans;
    private Context context;

    public PricesAdapter(Context context, List<PriceModality> plans) {
        this.context = context;
        this.plans = plans;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.item_price, container, false);
        ((TextView) view.findViewById(R.id.title)).setText(plans.get(position).getTitle());
        ((TextView) view.findViewById(R.id.price)).setText(plans.get(position).getPrice());

        ListView listView = view.findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<>(context, R.layout.item_price_features, R.id.description, plans.get(position).getFeatures_array()));

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