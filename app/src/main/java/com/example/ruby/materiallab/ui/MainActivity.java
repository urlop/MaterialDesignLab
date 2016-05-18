package com.example.ruby.materiallab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.ui.adapters.ContentAdapter;

/**
 * Principal Activity.
 * Contains FAB, CardViews & Parallax
 * Animations and Animator
 *
 * Icons found in: https://design.google.com/icons/
 * Animated Vector Drawables example found in: https://github.com/udacity/ud862-samples/blob/master/TickCross/app/src/main/java/com/example/android/tickcross/MainActivity.java
 * Based on: http://inthecheesefactory.com/blog/android-design-support-library-codelab/en
 */
public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();
        fabAnimation();
    }

    private void setupView(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, ButtonsActivity.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        ContentAdapter adapter = new ContentAdapter(this);
        rv_list.setAdapter(adapter);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    private void fabAnimation() {
        Animation show_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        fab.startAnimation(show_fab);
        fab.setClickable(true);

        /*AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fab_show_3);
        set.setTarget(fab);
        set.start();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()){
            case R.id.action_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_tab_header:
                intent = new Intent(this, TabsHeaderActivity.class);
                startActivity(intent);
                break;
            case R.id.action_buttons:
                intent = new Intent(this, ButtonsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_fragment_transition:
                intent = new Intent(this, TransitionFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.action_navigation_drawer:
                intent = new Intent(this, DrawerActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
