package com.example.tdmd;

import android.content.SharedPreferences;

class SharedPreferencesManager {
    private SharedPreferences.Editor editor;
    private SharedPreferences settings;

    public SharedPreferencesManager(SharedPreferences settings) {
        this.settings = settings;
        editor = settings.edit();
    }

    public void AddSetting(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public String GetSetting(String key){
        return settings.getString(key, "").toString();
    }

}
