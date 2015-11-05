package com.boomapp.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

/**
 * Created by Hamid on 11/05/2015.
 */

public class AddEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event_layout);

        Spinner type = (Spinner) findViewById(R.id.spinner);
        Button OK = (Button) findViewById(R.id.OKbtn);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
