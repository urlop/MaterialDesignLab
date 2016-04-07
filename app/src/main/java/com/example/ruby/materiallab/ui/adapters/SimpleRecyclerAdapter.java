package com.example.ruby.materiallab.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruby.materiallab.R;

import java.util.List;

/**
 * Created by Suleiman on 14-04-2015.
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.VersionViewHolder> {
    List<String> items;

    public SimpleRecyclerAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        versionViewHolder.title.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder{
        public TextView title;

        public VersionViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listitem_name);
        }
    }
}
