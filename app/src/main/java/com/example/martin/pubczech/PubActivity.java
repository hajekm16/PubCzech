package com.example.martin.pubczech;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class PubActivity extends AppCompatActivity {

    int item1P;
    int item1A;
    String item1N;
    int item2P;
    int item2A;
    String item2N;
    int item3P;
    int item3A;
    String item3N;
    int item4P;
    int item4A;
    String item4N;
    int item5P;
    int item5A;
    String item5N;
    int total;
    int limit;
    int items;
    boolean isLimit;
    int firsttime;

    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.FullAd));

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });

        requestNewInterstitial();

        item1A = 0;
        item2A = 0;
        item3A = 0;
        item4A = 0;
        item5A = 0;
        total=0;
        item1P =30;
        item2P =40;
        item3P =40;
        item4P =100;
        item5P =50;
        limit =300;
        items =2;
        isLimit = true;
        firsttime = 0;
        item1N = "Beer";
        item2N = "Shot";
        item3N = "Wine";
        item4N = "Food";
        item5N = "Other";

        final LinearLayout contItem1 = (LinearLayout) findViewById(R.id.contItem1);
        final LinearLayout contItem2 = (LinearLayout) findViewById(R.id.contItem2);

        final TextView celkem = (TextView) findViewById(R.id.total);
        final ScrollView backBeer = (ScrollView) findViewById(R.id.backBeer);
        final EditText EditLimit = (EditText) findViewById(R.id.EditLimit);
        EditLimit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    if (isLimit == false) {
                        setLimit();
                        return true;
                    }
                }
                return false;
            }
        });
        final NumberPicker cenaItem1 = (NumberPicker) findViewById(R.id.cenaItem1);
        cenaItem1.setMinValue(0);
        cenaItem1.setMaxValue(500);
        cenaItem1.setValue(item1P);
        cenaItem1.setSelected(false);

        final NumberPicker cenaItem2 = (NumberPicker) findViewById(R.id.cenaItem2);
        cenaItem2.setMinValue(0);
        cenaItem2.setMaxValue(500);
        cenaItem2.setValue(item2P);
        cenaItem2.setSelected(false);

        final Button buttonItem1 = (Button) findViewById(R.id.buttonItem1);
        celkem.setText(getString(R.string.total) + ": " + total);
        buttonItem1.setText(item1N + ": " + item1A);
        buttonItem1.requestFocus();
        buttonItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item1P = cenaItem1.getValue();
                if ((limit > 0) && (limit >= (total + item1P))) {
                    item1A++;
                    total = total + item1P;
                    celkem.setText(getString(R.string.total) + ": " + total);
                    buttonItem1.setText(item1N + ": " + item1A);
                    if (limit < (total + item1P)) {
                        Toast.makeText(getApplicationContext(), "This is your final " + item1N + "!", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Over your limit ! ! !\n Go home!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonItem1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                item1P = cenaItem1.getValue();
                if ((0 <= (total - item1P)) && (item1A > 0)) {
                    item1A--;
                    total = total - item1P;
                    celkem.setText(getString(R.string.total) + ": " + total);
                    buttonItem1.setText(item1N + ": " + item1A);
                } else
                    Toast.makeText(getApplicationContext(), "Can't remove this " + item1N + "!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        final Button buttonItem2 = (Button) findViewById(R.id.buttonItem2);
        buttonItem2.setText(item2N + ": " + item2A);
        buttonItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item2P = cenaItem2.getValue();
                if ((limit > 0) && (limit >= (total + item2P))) {
                    item2A++;
                    total = total + item2P;
                    buttonItem2.setText(item2N + ": " + item2A);
                    celkem.setText(getString(R.string.total) + ": " + total);
                    if (limit < (total + item2P)) {
                        Toast.makeText(getApplicationContext(), "This is your last "+item2N+"!", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "Over your limit ! ! !\n Go home!", Toast.LENGTH_SHORT).show();
            }
        });

        buttonItem2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                item2P = cenaItem2.getValue();
                if ((0 <= (total - item2P)) && (item2A > 0)) {
                    item2A--;
                    total = total - item2P;
                    celkem.setText(getString(R.string.total) + ": " + total);
                    buttonItem2.setText(item2N + ": " + item2A);
                } else
                    Toast.makeText(getApplicationContext(), "Can't remove this "+item2N+"!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                newSession();
                clearPub();
            }
        });
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("SEE_YOUR_LOGCAT_TO_GET_YOUR_DEVICE_ID")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        updateUI();
        if (firsttime == 0){
            showHelp();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    protected void loadData() {
        SharedPreferences sharedPref = getSharedPreferences("Data", Context.MODE_PRIVATE);
        item1A = sharedPref.getInt(getString(R.string.saved_item1A), item1A);
        item2A = sharedPref.getInt(getString(R.string.saved_item2A), item2A);
        item3A = sharedPref.getInt(getString(R.string.saved_item3A), item3A);
        item4A = sharedPref.getInt(getString(R.string.saved_item4A), item4A);
        item5A = sharedPref.getInt(getString(R.string.saved_item5A), item5A);
        item1P = sharedPref.getInt(getString(R.string.saved_item1P), item1P);
        item2P = sharedPref.getInt(getString(R.string.saved_item2P), item2P);
        item3P = sharedPref.getInt(getString(R.string.saved_item3P), item2P);
        item4P = sharedPref.getInt(getString(R.string.saved_item4P), item2P);
        item5P = sharedPref.getInt(getString(R.string.saved_item5P), item2P);
        item1N = sharedPref.getString(getString(R.string.saved_item1N), item1N);
        item2N = sharedPref.getString(getString(R.string.saved_item2N), item2N);
        item3N = sharedPref.getString(getString(R.string.saved_item3N), item3N);
        item4N = sharedPref.getString(getString(R.string.saved_item4N), item4N);
        item5N = sharedPref.getString(getString(R.string.saved_item5N), item5N);
        total = sharedPref.getInt(getString(R.string.saved_total), total);
        limit = sharedPref.getInt(getString(R.string.saved_limit), limit);
        firsttime = sharedPref.getInt(getString(R.string.saved_first), firsttime);
        items = sharedPref.getInt(getString(R.string.saved_items), items);
    }

    protected void saveData() {
        SharedPreferences sharedPref = getSharedPreferences("Data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.saved_item1A), item1A);
        editor.putInt(getString(R.string.saved_item2A), item2A);
        editor.putInt(getString(R.string.saved_item3A), item3A);
        editor.putInt(getString(R.string.saved_item4A), item4A);
        editor.putInt(getString(R.string.saved_item5A), item5A);
        editor.putInt(getString(R.string.saved_item1P), item1P);
        editor.putInt(getString(R.string.saved_item2P), item2P);
        editor.putInt(getString(R.string.saved_item3P), item3P);
        editor.putInt(getString(R.string.saved_item4P), item4P);
        editor.putInt(getString(R.string.saved_item5P), item5P);
        editor.putString(getString(R.string.saved_item1N), item1N);
        editor.putString(getString(R.string.saved_item2N), item2N);
        editor.putString(getString(R.string.saved_item3N), item3N);
        editor.putString(getString(R.string.saved_item4N), item4N);
        editor.putString(getString(R.string.saved_item5N), item5N);
        editor.putInt(getString(R.string.saved_total), total);
        editor.putInt(getString(R.string.saved_limit), limit);
        editor.putInt(getString(R.string.saved_first), firsttime);
        editor.putInt(getString(R.string.saved_items), items);
        editor.commit();
    }

    protected void newSession() {
        item1A = 0;
        item2A = 0;
        item3A = 0;
        item4A = 0;
        item5A = 0;
        total = 0;
    }

    protected void updateUI() {
        final NumberPicker cenaItem1 = (NumberPicker) findViewById(R.id.cenaItem1);
        cenaItem1.setValue(item1P);
        final NumberPicker cenaItem2 = (NumberPicker) findViewById(R.id.cenaItem2);
        cenaItem2.setValue(item2P);
        final Button buttonItem1 = (Button) findViewById(R.id.buttonItem1);
        buttonItem1.setText(item1N + ": " + item1A);
        final Button buttonItem2 = (Button) findViewById(R.id.buttonItem2);
        buttonItem2.setText(item2N+": "+ item2A);
        final TextView celkem = (TextView) findViewById(R.id.total);
        celkem.setText(getString(R.string.total) + ": " + total);
        final EditText limitset = (EditText) findViewById(R.id.EditLimit);
        limitset.setText(String.valueOf(limit));
    }

    public void setLimit(){
        final EditText EditLimit = (EditText) findViewById(R.id.EditLimit);
        try {
            limit = Integer.parseInt(EditLimit.getText().toString());
            //EditLimit.setVisibility(View.INVISIBLE);
            if (limit >= total){
                isLimit = true;
                EditLimit.setEnabled(false);
                EditLimit.setTextColor(Color.WHITE);
                Toast.makeText(getApplicationContext(), "The limit is set", Toast.LENGTH_LONG).show();
                updateUI();
            }
            else{
                Toast.makeText(getApplicationContext(), "Sorry... Already over this limit", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Enter valid value", Toast.LENGTH_LONG).show();
        }
    }
    public void getLimit(){
        final EditText EditLimit = (EditText) findViewById(R.id.EditLimit);
        Toast.makeText(getApplicationContext(), "Enter your limit", Toast.LENGTH_LONG).show();
        //EditLimit.setVisibility(View.VISIBLE);
        EditLimit.setTextColor(Color.RED);
        EditLimit.setEnabled(true);
        if (EditLimit.requestFocus()){
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        isLimit = false;
    }
    public void changeLimit(View view){
        if (isLimit)
            getLimit();
        else
            setLimit();
    }
    public void clearPub(){
        updateUI();
    }
    public void showHelp(){
        firsttime = 1;
        Intent intent = new Intent(this, HelpActivity.class);
        startActivity(intent);
    }
    public void openOptions(View view){
        Intent intent = new Intent(this, OptionActivity.class);
        startActivity(intent);
    }
}
