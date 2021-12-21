package com.example.tdmd.UIHandlers;

import android.graphics.Color;

import com.example.tdmd.Contracts.Type;

public class TypeColorHandler {

    public static int typeColor(Type type) {
        switch (type) {
            case Normal:
                return Color.parseColor("#A3A37B");
            case Fighting:
                return Color.parseColor("#c03028");
            case Flying:
                return Color.parseColor("#a890f0");
            case Poison:
                return Color.parseColor("#a040a0");
            case Ground:
                return Color.parseColor("#C5AE72");
            case Rock:
                return Color.parseColor("#b8a038");
            case Bug:
                return Color.parseColor("#a8b820");
            case Ghost:
                return Color.parseColor("#705898");
            case Steel:
                return Color.parseColor("#8A8A9C");
            case Fire:
                return Color.parseColor("#f08030");
            case Water:
                return Color.parseColor("#6890f0");
            case Grass:
                return Color.parseColor("#78c850");
            case Electric:
                return Color.parseColor("#FFFC55");
            case Psychic:
                return Color.parseColor("#f85888");
            case Ice:
                return Color.parseColor("#98d8d8");
            case Dragon:
                return Color.parseColor("#7038f8");
            case Dark:
                return Color.parseColor("#705848");
            case Fairy:
                return Color.parseColor("#ee99ac");
            default:
                return Color.WHITE;
        }
    }
}
