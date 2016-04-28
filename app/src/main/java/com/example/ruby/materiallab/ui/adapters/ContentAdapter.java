package com.example.ruby.materiallab.ui.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.ui.ItemDetailActivity;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LENGTH = 7;
    private final Context context;

    public ContentAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return LENGTH;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView iv_image;
        final TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ItemDetailActivity.class);
            /*Pair<View, String> p1 = Pair.create((View)iv_image, "image");
            Pair<View, String> p2 = Pair.create((View)tv_title, "title");
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, p2, p1);*/
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation((Activity) context, iv_image, "image");
            context.startActivity(intent, options.toBundle());
        }
    }
}
