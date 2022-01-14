package com.example.tdmd.Contracts;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokemonTest {
    PokemonStats pokemonStats = new PokemonStats(20,30,40,50,60,70);
    ArrayList<Type> types = new ArrayList<Type>(Collections.singleton(Type.Grass));
    ArrayList<String> abilities = new ArrayList<String>(Collections.singleton("Ember"));
    ArrayList<Pokemon> evolutions = new ArrayList<Pokemon>(Collections.singleton(new Pokemon("Blaziken", types, "imageurl")));
    ArrayList<String> moves = new ArrayList<>(Collections.singleton("Flamethrower"));
    Pokemon pokemon = new Pokemon("Torchic",types,12,30,40,abilities,pokemonStats,evolutions,moves,"imageurl");

    @Test
    public void testToString() {
        String testString = "Pokemon{" +
                "name='" + pokemon.getName() + '\'' +
                ", types=" + types +
                ", ID=" + pokemon.getID() +
                ", height=" + pokemon.getHeight() +
                ", weight=" + pokemon.getWeight() +
                ", abilities=" + abilities +
                ", pokemonStats=" + pokemonStats +
                ", evolutions=" + evolutions +
                ", moves=" + moves +
                ", imageurl='" + "imageurl" + '\'' +
                '}';

        String getString = pokemon.toString();

        assertEquals(testString, getString);
    }

    @Test
    public void getName() {
        String testString = "Torchic";

        String getString = pokemon.getName();

        assertEquals(testString,getString);
    }

    @Test
    public void getTypes() {
        List<Type> testTypes = this.types;

        List<Type> getTypes = pokemon.getTypes();

        assertEquals(testTypes,getTypes);
    }

    @Test
    public void getID() {
        int testID = 12;

        int getID = pokemon.getID();

        assertEquals(testID,getID);
    }

    @Test
    public void getHeight() {
        double testHeight = 30;

        double getHeight = pokemon.getHeight();

        assertEquals(testHeight,getHeight,0);
    }

    @Test
    public void getWeight() {
        double testWeight = 40;

        double getWeight = pokemon.getWeight();

        assertEquals(testWeight,getWeight,0);
    }

    @Test
    public void getAbilities() {
        List<String> testAbilities = abilities;

        List<String> getAbilities = pokemon.getAbilities();

        assertEquals(testAbilities,getAbilities);
    }

    @Test
    public void getPokemonStats() {
        PokemonStats testPokemonStats = this.pokemonStats;

        PokemonStats getPokemonStats = pokemon.getPokemonStats();

        assertEquals(testPokemonStats,getPokemonStats);
    }

    @Test
    public void getEvolutions() {
        List<Pokemon> testEvolutions = evolutions;

        List<Pokemon> getEvolutions = pokemon.getEvolutions();

        assertEquals(testEvolutions,getEvolutions);
    }

    @Test
    public void getMoves() {
        List<String> testMoves = moves;

        List<String> getMoves = pokemon.getMoves();

        assertEquals(testMoves,getMoves);
    }

    @Test
    public void getImageurl() {
        String testImageurl = "imageurl";

        String getImageurl = pokemon.getImageurl();

        assertEquals(testImageurl,getImageurl);
    }
}