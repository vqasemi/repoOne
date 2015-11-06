package com.boomapp.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.boomapp.app.objects.Event;
import com.boomapp.app.objects.MarkerObject;

import java.util.*;


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

    public static List<Event> fakeData(){
        List<Event> fakeList = new ArrayList<Event>();
        fakeList.add(new Event(null,new Date(),"تولد محسن","100 تومن ریختم به حسابش"));
        fakeList.add(new Event(null,new Date(),"اجاره خونه","انتقال زدم"));
        fakeList.add(new Event(null,new Date(),"پرداخت قسط","وام مسکن"));
        fakeList.add(new Event(new MarkerObject(32.0,53.0),null,"داروخانه","داروهای مامان بزرگ"));
        fakeList.add(new Event(new MarkerObject(32.0,53.0),new Date(),"اصغر","یادآوری تمرین دوم"));
        return fakeList;
    }

    public static Set<String> listToJson(List<Event> eventsList){
        Set<String> eventsSet = new HashSet<String>();
        for (Event e : eventsList) {
            eventsSet.add(e.eventToJson());
        }
        return eventsSet;
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

    public void setDate(String firstLaunch){
        appSharedPreferences.edit().putString("DATE", firstLaunch).commit();
    }

    public String  getDate(){
        return appSharedPreferences.getString("DATE","");
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
