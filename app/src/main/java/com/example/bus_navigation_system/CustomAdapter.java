package com.example.bus_navigation_system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList bus_no, name, reg_no, contact_no, source, destination;

    CustomAdapter(Activity activity, Context context, ArrayList bus_no,ArrayList source, ArrayList destination,ArrayList name, ArrayList contact_no, ArrayList reg_no){
        this.activity = activity;
        this.context = context;
        this.bus_no = bus_no;
        this.source = source;
        this.destination = destination;
        this.name = name;
        this.contact_no = contact_no;
        this.reg_no = reg_no;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item,parent,false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.bus_no_txt.setText(String.valueOf(bus_no.get(position)));
        holder.source_txt.setText(String.valueOf(source.get(position)));
        holder.dest_txt.setText(String.valueOf(destination.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,UpdateActivity.class);
                intent.putExtra("bus_no",String.valueOf(bus_no.get(position)));
                intent.putExtra("source",String.valueOf(source.get(position)));
                intent.putExtra("destination",String.valueOf(destination.get(position)));
                intent.putExtra("name",String.valueOf(name.get(position)));
                intent.putExtra("contact_no",String.valueOf(contact_no.get(position)));
                intent.putExtra("reg_no",String.valueOf(reg_no.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return bus_no.size();

    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bus_no_txt, name_txt, reg_txt, cont_txt, source_txt, dest_txt;
        LinearLayout mainLayout;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bus_no_txt = itemView.findViewById(R.id.bus_no_view_val);
            source_txt = itemView.findViewById(R.id.source_view_val);
            dest_txt = itemView.findViewById(R.id.dest_view_val);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
