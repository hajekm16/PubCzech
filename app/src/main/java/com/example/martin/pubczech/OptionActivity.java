package com.example.martin.pubczech;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martin.pubczech.R;

public class OptionActivity extends AppCompatActivity {

    int limit;
    int items;
    String item1N;
    String item2N;
    String item3N;
    String item4N;
    String item5N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        limit =300;
        items =2;
        item1N = "Beer";
        item2N = "Shot";
        item3N = "Wine";
        item4N = "Food";
        item5N = "Other";

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText EditLimit = (EditText) findViewById(R.id.EditLimit);
                try {
                    limit = Integer.parseInt(EditLimit.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Enter valid value", Toast.LENGTH_LONG).show();
                }
                final EditText EditItem1 = (EditText) findViewById(R.id.EditItem1);
                item1N = EditItem1.getText().toString();
                final EditText EditItem2 = (EditText) findViewById(R.id.EditItem2);
                item2N = EditItem2.getText().toString();
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        updateUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    protected void loadData() {
        SharedPreferences sharedPref = getSharedPreferences("Data", Context.MODE_PRIVATE);
        item1N = sharedPref.getString(getString(R.string.saved_item1N), item1N);
        item2N = sharedPref.getString(getString(R.string.saved_item2N), item2N);
        item3N = sharedPref.getString(getString(R.string.saved_item3N), item3N);
        item4N = sharedPref.getString(getString(R.string.saved_item4N), item4N);
        item5N = sharedPref.getString(getString(R.string.saved_item5N), item5N);
        limit = sharedPref.getInt(getString(R.string.saved_limit), limit);
        items = sharedPref.getInt(getString(R.string.saved_items), items);
    }

    protected void saveData() {
        SharedPreferences sharedPref = getSharedPreferences("Data",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.saved_item1N), item1N);
        editor.putString(getString(R.string.saved_item2N), item2N);
        editor.putString(getString(R.string.saved_item3N), item3N);
        editor.putString(getString(R.string.saved_item4N), item4N);
        editor.putString(getString(R.string.saved_item5N), item5N);
        editor.putInt(getString(R.string.saved_limit), limit);
        editor.putInt(getString(R.string.saved_items), items);
        editor.commit();
    }

    protected void updateUI() {
        final EditText EditLimit = (EditText) findViewById(R.id.EditLimit);
        EditLimit.setText(String.valueOf(limit));
        final EditText EditItem1 = (EditText) findViewById(R.id.EditItem1);
        EditItem1.setText(String.valueOf(item1N));
        final EditText EditItem2 = (EditText) findViewById(R.id.EditItem2);
        EditItem2.setText(String.valueOf(item2N));
    }

}
