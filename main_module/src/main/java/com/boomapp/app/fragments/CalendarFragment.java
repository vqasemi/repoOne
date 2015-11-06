package com.boomapp.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boomapp.app.R;
import com.boomapp.app.utils.SharedPref;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Hamid on 11/05/2015.
 */
public class CalendarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        MaterialCalendarView calendarView = (MaterialCalendarView) inflater.inflate(R.layout.mat_calendar, null);
        calendarView.setDateSelected(new Date(),true);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView materialCalendarView, CalendarDay calendarDay, boolean b) {
                Toast.makeText(container.getContext(), calendarDay.toString(), Toast.LENGTH_SHORT).show();


                Log.e("111111111111111111111111",calendarDay.getDate().toString());
                String d = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS+00:00").format(calendarDay.getDate());
                d = d.substring(0,10)+"T"+d.substring(11);
                Log.e("111111111111111111111111",d);
                SharedPref.getInstance().setDate(d);
            }
        });
        return calendarView;
    }
}
