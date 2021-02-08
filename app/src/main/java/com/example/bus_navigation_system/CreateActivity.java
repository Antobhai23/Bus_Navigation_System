package com.example.bus_navigation_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText t1, t2, t3, t4, t5, t6;
    Button create_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Enter Details");
        setSupportActionBar(tb);

        tb.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });

        mydb = new DatabaseHelper(this);
        t1 = findViewById(R.id.busno_val);
        t2 = findViewById(R.id.name_cre);
        t3 = findViewById(R.id.conc_crea);
        t5 = findViewById(R.id.source_val);
        t6 = findViewById(R.id.dest_val);
        t4 = findViewById(R.id.reg_cre);
        create_btn = findViewById(R.id.create_btn);
        AddData();
    }
    public void AddData() {
        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = mydb.insertData(t1.getText().toString(),
                          t2.getText().toString(),
                          t3.getText().toString(),
                         t4.getText().toString(),
                        t5.getText().toString(),
                        t6.getText().toString());
                if (isInserted == true){
                    Toast.makeText(CreateActivity.this, "Inserted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateActivity.this,ViewActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CreateActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
                    t1.setText(" "); t2.setText(" "); t3.setText(" "); t4.setText(" "); t5.setText(" "); t6.setText(" ");
                }
            }
        });
    }
}
