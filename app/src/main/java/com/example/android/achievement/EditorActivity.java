package com.example.android.achievement;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditorActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DETAILS = "details";
    public static final String EXTRA_TYPE = "type";
    public static final String EXTRA_DATE = "date";

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private EditText title_et, details_et, date_et;
    private RadioGroup radioGroup_type;
    private RadioButton radioButton_sec;
    private int radioId;
    private Button add_btn;
    private boolean achievementHasChanged = false;
    //OnTouchListener object that listens for any user touches on a View
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            achievementHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        title_et = findViewById(R.id.title_et);
        title_et.setOnTouchListener(mTouchListener);
        details_et = findViewById(R.id.details_et);
        details_et.setOnTouchListener(mTouchListener);
        date_et = findViewById(R.id.date_et);
        date_et.setOnClickListener(this);
        date_et.setOnTouchListener(mTouchListener);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        radioGroup_type = findViewById(R.id.radioGroup);
        radioGroup_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                achievementHasChanged = true;
            }
        });

        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String date=day+"/"+(month+1)+"/"+year;
                date_et.setText(date);
            }
        };

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            add_btn.setText(getString(R.string.edit_achievement));
            title_et.setText(intent.getStringExtra(EXTRA_TITLE));
            details_et.setText(intent.getStringExtra(EXTRA_DETAILS));
            date_et.setText(intent.getStringExtra(EXTRA_DATE));
            String type = intent.getStringExtra(EXTRA_TYPE);
            if (type.equals(getString(R.string.rb_sec1))) {
                radioGroup_type.check(R.id.sec1_rb);
            } else if (type.equals(getString(R.string.rb_sec2))) {
                radioGroup_type.check(R.id.sec2_rb);
            } else if (type.equals(getString(R.string.rb_sec3))) {
                radioGroup_type.check(R.id.sec3_rb);
            } else {
                radioGroup_type.check(R.id.sec4_rb);
            }

        } else {
            add_btn.setText(getString(R.string.add_achievement));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == add_btn) {
            saveAchievement();
        }
        if(view==date_et){
            showDatePicker();
        }
    }


    private void showDatePicker() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                mDateSetListener,
                year,month,day);
        datePickerDialog.show();
    }

    private void saveAchievement() {
        radioId = radioGroup_type.getCheckedRadioButtonId();
        radioButton_sec = findViewById(radioId);
        String type = (String) radioButton_sec.getText();
        String date = date_et.getText().toString().trim();
        String details = details_et.getText().toString().trim();
        String title = title_et.getText().toString().trim();
        if (TextUtils.isEmpty(date)) {
            date_et.setError(getString(R.string.date_empty));
        }
        if (TextUtils.isEmpty(details)) {
            details_et.setError(getString(R.string.details_empty));
        }
        if (TextUtils.isEmpty(title)) {
            title_et.setError(getString(R.string.title_empty));
        }

        if (TextUtils.isEmpty(date) | TextUtils.isEmpty(details) | TextUtils.isEmpty(title)) {
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_TITLE, title);
        intent.putExtra(EXTRA_DETAILS, details);
        intent.putExtra(EXTRA_DATE, date);
        intent.putExtra(EXTRA_TYPE, type);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            intent.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                checkUnsavedBeforeGoingToParent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private boolean checkUnsavedBeforeGoingToParent() {
        if (!achievementHasChanged) {
            finish();
            return true;
        } else {
            showUnSavedChangesDialog();
            return true;
        }
    }

    private void showUnSavedChangesDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes);
        builder.setPositiveButton(R.string.discard, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }
}
