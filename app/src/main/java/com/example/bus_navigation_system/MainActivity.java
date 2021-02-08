package com.example.bus_navigation_system;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView tv;
    public TextView Emp_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv =  findViewById(R.id.heading);
        tv.setSelected(true);

        Emp_Id = (EditText) findViewById(R.id.emp_id);

        button =  findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }
    public void validate(){
        if(Emp_Id.getText().toString().isEmpty() || (Emp_Id.getText().toString().equals(" "))){
            Toast.makeText(MainActivity.this,"Enter A Valid Name",Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            String name = Emp_Id.getText().toString();
            intent.putExtra("message", name);
            startActivity(intent);
        }

    }

}
