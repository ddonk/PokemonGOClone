package com.example.tdmd.UIHandlers;

import android.graphics.Color;

import com.example.tdmd.Contracts.Type;

public class TypeColorHandler {

    public static int typeColor(Type type) {
        switch (type) {
            case normal:
                return Color.parseColor("#A3A37B");
            case fighting:
                return Color.parseColor("#c03028");
            case flying:
                return Color.parseColor("#a890f0");
            case poison:
                return Color.parseColor("#a040a0");
            case ground:
                return Color.parseColor("#C5AE72");
            case rock:
                return Color.parseColor("#b8a038");
            case bug:
                return Color.parseColor("#a8b820");
            case ghost:
                return Color.parseColor("#705898");
            case steel:
                return Color.parseColor("#8A8A9C");
            case fire:
                return Color.parseColor("#f08030");
            case water:
                return Color.parseColor("#6890f0");
            case grass:
                return Color.parseColor("#78c850");
            case electric:
                return Color.parseColor("#FFFC55");
            case psychic:
                return Color.parseColor("#f85888");
            case ice:
                return Color.parseColor("#98d8d8");
            case dragon:
                return Color.parseColor("#7038f8");
            case dark:
                return Color.parseColor("#705848");
            case fairy:
                return Color.parseColor("#ee99ac");
            default:
                return Color.WHITE;
        }
    }
}
