package com.example.tdmd.Contracts;

import java.io.Serializable;
import java.util.List;

public class Pokemon implements Serializable {
    private String name;
    private List<Type> types;
    private int ID;
    private double height;
    private double weight;
    private List<String> abilities;
    private PokemonStats pokemonStats;
    private List<String> moves;
    private String imageurl;

    public Pokemon(String name, List<Type> types, String imageurl) {
        this.name = name;
        this.types = types;
        this.imageurl = imageurl;
    }

    public Pokemon(String name, List<Type> types, int ID, double height, double weight, List<String> abilities, PokemonStats pokemonStats, List<String> moves, String imageurl) {
        this.name = name;
        this.types = types;
        this.ID = ID;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.pokemonStats = pokemonStats;
        this.moves = moves;
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", types=" + types +
                ", ID=" + ID +
                ", height=" + height +
                ", weight=" + weight +
                ", abilities=" + abilities +
                ", pokemonStats=" + pokemonStats +
                ", moves=" + moves +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public List<Type> getTypes() {
        return types;
    }

    public int getID() {
        return ID;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public List<String> getAbilities() {
        return abilities;
    }

    public PokemonStats getPokemonStats() {
        return pokemonStats;
    }

    public List<String> getMoves() {
        return moves;
    }

    public String getImageurl() {
        return imageurl;
    }
}
