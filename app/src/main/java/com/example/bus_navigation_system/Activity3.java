package com.example.bus_navigation_system;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
    private TextView tv;
    private ImageButton next_page_btn,gps_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Intent intent = getIntent();
        String name = intent.getStringExtra("message");
        tv =  findViewById(R.id.heading3);
        tv.setText("Logged In As " + name);
        tv.setSelected(true);

        next_page_btn =  findViewById(R.id.next_page);
        next_page_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Activity3.this, ViewActivity.class);
                startActivity(intent);
            }
        });

        gps_btn = findViewById(R.id.gps_btn);
        gps_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this,GPSTracker.class);
                startActivity(intent);
            }
        });
    }
}