package com.example.tdmd.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tdmd.Contracts.Type;
import com.example.tdmd.R;
import com.google.android.material.imageview.ShapeableImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private String[] names;
    private Type[] types;
    private String[] images;
    private Context context;


    public RecyclerAdapter(String[] list, Type[] types) {
        this.names = list;
        this.types = types;
    }

    public RecyclerAdapter(Context context, String[] name, Type[] types, int[] lvl, String[] img) {
        this.context = context;
        this.names = name;
        this.types = types;
        this.images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(names[position]);
        holder.myText2.setText("" + types[position]);

        holder.cardImageView.setColorFilter(typeColor(types[position]), PorterDuff.Mode.MULTIPLY);
        //ImageViewCompat.setImageTintList(holder.cardImageView, ColorStateList.valueOf()));
        //holder.cardImageView.setBackgroundColor(typeColor(types[position]));
        //holder.img.setImageResource(images[position]);
    }



    public int typeColor(Type type) {
        switch (type) {
            case Normal:
                return Color.parseColor("#A3A37B");
            case Fighting:
                return Color.parseColor("#c03028");
            case Flying:
                return Color.parseColor("#a890f0");
            case Poison:
                return Color.parseColor("#a040a0");
            case Ground:
                return Color.parseColor("#C5AE72");
            case Rock:
                return Color.parseColor("#b8a038");
            case Bug:
                return Color.parseColor("#a8b820");
            case Ghost:
                return Color.parseColor("#705898");
            case Steel:
                return Color.parseColor("#8A8A9C");
            case Fire:
                return Color.parseColor("#f08030");
            case Water:
                return Color.parseColor("#6890f0");
            case Grass:
                return Color.parseColor("#78c850");
            case Electric:
                return Color.parseColor("#FFFC55");
            case Psychic:
                return Color.parseColor("#f85888");
            case Ice:
                return Color.parseColor("#98d8d8");
            case Dragon:
                return Color.parseColor("#7038f8");
            case Dark:
                return Color.parseColor("#705848");
            case Fairy:
                return Color.parseColor("#ee99ac");
            default:
                return Color.WHITE;
        }
    }

    @Override
    public int getItemCount() {
        return names.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;
        ImageView img;
        ShapeableImageView cardImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.tvRC_NamePokemon);
            myText2 = itemView.findViewById(R.id.tvRCTypePokemon);
            cardImageView = itemView.findViewById(R.id.shapeableImageView);

            if(cardImageView != null) {
                Log.d("Kaas", "Testing");
            }

            //img = itemView.findViewById(R.id.pokemon_image);
        }
    }

}
