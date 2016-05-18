package com.example.ruby.materiallab.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.ruby.materiallab.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private LinearLayout ll_birthday, ll_current_time;

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
        ll_birthday = (LinearLayout) findViewById(R.id.ll_birthday);
        ll_current_time = (LinearLayout) findViewById(R.id.ll_current_time);

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

        ll_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentDate = Calendar.getInstance();
                int mYear = mCurrentDate.get(Calendar.YEAR);
                int mMonth = mCurrentDate.get(Calendar.MONTH);
                int mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(SettingsActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                        ((TextView)findViewById(R.id.tv_birthday)).setText(selectedDay + "/" + (selectedMonth+1) + "/" + selectedYear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
            }
        });

        ll_current_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentDate = Calendar.getInstance();
                int mHour = mCurrentDate.get(Calendar.HOUR);
                int mMinute = mCurrentDate.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(SettingsActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar mCurrentDate = Calendar.getInstance();
                        mCurrentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        mCurrentDate.set(Calendar.MINUTE, minute);
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                        ((TextView)findViewById(R.id.tv_current_time)).setText(sdf.format(mCurrentDate.getTime()));
                    }
                }, mHour, mMinute, false);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });
    }
}
