package com.boomapp.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.transfer_dialog);
        String[] depos = new String[]{"0203342703006", "0103342704006"};
        final ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, depos);
        AutoCompleteTextView a = (AutoCompleteTextView) dialog.findViewById(R.id.tranFrom);
        a.setAdapter(adapt);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
}
