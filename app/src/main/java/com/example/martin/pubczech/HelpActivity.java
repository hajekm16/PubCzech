package com.example.martin.pubczech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class HelpActivity extends AppCompatActivity {

    int help_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        help_image = 0;
    }
    public void changeHelp(View view){
        final FrameLayout obraz = (FrameLayout) findViewById(R.id.obrazek);
        switch (help_image){
            case 0:
                obraz.setBackgroundResource(R.drawable.picker_help);
                help_image++;
                break;
            case 1:
                obraz.setBackgroundResource(R.drawable.clear_help);
                help_image++;
                break;
            case 2:
                obraz.setBackgroundResource(R.drawable.limit_help);
                help_image++;
                break;
            case 3:
                obraz.setBackgroundResource(R.drawable.set_help);
                help_image++;
                break;
            default:
                help_image = 0;
                finish();
                break;
        }
    }
}
