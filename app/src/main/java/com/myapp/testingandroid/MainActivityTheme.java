package com.myapp.testingandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivityTheme extends AppCompatActivity {

    TextView textView1,textView2;
    Button btngreen,btnblue,btnpurple,btnorange, btnsave;
    View holderbg, dynamicbg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitytheme);

        btnsave = findViewById(R.id.btnsave);

        textView1 = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        btngreen = findViewById(R.id.btngreen);
        btnblue = findViewById(R.id.btnblue);
        btnpurple = findViewById(R.id.btnpurple);
        btnorange = findViewById(R.id.btnorange);

        holderbg = findViewById(R.id.holderbg);
        dynamicbg = findViewById(R.id.dynamicbg);

        holderbg.setBackgroundResource(R.drawable.bggreen);
        holderbg.setScaleX(3);
        holderbg.setScaleY(3);

        btngreen.setScaleY(1.5f);
        btngreen.setScaleX(1.5f);

        btnblue.setOnClickListener(v->{

            btnblue.animate().translationY(20).scaleX(1.5f)
                    .scaleY(1.5f).setDuration(800).start();

            btngreen.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btnpurple.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btnorange.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();

            dynamicbg.setBackgroundResource(R.drawable.bgblue);
            dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

            btnsave.setTextColor(Color.parseColor("#3498db"));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holderbg.setScaleX(3);
                    holderbg.setScaleY(3);
                    holderbg.setBackgroundResource(R.drawable.bgblue);
                    dynamicbg.setScaleX(0);
                    dynamicbg.setScaleY(0);
                }
            },850);
        });

        btngreen.setOnClickListener(v->{

            btngreen.animate().scaleX(1.5f)
                    .scaleY(1.5f).setDuration(800).start();

            btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btnpurple.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btnorange.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();

            dynamicbg.setBackgroundResource(R.drawable.bggreen);
            dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

            btnsave.setTextColor(Color.parseColor("#1bac9c"));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holderbg.setScaleX(3);
                    holderbg.setScaleY(3);
                    holderbg.setBackgroundResource(R.drawable.bggreen);
                    dynamicbg.setScaleX(0);
                    dynamicbg.setScaleY(0);
                }
            },850);
        });

        btnorange.setOnClickListener(v->{

            btnorange.animate().scaleX(1.5f)
                    .scaleY(1.5f).setDuration(800).start();

            btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btnpurple.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btngreen.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();

            dynamicbg.setBackgroundResource(R.drawable.bggorange);
            dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

            btnsave.setTextColor(Color.parseColor("#1bac9c"));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holderbg.setScaleX(3);
                    holderbg.setScaleY(3);
                    holderbg.setBackgroundResource(R.drawable.bggorange);
                    dynamicbg.setScaleX(0);
                    dynamicbg.setScaleY(0);
                }
            },850);
        });

        btnpurple.setOnClickListener(v->{

            btnpurple.animate().scaleX(1.5f)
                    .scaleY(1.5f).setDuration(800).start();

            btnblue.animate().translationY(0).scaleX(1).scaleY(1).setDuration(350).start();
            btngreen.animate().scaleX(1).scaleY(1).setDuration(350).start();
            btnorange.animate().scaleX(1).scaleY(1).setDuration(350).start();

            dynamicbg.setBackgroundResource(R.drawable.bgpurple);
            dynamicbg.animate().scaleX(3).scaleY(3).setDuration(800).start();

            btnsave.setTextColor(Color.parseColor("#1bac9c"));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    holderbg.setScaleX(3);
                    holderbg.setScaleY(3);
                    holderbg.setBackgroundResource(R.drawable.bgpurple);
                    dynamicbg.setScaleX(0);
                    dynamicbg.setScaleY(0);
                }
            },850);
        });

    }
}