package com.example.tdmd.Adapters;

import static com.example.tdmd.UIHandlers.TypeColorHandler.typeColor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.FragmentHandler;
import com.example.tdmd.Fragments.FragmentCollection;
import com.example.tdmd.Fragments.FragmentInfo;
import com.example.tdmd.R;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<Pokemon> pokemons;
    private View view;

    public RecyclerAdapter(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_card , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameText.setText(pokemons.get(position).getName());

        holder.typeText1.setText("" + pokemons.get(position).getTypes().get(0));

        if(pokemons.get(position).getTypes().size() > 1) {
            holder.typeText2.setText("" + pokemons.get(position).getTypes().get(1));
        } else {
            holder.typeText2.setVisibility(View.INVISIBLE);
        }

        holder.cardImageView.setColorFilter(typeColor(pokemons.get(position).getTypes().get(0)), PorterDuff.Mode.MULTIPLY);
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(pokemons.get(position).getImageurl()).into(holder.pokemonImage);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) v.getContext();

                FragmentManager ft = ((FragmentActivity)activity).getSupportFragmentManager();

                FragmentInfo fragmentInfo = FragmentInfo.newInstance(pokemons.get(position));

                FragmentHandler.ReplaceFragment(ft, fragmentInfo , R.id.fragment_container);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameText;
        TextView typeText1;
        TextView typeText2;
        ImageView pokemonImage;
        ShapeableImageView cardImageView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.tvRC_NamePokemon);
            typeText1 = itemView.findViewById(R.id.tvRCTypePokemon);
            typeText2 = itemView.findViewById(R.id.tvRCTypePokemon1);
            cardImageView = itemView.findViewById(R.id.shapeableImageView);
            pokemonImage =  itemView.findViewById(R.id.ivRCPokemon);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

}
