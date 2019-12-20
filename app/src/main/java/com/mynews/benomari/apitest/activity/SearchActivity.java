package com.mynews.benomari.apitest.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.mynews.benomari.apitest.R;
import com.mynews.benomari.apitest.fragment.MainFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.include5) Toolbar toolbar;

    @BindView(R.id.edit_query) EditText editQuery;

    @BindView(R.id.search_begin_date) EditText mBeginDate;

    @BindView(R.id.search_end_date) EditText mEndDate;

    @BindView(R.id.ArtsCheckBox) CheckBox ArtsCheckBox;

    @BindView(R.id.BusinessCheckBox) CheckBox BusinessCheckBox;

    @BindView(R.id.EntrepreneursCheckBox) CheckBox EntrepreneursCheckBox;

    @BindView(R.id.PoliticsCheckBox) CheckBox PoliticsCheckBox;

    @BindView(R.id.SportsCheckBox) CheckBox SportsCheckBox;

    @BindView(R.id.TravelCheckBox) CheckBox TravelCheckBox;

    @BindView(R.id.buttonSearch) Button buttonSearch;

    @BindView(R.id.checkbox_container) ConstraintLayout checkboxContainer;

    public static String QUERY_TERM = "QUERY_TERM";
    public static String CHECKBOXES = "CHECKBOXES";
    public static String BEGIN_DATE = "BEGIN_DATE";
    public static String END_DATE = "END_DATE";
    String beginDate = null;
    String endDate = null;
    SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        isChecked();
        setButtonSearch();
        this.configureToolbar();
        }





    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.include5);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle("Search Articles");
        }

    }
    //Verify if a checkbox is checked and add it to a list if it is
    private List<String> isChecked() {
        List<String> checkedBoxes = new ArrayList<>();
        for (int i = 0; i < checkboxContainer.getChildCount(); i++){
            View v = checkboxContainer.getChildAt(i);
            if (v instanceof CheckBox){
                CheckBox cb = (CheckBox)v;
                // isChecked should be a boolean indicating if every Checkbox should be checked or not
                if ( cb.isChecked()){
                    checkedBoxes.add((String) cb.getText());
                }
            }
        }
        return checkedBoxes;
        }

    //Open SearchResult Activity with the query terms and the category choosed by the user.
    private void openSearchResultActivity(){
        Intent intent = new Intent(getBaseContext(), SearchResultActivity.class);
        intent.putExtra("SEARCH",true);
        intent.putExtra(QUERY_TERM,editQuery.getText().toString());
        intent.putExtra(CHECKBOXES,(ArrayList<String>)isChecked());
        intent.putExtra(BEGIN_DATE,beginDate);
        intent.putExtra(END_DATE,endDate);
        startActivity(intent);
    }
    //Show results if there is at least one category
    private void setButtonSearch(){
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isChecked().isEmpty()){
                    openSearchResultActivity();
                }else{
                    Toast.makeText(getApplicationContext(),"You must choose at least one category",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void getDate(View v) {
        @SuppressLint("SimpleDateFormat") final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (v == mBeginDate){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mBeginDate.setText(sdf.format(new Date(year - 1900,monthOfYear,dayOfMonth)));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        else if (v == mEndDate){
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            mEndDate.setText(sdf.format(new Date(year - 1900,monthOfYear,dayOfMonth)));

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        try {
            Date d1 = new SimpleDateFormat("dd/MM/yyyy").parse(mBeginDate.getText().toString());
            Date d2 = new SimpleDateFormat("dd/MM/yyyy").parse(mEndDate.getText().toString());
            beginDate = mSimpleDateFormat.format(d1);
            endDate = mSimpleDateFormat.format(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
