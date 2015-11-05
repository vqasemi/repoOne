package com.boomapp.app;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.boomapp.app.fragments.CalendarFragment;
import com.boomapp.app.fragments.MapFragmentINote;
import com.boomapp.app.utils.SharedPref;

public class MainActivity extends Activity {

    Fragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPref.getInstance(this).getFirstLaunch() == -1) {
            // todo vahid fragment ziri ro ba kelasi ke zadi por kon.
            mainFragment = new CalendarFragment();
        } else {
            // todo reza fragment ziri ro por kon.
            mainFragment = new Fragment();
        }
        getFragmentManager().beginTransaction().replace(R.id.topFragment, mainFragment).commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map_settings) {

            if (mainFragment instanceof CalendarFragment) {
                mainFragment = new MapFragmentINote();
                item.setIcon(R.drawable.ic_calendar70);
            } else {
                mainFragment = new CalendarFragment();
                item.setIcon(android.R.drawable.ic_dialog_map);
            }
            getFragmentManager().beginTransaction().replace(R.id.topFragment, mainFragment).commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
