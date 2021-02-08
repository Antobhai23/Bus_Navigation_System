package com.example.bus_navigation_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView tv,check;
    EditText Emp_Id, Pasd;
    Button val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.heading2);
        tv.setSelected(true);
        Pasd =  findViewById(R.id.pass_word);
        val =  findViewById(R.id.val_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification","My Notification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager =getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        val.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                String name = intent.getStringExtra("message");



                if(Pasd.getText().toString().isEmpty() || (Pasd.getText().toString().equals(" "))){
                    Toast.makeText(MainActivity2.this, "Enter A Valid PassWord!!!", Toast.LENGTH_SHORT).show();
                }

                else if((name.equals("Antony")) && Pasd.getText().toString().equals("100")){

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity2.this,"My Notification");
                    builder.setContentTitle("Message From BNS");
                    builder.setContentText("Welcome "+name+ " to BNS Service");
                    builder.setSmallIcon(R.drawable.ic_baseline_message_24);
                    builder.setAutoCancel(true);
                    NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(MainActivity2.this);

                    Intent intent1 = new Intent(MainActivity2.this,Activity3.class);
                    intent1.putExtra("message",name);
                    notificationManagerCompat.notify(0,builder.build());
                    startActivity(intent1);

                }
                else{
                    Toast.makeText(MainActivity2.this, "Wrong Credentials!!!",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent1);
                }
            }
        });
    }
}