package com.myapp.testingandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myapp.mylibrary.button.CustomButton1;
import com.myapp.mylibrary.views.AutoHideButtonFloat;
import com.myapp.mylibrary.views.ButtonFloat;
import com.myapp.mylibrary.views.ButtonFloatSmall;
import com.myapp.mylibrary.views.CheckBox;
import com.myapp.mylibrary.widgets.ColorSelector;
import com.myapp.mylibrary.widgets.SnackBar;

public class MainActivity extends AppCompatActivity implements ColorSelector.OnColorSelectedListener {

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomButton1 bt = findViewById(R.id.bt1);
        bt.setOnClickListener(v->{
            startActivity(new Intent(this,  MyListView.class));
            /*new CustomDialog1(this, com.myapp.mylibrary.R.color.green, new CustomDialog1.OnAmbilWarnaListener() {
                @Override
                public void onCancel(CustomDialog1 dialog) {
                    Toast.makeText(MainActivity.this, "CustomDialog1 onCancel", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onOk(CustomDialog1 dialog, int color) {
                    Toast.makeText(MainActivity.this, "CustomDialog1 onOk", Toast.LENGTH_SHORT).show();
                }
            }).show();*/
            //new ProgressDialog(this,"progressbar", com.myapp.mylibrary.R.color.green).show();
        });
        ButtonFloat bf = findViewById(R.id.bt3);
        //bf.setText("abc");
        //bf.setBackgroundColor(Color.GREEN);

        bf.setOnClickListener(v->{
           // new Dialog(this,"dialog","this is dialog").show();
            new SnackBar(this, "snackbar", "button", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "CustomDialog1 onOk", Toast.LENGTH_SHORT).show();
                }
            }).show();
        });
        AutoHideButtonFloat abf = findViewById(R.id.bt2);
        abf.setOnClickListener(v->{
            //startActivity(new Intent(this,MainActivity2.class));
            new ColorSelector(
                   this,com.myapp.mylibrary.R.color.green,this).show();
        });
        ButtonFloatSmall bfs = findViewById(R.id.bt4);
        //ImageView imageView = findViewById(R.drawable.ic_launcher_background);
       // bfs.setIcon(getResources().getDrawable(R.drawable.ic_launcher_background));
        bfs.setOnClickListener(v->{
            //new ColorSelector(
            //        this,com.myapp.mylibrary.R.color.green,this).show();
        });
        /*ButtonIcon bti = findViewById(R.id.bt5);
        bti.setOnClickListener(v->{
            //startActivity(new Intent(this, DirectoryChooserActivity.class));
            //startActivity(new Intent(this, MainActivity3.class));
        });
        ButtonRectangle br = findViewById(R.id.bt6);
        br.setOnClickListener(v->{
            //new ProgressDialog(this,"progressbar", com.myapp.mylibrary.R.color.green).show();
        });
        Card c = findViewById(R.id.card);
        c.setOnClickListener(v->{
            *//*new SnackBar(this, "snackbar", "button", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "CustomDialog1 onOk", Toast.LENGTH_SHORT).show();
                }
            }).show();*//*
        });*/
        CheckBox cb = findViewById(R.id.cb1);
        cb.setOnClickListener(v->{
            new SnackBar(this, "snackbar", "button", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "CustomDialog1 onOk", Toast.LENGTH_SHORT).show();
                }
            }).show();
        });
    }

    @Override
    public void onColorSelected(int color) {
        Toast.makeText(this,"this is color selector",Toast.LENGTH_SHORT);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}