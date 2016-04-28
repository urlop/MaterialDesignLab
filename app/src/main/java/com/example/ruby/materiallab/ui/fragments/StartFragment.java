package com.example.ruby.materiallab.ui.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ruby.materiallab.R;

import java.util.Random;

public class StartFragment extends Fragment implements AbsListView.OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        String [] strings = {"First Element", "Second Element", "Third Element"};
        MyListAdapter myListAdapter = new MyListAdapter(getActivity(), R.layout.list_item, strings);
        listView.setAdapter(myListAdapter);

        listView.setOnItemClickListener(this);

        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.image_cross);
        set.setTarget(view.findViewById(R.id.imageCross));
        set.start();

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String imageTransitionName = "";
        String textTransitionName = "";

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

        ImageView staticImage = (ImageView) getView().findViewById(R.id.imageView);

        EndFragment endFragment = new EndFragment();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementReturnTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_transform));
            setExitTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            endFragment.setSharedElementEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(R.transition.change_image_transform));
            endFragment.setEnterTransition(TransitionInflater.from(
                    getActivity()).inflateTransition(android.R.transition.fade));

            imageTransitionName = imageView.getTransitionName();
            textTransitionName = textView.getTransitionName();
        }

        Bundle bundle = new Bundle();
        bundle.putString("TRANS_NAME", imageTransitionName);
        bundle.putString("ACTION", textView.getText().toString());
        bundle.putString("TRANS_TEXT", textTransitionName);
        bundle.putParcelable("IMAGE", ((BitmapDrawable) imageView.getDrawable()).getBitmap());
        endFragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, endFragment)
                .addToBackStack("Fragment2")
                .addSharedElement(imageView, imageTransitionName)
                .addSharedElement(textView, textTransitionName)
                .addSharedElement(staticImage, "imageTransition")
                .commit();
    }
}

class MyListAdapter extends ArrayAdapter<String> {

    public MyListAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);
        }

        String listItem = getItem(position);

        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(listItem);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setImageResource(getRandomImage());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.setTransitionName("trans_text" + position);
            imageView.setTransitionName("trans_image" + position);
        }

        return view;
    }

    private int getRandomImage() {
        if (new Random().nextInt(2) == 1)
            return R.drawable.ic_notifications_black_24dp;
        return R.drawable.ic_info_black_24dp;
    }
}
