package com.bo.punto.medico.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bo.punto.medico.R;
import com.bo.punto.medico.models.ShopProduct;
import com.bo.punto.medico.utils.Tools;

import java.util.List;


public class AdapterGridShopProductCard extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ShopProduct> items;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public AdapterGridShopProductCard(Context context, List<ShopProduct> items) {
        this.items = items;
        ctx = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog, parent, false);
        return new OriginalViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            final ShopProduct p = items.get(position);
            view.title.setText(p.title);
            view.price.setText(p.price);
            Tools.displayImageOriginal(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView title;
        public TextView price;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            title = v.findViewById(R.id.title);
            price = v.findViewById(R.id.price);
            lyt_parent = v.findViewById(R.id.lyt_parent);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, ShopProduct obj, int pos);
    }

}