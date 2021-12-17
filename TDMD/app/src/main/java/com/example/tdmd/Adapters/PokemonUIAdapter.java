package com.example.tdmd.Adapters;

import android.app.Activity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.R;
import com.example.tdmd.UIHandlers.ProgressBarHandler;

import org.w3c.dom.Text;

public class PokemonUIAdapter {
    Activity activity;

    public PokemonUIAdapter(Activity activity) {
        this.activity = activity;
    }

    public void LoadPokemoninUI(Pokemon pokemon) {

        TextView tvInfoName = activity.findViewById(R.id.tvInfoNamePokemon);
        tvInfoName.setText(pokemon.getName());

        TextView tvID = activity.findViewById(R.id.tvIDPokemon);
        tvID.setText("#" + pokemon.getID());

        TextView tvType = activity.findViewById(R.id.tvInfoTypePokemon);
        tvType.setText(pokemon.getTypes().get(0).name());

        if(pokemon.getTypes().size() > 1) {
            TextView tvType1 = activity.findViewById(R.id.tvInfoTypePokemon1);
            tvType1.setText(pokemon.getTypes().get(1).name());
        }

        LoadAboutPage(pokemon);
    }

    public void LoadAboutPage(Pokemon pokemon) {
        TextView tvHeight = activity.findViewById(R.id.tvTextHeight);
        tvHeight.setText(pokemon.getHeight() + "cm");

        TextView tvWeight = activity.findViewById(R.id.tvTextWeight);
        tvWeight.setText(pokemon.getWeight() + "kg");

        TextView tvAbilities = activity.findViewById(R.id.tvTextAbilities);
        String result = pokemon.getAbilities().get(0);

        for (int i = 1; i < pokemon.getAbilities().size()-1; i++) {
            result += ", " + pokemon.getAbilities().get(i);
        }

        tvAbilities.setText(result);
    }

    public void LoadStats(Pokemon pokemon) {
        ProgressBarHandler progressBarHandler = new ProgressBarHandler(activity);

        TextView tvTextHP = activity.findViewById(R.id.tvTextHP);
        ProgressBar pbHP = activity.findViewById(R.id.pbHP);
        int hp = pokemon.getPokemonStats().getHp();
        tvTextHP.setText(hp);
        progressBarHandler.SetProgressbarValue(hp, pbHP);

        TextView tvTextAttack = activity.findViewById(R.id.tvTextAttack);
        ProgressBar pbAttack = activity.findViewById(R.id.pbAttack);
        int attack = pokemon.getPokemonStats().getAttack();
        tvTextAttack.setText(attack);
        progressBarHandler.SetProgressbarValue(attack, pbAttack);

        TextView tvTextDefense = activity.findViewById(R.id.tvTextDefense);
        ProgressBar pbDefense = activity.findViewById(R.id.pbDefense);
        int defense = pokemon.getPokemonStats().getDefense();
        tvTextDefense.setText(defense);
        progressBarHandler.SetProgressbarValue(defense, pbDefense);

        TextView tvTextSpAttack = activity.findViewById(R.id.tvTextSpAttack);
        ProgressBar pbSpAttack = activity.findViewById(R.id.pbSpAttack);
        int spAttack = pokemon.getPokemonStats().getSpAttack();
        tvTextSpAttack.setText(spAttack);
        progressBarHandler.SetProgressbarValue(spAttack, pbSpAttack);

        TextView tvTextSpDef = activity.findViewById(R.id.tvTextSpDef);
        ProgressBar pbSpDef = activity.findViewById(R.id.pbSpDefense);
        int spDef = pokemon.getPokemonStats().getSpDefense();
        tvTextSpDef.setText(spDef);
        progressBarHandler.SetProgressbarValue(spDef, pbSpDef);

        TextView tvTextSpeed = activity.findViewById(R.id.tvTextSpeed);
        ProgressBar pbSpeed = activity.findViewById(R.id.pbSpeed);
        int speed = pokemon.getPokemonStats().getSpeed();
        tvTextSpeed.setText(speed);
        progressBarHandler.SetProgressbarValue(speed, pbSpeed);
    }

    public void LoadEvolutions(Pokemon pokemon) {
        //TODO Load Logic for evos
    }

    public void LoadMoves(Pokemon pokemon) {
        //TODO Load Logic for evos
    }
}
