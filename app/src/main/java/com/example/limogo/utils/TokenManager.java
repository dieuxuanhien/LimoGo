package com.example.limogo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String PREFS = "lmg_prefs";
    private static final String KEY = "jwt";
    public static void saveToken(Context c, String t) {
        c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putString(KEY, t).apply();
    }
    public static String getToken(Context c) {
        return c.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getString(KEY, "");
    }
}