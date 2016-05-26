package br.com.meetstudents.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by marinagosson on 26/05/16.
 */
public class AppPreferences {


    public static final String PREFS_NAME = "PREFS_NAME_APP";
    public static final String PREFS_IS_LOGGED = "APP_PREFS_IS_LOGGED";

    public AppPreferences() {
        super();
    }

    public void saveBoolean(Context context, Boolean value, String prefsKey) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE); //1
        editor = settings.edit();

        editor.putBoolean(prefsKey, value);

        editor.commit();
    }

    public void setBooleanValue(Context context, String prefsKey, Boolean newValue) {
        SharedPreferences settings;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(prefsKey, newValue);
        editor.commit();

    }

    public Boolean getBooleanValue(Context context, String prefsKey) {
        SharedPreferences settings;
        Boolean text;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        text = settings.getBoolean(prefsKey, Boolean.parseBoolean(null));
        return text;
    }

    public void clearSharedPreference(Context context) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();
    }

    public void removeBoolenValuePrefs(Context context, String prefsKey) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.remove(prefsKey);
        editor.commit();
    }

}
