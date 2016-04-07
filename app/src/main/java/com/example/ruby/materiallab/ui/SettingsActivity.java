package com.example.ruby.materiallab.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.ruby.materiallab.R;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Shows basic look in case of settings. And default animation for general components.
 * Contains:
 * EditText, Spinner, Switch, Checkbox & Clickable Container.
 */
public class SettingsActivity extends AppCompatActivity {

    private TextInputLayout til_username;
    private MaterialSpinner sp_gender;
    private Button bt_errors;
    private LinearLayout ll_see_buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupView();
    }

    private void setupView(){
        til_username = (TextInputLayout) findViewById(R.id.til_username);
        sp_gender = (MaterialSpinner) findViewById(R.id.sp_gender);
        bt_errors = (Button) findViewById(R.id.bt_errors);
        ll_see_buttons = (LinearLayout) findViewById(R.id.ll_see_buttons);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.st_genders));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(adapter);

        bt_errors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String error = getString(R.string.st_error_field_required);
                til_username.setError(error);
                sp_gender.setError(error);
            }
        });

        ll_see_buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ButtonsActivity.class);
                startActivity(intent);
            }
        });
    }
}
