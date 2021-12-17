package com.example.tdmd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private String[] names;
    private String[] details;
    private int[] level;
    private String[] images;
    private Context context;

    public RecyclerAdapter(Context context, String[] name, String[] detail, int[] lvl, String[] img) {
        this.context = context;
        this.names = name;
        this.details = detail;
        this.level = lvl;
        this.images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_pokemon, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(names[position]);
        holder.myText2.setText(details[position]);
        //holder.img.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.pokemon_name);
            myText2 = itemView.findViewById(R.id.pokemon_detail);
            //img = itemView.findViewById(R.id.pokemon_image);
        }
    }
}
