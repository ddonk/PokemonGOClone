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
    private Integer rndInt;

    public RESTAPIAdapter(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public void GetPokemon() {
        rndInt = (int) Math.floor(Math.random() * 898) + 1;
        String url = "https://pokeapi.co/api/v2/pokemon/";
        url = url + rndInt;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject species = response.getJSONObject("species");
                            String name = species.getString("name");

                            JSONObject JSONtypes = response.getJSONObject("types");
                            ArrayList<Type> types = convertJSONObjectToTypeArray(JSONtypes);

                            //JSONArray JSONtypes = response.getJSONArray("types");
                            //ArrayList<Type> types = convertJSONArrayToTypeArray(JSONtypes);

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

                            Log.d("test", name);
                            Log.d("test", types.toString());
                            Log.d("test", imageurl);

                            pokemon = new Pokemon(name, types, imageurl);
                            //pokemon = new Pokemon(name, types, id, height, weight, abilities, pokemonStats, evolutions, moves, imageurl);
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

    private ArrayList<Type> convertJSONObjectToTypeArray(JSONObject jsonObject) {
        ArrayList<Type> types = new ArrayList<>();
        JSONArray tmpArray = new JSONArray();
        String tmpString;

        try {
            tmpArray = jsonObject.getJSONArray("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tmpArray.length(); i++) {
            try {
                tmpString = tmpArray.getString(i);
                tmpString = tmpString.substring(0, 1).toUpperCase() + tmpString.substring(1);
                types.add(Type.valueOf(tmpString));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return types;
    }

//    private ArrayList<Type> convertJSONArrayToTypeArray(JSONArray jsonArray) {
//        ArrayList<Type> types = new ArrayList<>();
//        String tmpString;
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            try {
//                tmpString = jsonArray.getString(i);
//
//                tmpString = tmpString.substring(0, 1).toUpperCase() + tmpString.substring(1);
//                types.add(Type.valueOf(tmpString));
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return types;
//    }

    @NonNull
    private ArrayList<String> convertJSONArrayToStringArray(@NonNull JSONArray jsonArray) {
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

    public Pokemon takePokemon() {
        return this.pokemon;
    }
}


