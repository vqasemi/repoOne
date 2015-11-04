package com.boomapp.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.boomapp.app.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * @author mohsen
 * @since 11/4/2015.
 */
public class MapFragmentINote extends MapFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mapView = super.onCreateView(inflater,container,savedInstanceState);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(32.0,53.0))      // Sets the center of the map to Last Location View
                .zoom(6)                   // Sets the zoom
                .build();
        getMap().moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        return mapView;
    }
}
