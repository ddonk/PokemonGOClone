package com.example.tdmd.Contracts;

import static org.junit.Assert.*;

import org.junit.Test;

public class PokemonStatsTest {

    PokemonStats pokemonStats = new PokemonStats(20,30,40,50,60,70);

    @Test
    public void getHp() {
        int targetHP = 20;

        int getHP = pokemonStats.getHp();

        assertEquals(targetHP, getHP);
    }

    @Test
    public void getAttack() {
        int targetAtt = 30;

        int getAtt = pokemonStats.getAttack();

        assertEquals(targetAtt, getAtt);
    }

    @Test
    public void getDefense() {
        int targetDef = 40;

        int getDef = pokemonStats.getDefense();

        assertEquals(targetDef, getDef);
    }

    @Test
    public void getSpAttack() {
        int targetSpAtt = 50;

        int getSpAtt = pokemonStats.getSpAttack();

        assertEquals(targetSpAtt, getSpAtt);
    }

    @Test
    public void getSpDefense() {
        int targetSpDef = 60;

        int getSpDef = pokemonStats.getSpDefense();

        assertEquals(targetSpDef, getSpDef);
    }

    @Test
    public void getSpeed() {
        int targetSp = 70;

        int getSp = pokemonStats.getSpeed();

        assertEquals(targetSp, getSp);
    }
}