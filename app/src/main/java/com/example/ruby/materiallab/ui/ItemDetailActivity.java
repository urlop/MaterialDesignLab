package com.example.ruby.materiallab.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.utils.UIHelper;

/** Detail Activity for showing transitions animation.
 * Contains Circular Reveal Animation
 * Drawable transition
 * Image Shape
 * Palette
 *
 * More Information: https://guides.codepath.com/android/Shared-Element-Activity-Transition
 */
public class ItemDetailActivity extends AppCompatActivity {

    ImageView card_image;
    ImageView iv_shape_image;
    View v_background;
    TransitionDrawable drawable;

    boolean REVEAL_ANIMATION = false;
    boolean TRANSITION_ANIMATION = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        card_image = (ImageView) findViewById(R.id.card_image);
        iv_shape_image = (ImageView) findViewById(R.id.iv_shape_image);
        v_background = findViewById(R.id.v_background);
        drawable = (TransitionDrawable) card_image.getDrawable();

        if (REVEAL_ANIMATION) {
            getPalette();
            doReveal();
        }

        if(TRANSITION_ANIMATION) {
            doTransition();
        }
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
 // close this activity and return to preview activity (if there is any)
                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }
            };
            getWindow().getEnterTransition().addListener(mEnterTransitionListener);
        }
    }

    private void getPalette(){
        Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int defaultColor = 0x000000;
                v_background.setBackgroundColor(palette.getVibrantColor(defaultColor));
            }
        };

        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.place_des_vosges);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette.from(myBitmap).generate(paletteListener);
        }
    }

    @Override
    public void onBackPressed() {
        if (REVEAL_ANIMATION) {
            UIHelper.exitReveal(v_background);
        }
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}
