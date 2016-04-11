package com.example.ruby.materiallab.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.ui.adapters.ContentAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    RecyclerView rv_list;

    private static MainFragment instance;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        setupView(v);

        return v;
    }

    private void setupView(View v){
        rv_list = (RecyclerView) v.findViewById(R.id.rv_list);

        ContentAdapter adapter = new ContentAdapter(getActivity());
        rv_list.setAdapter(adapter);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
}
