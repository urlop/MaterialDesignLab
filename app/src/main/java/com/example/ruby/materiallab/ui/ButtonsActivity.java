package com.example.ruby.materiallab.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.ruby.materiallab.R;

/** Activity with different Material like Buttons. Customized and native.
 * Contains:
 * Customized Buttons
 * Android Native Raised Colored Buttons
 * Android Native Flat Buttons
 * +Enabled, Disable & Selected states
 * Circular Buttons
 */
public class ButtonsActivity extends AppCompatActivity {

    private Button bt_main, bt_colored, bt_borderless;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupView();
    }

    private void setupView(){
        bt_main = (Button) findViewById(R.id.bt_main);
        bt_colored = (Button) findViewById(R.id.bt_colored);
        bt_borderless = (Button) findViewById(R.id.bt_borderless);

        bt_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(0);
            }
        });
        bt_colored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(1);
            }
        });
        bt_borderless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(2);
            }
        });
    }

    private void selectButton(int pos){
        bt_main.setSelected(false);
        bt_colored.setSelected(false);
        bt_borderless.setSelected(false);

        switch (pos){
            case 0:
                bt_main.setSelected(true);
                break;
            case 1:
                bt_colored.setSelected(true);
                break;
            case 2:
                bt_borderless.setSelected(true);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
