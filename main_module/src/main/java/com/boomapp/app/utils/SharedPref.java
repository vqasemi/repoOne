package com.boomapp.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Set;


public class SharedPref {

    public static final String APP_PREFs = "AppPrefs" ;
    //shared pref keys:
    public static final String FIRST_LAUNCH = "FIRST_LAUNCH";
    public static final String EVENT_LIST = "EVENT_LIST";

    private static SharedPref instance;

    private SharedPreferences appSharedPreferences =null;

    private SharedPref(Context appContext){
        appSharedPreferences = appContext.getSharedPreferences(APP_PREFs, Context.MODE_PRIVATE);
    }

    public static SharedPref getInstance(Context appContext){
        if(instance == null){
            synchronized (SharedPref.class) {
                instance = new SharedPref(appContext);
            }
        }
        return instance;
    }

    public static SharedPref getInstance(){
        if(instance == null){
            Log.e("SharedPref", "You must first get the instance of this class from getInstance(Context).");
        }
        return instance;
    }


    public void clearData(){
    }


    // setters
    public void setFirstLaunch(int firstLaunch){
        appSharedPreferences.edit().putInt(FIRST_LAUNCH,firstLaunch).commit();
    }

    public void setEventSet(Set<String> events){
        appSharedPreferences.edit().putStringSet(EVENT_LIST, events).commit();
    }


    // getters
    public Integer getFirstLaunch(){
        return appSharedPreferences.getInt(FIRST_LAUNCH, -1);
    }

    public Set<String> getEventSet(){
        return appSharedPreferences.getStringSet(EVENT_LIST, null);
    }


    // todo : add methods for modifying pref values!

    public void removeAllPrefs(){
        appSharedPreferences.edit().clear().commit();
    }
}
