package com.example.ruby.materiallab.ui.fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.graphics.Palette;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.utils.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {

    ImageView card_image;
    ImageView iv_shape_image;
    View v_background;
    TransitionDrawable drawable;

    boolean REVEAL_ANIMATION = false;
    boolean TRANSITION_ANIMATION = false;

    private static ItemDetailFragment instance;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    public static ItemDetailFragment getInstance() {
        if (instance == null) {
            instance = new ItemDetailFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_item_detail, container, false);

        card_image = (ImageView) v.findViewById(R.id.card_image);
        iv_shape_image = (ImageView) v.findViewById(R.id.iv_shape_image);
        v_background = v.findViewById(R.id.v_background);
        drawable = (TransitionDrawable) card_image.getDrawable();

        String transitionName = "";

        Bundle bundle = getArguments();
        if (bundle != null) {
            transitionName = bundle.getString("TRANS_NAME");
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            card_image.setTransitionName(transitionName);
        }

        if (REVEAL_ANIMATION) {
            doReveal();
        }

        if(TRANSITION_ANIMATION) {
            doTransition();
        }

        getPalette();

        return v;
    }

    private void doTransition(){
        drawable.startTransition(2000);
    }

    private void doReveal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Transition.TransitionListener mEnterTransitionListener = new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    UIHelper.enterReveal(v_background);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            };
            getActivity().getWindow().getEnterTransition().addListener(mEnterTransitionListener);
        }
    }

    private void getPalette(){
        Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int defaultColor = 0x000000;
                v_background.setBackgroundColor(palette.getVibrantColor(palette.getVibrantColor(defaultColor)));
            }
        };

        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.kitten_2);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette.from(myBitmap).generate(paletteListener);
        }
    }

    /*@Override
    public void setExitSharedElementCallback(SharedElementCallback callback) {
        if (REVEAL_ANIMATION) {
            UIHelper.exitReveal(v_background);
        }

        super.setExitSharedElementCallback(callback);
    }*/
}
