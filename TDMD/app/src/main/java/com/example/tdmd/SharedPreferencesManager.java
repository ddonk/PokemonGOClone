package com.example.tdmd;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tdmd.Contracts.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesManager {
    private TinyDB tinyDB;
    final String key = "PokemonList";

    public SharedPreferencesManager(Context context) {
        this.tinyDB = new TinyDB(context);
    }

    public void AddPokemon(List<Pokemon> pokemons) {
        ArrayList<Object> pokeObjects = new ArrayList<>();

        for (Pokemon p : pokemons) {
            pokeObjects.add((Object) p);
        }
        tinyDB.putListObject(key,pokeObjects);
    }

    public ArrayList<Pokemon> GetPokemon(){
        ArrayList<Object> objects = tinyDB.getListObject(key, Pokemon.class);
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        for (Object o: objects) {
            pokemons.add((Pokemon) o);
        }
        return pokemons;
    }
}
