package com.boomapp.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.boomapp.app.R;
import com.boomapp.app.objects.Event;

import java.util.List;

/**
 * @author mohsen
 * @since 11/4/2015.
 */
public class EventAdapter extends BaseAdapter {

    private Context context;
    private List<Event> eventList;

    public EventAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.location_event_list_view, parent);
        ((TextView)view.findViewById(R.id.id_title_event)).setText(eventList.get(position).getTitle());
        ((TextView)view.findViewById(R.id.id_title_event)).setText(eventList.get(position).getDescription());
        if(eventList.get(position).getDate() != null) {
            ((TextView) view.findViewById(R.id.id_title_event)).setText(eventList.get(position).getDescription());
        }
        if(eventList.get(position).getMarker() != null) {
            //todo
            //((ImageView) view.findViewById(R.id.id_marker_event)).setImageResource();
        }


        return view;
    }
}
