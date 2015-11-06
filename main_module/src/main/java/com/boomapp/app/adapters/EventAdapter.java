package com.boomapp.app.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.boomapp.app.R;
import com.boomapp.app.objects.Event;
import com.boomapp.app.utils.SharedPref;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author mohsen
 * @since 11/4/2015.
 */
public class EventAdapter extends BaseAdapter {

    static private final ObjectMapper objectMapper = new ObjectMapper();

    private Context context;
    private List<Event> eventList;

    public EventAdapter(Context context) {
        this.context = context;
        Set<String> eventsSet = SharedPref.getInstance(context).getEventSet();
        eventList = new ArrayList<Event>();
        if(eventsSet!=null) {
            for (String s : eventsSet) {
                Event event = null;
                try {
                    Log.e("EventAdapter:String:",s);
                    event = objectMapper.readValue(s, Event.class);
                } catch (IOException e) {
                    Log.e("EventAdapter", "read json error");
                    e.printStackTrace();
                }
                eventList.add(event);
            }
        }
    }

    @Override
    public int getCount() {
        return eventList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("size of events",""+eventList.size());
        View view = View.inflate(context, R.layout.location_event_list_view, null);
        ((TextView)view.findViewById(R.id.id_title_event)).setText(eventList.get(position).getTitle());
        ((TextView)view.findViewById(R.id.id_description_event)).setText(eventList.get(position).getDescription());
        if(eventList.get(position).getDate() != null) {
            ((TextView) view.findViewById(R.id.id_time_event)).setText(eventList.get(position).getDate().toString());
        }
        if(eventList.get(position).getMarker() != null) {
            //todo
            //((ImageView) view.findViewById(R.id.id_marker_event)).setImageResource();
        }

        return view;
    }
}
