package com.example.tdmd.Contracts;

import java.util.List;

public class Pokemon {
    private String name;
    private List<Type> types;
    private int ID;
    private double height;
    private double weight;
    private List<String> abilities;
    private PokemonStats pokemonStats;
    private List<Pokemon> evolutions;
    private List<Move> moves;

    public Pokemon(String name, List<Type> types) {
        this.name = name;
        this.types = types;
    }

    public Pokemon(String name, List<Type> types, int ID, double height, double weight, List<String> abilities, PokemonStats pokemonStats, List<Pokemon> evolutions, List<Move> moves) {
        this.name = name;
        this.types = types;
        this.ID = ID;
        this.height = height;
        this.weight = weight;
        this.abilities = abilities;
        this.pokemonStats = pokemonStats;
        this.evolutions = evolutions;
        this.moves = moves;
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

    public List<Pokemon> getEvolutions() {
        return evolutions;
    }

    public List<Move> getMoves() {
        return moves;
    }
}
