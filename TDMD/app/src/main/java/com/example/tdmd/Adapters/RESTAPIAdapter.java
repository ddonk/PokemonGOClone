package com.example.tdmd.Adapters;

import android.content.Context;
import android.util.Log;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RESTAPIAdapter {
    private Context context;
    private RequestQueue queue;
    private Pokemon pokemon;

    public RESTAPIAdapter(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void GetPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon/charmander";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject species = response.getJSONObject("species");
                            String name = species.getString("name");

                            JSONArray JSONtypes = response.getJSONArray("types");
                            ArrayList<Type> types = convertJSONArrayToTypeArray(JSONtypes);

                            int id = response.getInt("id");
                            double height = response.getDouble("height");
                            double weight = response.getDouble("weight");;
                            ArrayList<String> abilities = convertJSONArrayToStringArray(response.getJSONArray("abilities"));

                            JSONArray stats = response.getJSONArray("stats");

                            PokemonStats pokemonStats = new PokemonStats(stats.getJSONObject(0).getInt("base_stat"),
                                    stats.getJSONObject(0).getInt("base_stat"),
                                    stats.getJSONObject(1).getInt("base_stat"),
                                    stats.getJSONObject(2).getInt("base_stat"),
                                    stats.getJSONObject(3).getInt("base_stat"),
                                    stats.getJSONObject(4).getInt("base_stat"));

                            ArrayList<Pokemon> evolutions = new ArrayList<>();

                            JSONArray JSONMoves = response.getJSONArray("moves");

                            ArrayList<String> moves = new ArrayList<>();
                            for (int i = 0; i < 4; i++) {
                                Random random = new Random();

                                int high = JSONMoves.length() -1;
                                int low = 0;

                                int randomMoveIndex = random.nextInt(random.nextInt(high-low) + low);

                                JSONObject move = JSONMoves.getJSONObject(randomMoveIndex);
                                moves.add(move.getString("name"));
                            }

                            JSONObject JSONSprites = response.getJSONObject("sprites");
                            JSONObject JSONOtherSprites = JSONSprites.getJSONObject("other");
                            JSONObject officialArtwork = JSONOtherSprites.getJSONObject("official-artwork");
                            String imageurl = officialArtwork.getString("front_default");

                            pokemon = new Pokemon(name, types, id, height, weight, abilities, pokemonStats, evolutions, moves, imageurl);
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

    private ArrayList<Type> convertJSONArrayToTypeArray(JSONArray jsonArray) {
        ArrayList<Type> types = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                types.add(Type.valueOf(jsonArray.getString(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return types;
    }

    private ArrayList<String> convertJSONArrayToStringArray(JSONArray jsonArray) {
        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                strings.add(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return strings;
    }
}


