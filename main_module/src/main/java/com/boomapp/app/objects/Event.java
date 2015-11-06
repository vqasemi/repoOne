package com.boomapp.app.objects;

import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * @author mohsen
 * @since 11/4/2015.
 */
public class Event {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private MarkerObject marker;
    private Date date;
    private String title;
    private String description;

    public Event(){
    }

    public Event(MarkerObject marker, Date date, String title, String description) {
        this.marker = marker;
        this.date = date;
        this.title = title;
        this.description = description;
    }

    public String eventToJson(){
        String returnValue = null;
        try {
            returnValue = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public MarkerObject getMarker() {
        return marker;
    }

    public void setMarker(MarkerObject marker) {
        this.marker = marker;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
