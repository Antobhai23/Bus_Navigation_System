package com.example.bus_navigation_system;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper db;
    ArrayList<String> bus_no, source, destination, name,reg_no,contact_no;
    CustomAdapter customAdapter;
    FloatingActionButton create_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        recyclerView = (RecyclerView) findViewById(R.id.recy_view);
        Toolbar tb = findViewById(R.id.toolbar2);
        tb.setTitle("Time Table");
        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_directions_bus_24));
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        create_btn =  findViewById(R.id.creat_btn_view);
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this,CreateActivity.class);
                startActivity(intent);

            }
        });

        db = new DatabaseHelper(ViewActivity.this);
        bus_no = new ArrayList<>();
        source = new ArrayList<>();
        destination = new ArrayList<>();
        name = new ArrayList<>();
        contact_no = new ArrayList<>();
        reg_no = new ArrayList<>();
        displayData();
        customAdapter = new CustomAdapter(ViewActivity.this,this,bus_no,  source, destination, name, contact_no, reg_no);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ViewActivity.this));
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void displayData(){
        Cursor cursor = db.getAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                bus_no.add(cursor.getString(1));
                source.add(cursor.getString(5));
                destination.add(cursor.getString(6));
                name.add(cursor.getString(2));
                contact_no.add(cursor.getString(3));
                reg_no.add(cursor.getString(4));

            }
        }
    }
}