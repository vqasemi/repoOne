package com.boomapp.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.boomapp.app.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * Created by Hamid on 11/05/2015.
 */
public class CalendarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        MaterialCalendarView calendarView = (MaterialCalendarView) inflater.inflate(R.layout.mat_calendar, null);

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(MaterialCalendarView materialCalendarView, CalendarDay calendarDay, boolean b) {
                Toast.makeText(container.getContext(), calendarDay.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return calendarView;
    }
}
