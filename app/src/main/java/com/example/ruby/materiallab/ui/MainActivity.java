package com.example.ruby.materiallab.ui;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.ruby.materiallab.R;
import com.example.ruby.materiallab.ui.adapters.ContentAdapter;
import com.example.ruby.materiallab.ui.adapters.ViewPagerAdapter;
import com.example.ruby.materiallab.ui.fragments.ItemDetailFragment;
import com.example.ruby.materiallab.ui.fragments.MainFragment;
import com.example.ruby.materiallab.ui.fragments.OneFragment;
import com.example.ruby.materiallab.ui.fragments.ThreeFragment;
import com.example.ruby.materiallab.ui.fragments.TwoFragment;
import com.example.ruby.materiallab.utils.DetailsTransition;

/**
 * Principal Activity.
 * Contains FAB, CardViews & Parallax
 * Animations and Animator
 *
 * Icons found in: https://design.google.com/icons/
 * http://inthecheesefactory.com/blog/android-design-support-library-codelab/en
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener{

    FloatingActionButton fab;

    private DrawerLayout mDrawerLayout;

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

        setupFragment();
        setupNavigationDrawer();
    }

    private void fabAnimation() {
        Animation show_fab = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        fab.startAnimation(show_fab);
        fab.setClickable(true);

        /*AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fab_show_3);
        set.setTarget(fab);
        set.start();*/
    }

    private void setupNavigationDrawer() {
        // Create Navigation drawer and inflate layout
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        // Adding menu icon to Toolbar
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        // Set behavior of Navigation drawer
        if (navigationView != null) {
            navigationView.setCheckedItem(R.id.nav_default);

            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        // This method will trigger on item Click of navigation menu
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // Set item in checked state
                            menuItem.setChecked(true);

                            // TODO: handle navigation

                            // Closing drawer on item click
                            mDrawerLayout.closeDrawers();
                            return true;
                        }
                    });
        }
    }

    private void setupFragment(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, new MainFragment()) //replace / remove
                .commit();
    }

    public void goToDetail(View sharedView){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            MainFragment.getInstance().setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform));
            MainFragment.getInstance().setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));

            // Create new fragment to add (Fragment B)
            MainFragment.getInstance().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform));


            ItemDetailFragment.getInstance().setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform));
            MainFragment.getInstance().setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));

            // Create new fragment to add (Fragment B)
            ItemDetailFragment.getInstance().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.change_image_transform));
            ItemDetailFragment.getInstance().setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));

            // Add Fragment B
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, ItemDetailFragment.getInstance())
                    .addToBackStack("Detail")
                    .addSharedElement(sharedView, "image")
                    .commit();
        }else{
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, ItemDetailFragment.getInstance())
                    .addToBackStack("Detail")
                    .commit();
        }

        /*ItemDetailFragment fragment = ItemDetailFragment.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fragment.setSharedElementEnterTransition(new DetailsTransition());
            fragment.setEnterTransition(new Fade());
            MainFragment.getInstance().setExitTransition(new Fade());
            fragment.setSharedElementReturnTransition(new DetailsTransition());
        }

        getSupportFragmentManager()
            .beginTransaction()
            .addSharedElement(sharedView, "image")
            .replace(R.id.fl_container, fragment)
            .addToBackStack(null)
            .commit();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
            case R.id.action_favorite:
                intent = new Intent(this, ButtonsActivity.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
