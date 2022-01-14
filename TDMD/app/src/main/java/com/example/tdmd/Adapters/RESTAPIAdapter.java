package com.example.tdmd.Adapters;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tdmd.Contracts.Move;
import com.example.tdmd.Contracts.Pokemon;
import com.example.tdmd.Contracts.PokemonStats;
import com.example.tdmd.Contracts.Type;
import com.example.tdmd.VolleyCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RESTAPIAdapter {

    public static void GetPokemon(Context context, final VolleyCallback volleyCallback) {
        RequestQueue queue = Volley.newRequestQueue(context);
        int rndInt = (int) Math.floor(Math.random() * 898) + 1;
        String url = "https://pokeapi.co/api/v2/pokemon/";
        url = url + rndInt;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            //Log.d("Volley", response.toString());
                            String name = response.getString("name");

                            name = name.substring(0, 1).toUpperCase() + name.substring(1);
                            int id = response.getInt("id");
                            int height = response.getInt("height");
                            int weight = response.getInt("weight");
                            //Log.d("Volley", "name: " + name + " id: " + id + " height: " + height + " weight: " + weight);

                            String imageurl = getArt(response);
                            ArrayList<Type> types = getTypes(response);
                            ArrayList<String> abilities = getAbilities(response);
                            ArrayList<String> moves = getMoves(response);

                            PokemonStats pokemonStats = getPokemonStats(response);
                            Pokemon pokemon = new Pokemon(name, types, id, height, weight, abilities, pokemonStats, moves, imageurl);

                            volleyCallback.OnSucces(pokemon);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Volley", "" + error.toString());
                    }
                });

        queue.add(jsonObjectRequest);
    }

    private static PokemonStats getPokemonStats(JSONObject response) {
        try {
            JSONArray stats = response.getJSONArray("stats");

            int hp;
            int attack;
            int defense;
            int spAttack;
            int spDefense;
            int speed;

            hp = stats.getJSONObject(0).getInt("base_stat");
            attack = stats.getJSONObject(1).getInt("base_stat");
            defense = stats.getJSONObject(2).getInt("base_stat");
            spAttack = stats.getJSONObject(3).getInt("base_stat");
            spDefense = stats.getJSONObject(4).getInt("base_stat");
            speed = stats.getJSONObject(5).getInt("base_stat");

            PokemonStats pokemonStats = new PokemonStats(hp, attack, defense, spAttack, spDefense, speed);

            return pokemonStats;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ArrayList<String> getMoves(JSONObject response) {
        ArrayList<String> result = new ArrayList<>();

        try {
            JSONArray movesArray = response.getJSONArray("moves");
            for (int i = 0; i < 4; i++) {
                JSONObject move = movesArray.getJSONObject(i);
                JSONObject move1 = move.getJSONObject("move");
                String name = move1.getString("name");

                result.add(name);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String getArt(JSONObject response) {
        try {
            JSONObject spritesArray = response.getJSONObject("sprites");

            JSONObject otherSprites = spritesArray.getJSONObject("other");

            JSONObject officialArtwork = otherSprites.getJSONObject("official-artwork");

            String imageUrl = officialArtwork.getString("front_default");
            //Log.d("Volley", "imageurl: " + imageUrl.toString());

            return imageUrl;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ArrayList<Type> getTypes(JSONObject response) {
        ArrayList<Type> result = new ArrayList<>();
        try {
            JSONArray types = response.getJSONArray("types");
            for (int i = 0; i < types.length(); i++) {
                String type = types.getJSONObject(i).getJSONObject("type").getString("name");

                String output = type.substring(0, 1).toUpperCase() + type.substring(1);

                result.add(Type.valueOf(output));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static ArrayList<String> getAbilities(JSONObject response) {
        ArrayList<String> result = new ArrayList<>();
        try {
            JSONArray jsonArray = response.getJSONArray("abilities");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject ability = jsonArray.getJSONObject(i);
                JSONObject ability1 = ability.getJSONObject("ability");
                String abilityName = ability1.getString("name");
                result.add(abilityName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return result;
    }
}

