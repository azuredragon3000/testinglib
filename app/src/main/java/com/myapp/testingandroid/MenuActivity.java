package com.myapp.testingandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ArrayList<String> arrayList = new ArrayList<>();
        for(int i=0;i<1000;i++){
            arrayList.add("this is item #"+i);
        }

        recyclerView = findViewById(R.id.rc);
        MenuAdapter myAdapter = new MenuAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);



        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(v->{
            showPopup(tv);
        });
    }

    private void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu_example);
        popup.show();
        ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_example,menu);
        return true;
    }

    @SuppressLint({"NonConstantResourceId", "ShowToast"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_game:
                Toast.makeText(this,"new game",Toast.LENGTH_LONG).show();
                return true;
            case R.id.help:
                Toast.makeText(this,"help",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.new_game:
                Toast.makeText(this,"new game",Toast.LENGTH_LONG).show();
               // archive(item);
                return true;
            case R.id.help:
                Toast.makeText(this,"help",Toast.LENGTH_LONG).show();
               // delete(item);
                return true;
            default:
                return false;
        }
    }
}