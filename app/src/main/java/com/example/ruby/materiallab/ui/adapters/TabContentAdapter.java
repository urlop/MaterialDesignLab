package com.example.ruby.materiallab.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruby.materiallab.R;

import java.util.List;

public class TabContentAdapter extends RecyclerView.Adapter<TabContentAdapter.VersionViewHolder> {
    List<String> items;

    public TabContentAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler, viewGroup, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {
        versionViewHolder.tv_title.setText(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_title;

        public VersionViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }
}
