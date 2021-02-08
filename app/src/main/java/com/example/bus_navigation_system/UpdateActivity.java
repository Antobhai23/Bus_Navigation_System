package com.example.bus_navigation_system;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class UpdateActivity extends AppCompatActivity {

    EditText bus_n, name, reg_no, contact_no, src, dest;
    Button up_btn, delete_btn;
    String bus_no, name_v, r_n, c_no, source, destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Toolbar tb = findViewById(R.id.toolbar3);
        bus_n = findViewById(R.id.busno_val_update);
        name = findViewById(R.id.name_val_update);
        contact_no = findViewById(R.id.cont_val_update);
        reg_no = findViewById(R.id.reg_val_update);
        src = findViewById(R.id.source_up);
        dest = findViewById(R.id.dest_up);

        up_btn = findViewById(R.id.update_btn);
        delete_btn = findViewById(R.id.del_btn);
        getAndSetIntentData();
        tb.setTitle("Details of Bus No: " + bus_no);
        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateActivity.this, ViewActivity.class);
                startActivity(intent);
            }
        });
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                bus_no = bus_n.getText().toString();
                source = src.getText().toString();
                destination = dest.getText().toString();
                name_v = name.getText().toString();
                c_no = contact_no.getText().toString();
                r_n = reg_no.getText().toString();
                boolean isUpdate = db.updateData(bus_no, source, destination, name_v, c_no, r_n);
                if (isUpdate == true) {
                    Toast.makeText(UpdateActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateActivity.this, ViewActivity.class);
                    startActivity(intent);
                } else
                    Toast.makeText(UpdateActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
            }
        });
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("bus_no") && getIntent().hasExtra("source") && getIntent().hasExtra("destination")
        && getIntent().hasExtra("name") && getIntent().hasExtra("contact_no") && getIntent().hasExtra("reg_no")){
            bus_no = getIntent().getStringExtra("bus_no");
            source = getIntent().getStringExtra("source");
            destination = getIntent().getStringExtra("destination");
            name_v = getIntent().getStringExtra("name");
            c_no = getIntent().getStringExtra("contact_no");
            r_n = getIntent().getStringExtra("reg_no");

            bus_n.setText(bus_no);
            src.setText(source);
            dest.setText(destination);
            name.setText(name_v);
            contact_no.setText(c_no);
            reg_no.setText(r_n);
        }
        else{
            Toast.makeText(this, "No Data!!!", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Bus No: "+ bus_no +" ?");
        builder.setMessage("Are you sure you want to Delete Bus No: " +bus_no+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                db.deleteOneRow(bus_no);
                Toast.makeText(UpdateActivity.this,"Deleted Successfully!!!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}