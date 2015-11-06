package com.boomapp.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.boomapp.app.api.ActivationApi;
import com.boomapp.app.api.AutoTransferApi;
import com.boomapp.app.dto.AutoTransferDto;
import com.boomapp.app.dto.AutoTransferRequestDto;
import com.boomapp.app.dto.LoginDto;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

import java.math.BigDecimal;
import java.util.Date;

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

//        String[] types = new String [] {"«‰ ﬁ«· ÊÃÂ" , "Å—œ«Œ  ﬁ”ÿ"};
//        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,types);
//        type.setAdapter(aa);

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

                dialog.findViewById(R.id.okBTN).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AutoCompleteTextView f = (AutoCompleteTextView) dialog.findViewById(R.id.tranFrom);
                        String ff = f.getText().toString();

                        EditText t = (EditText) dialog.findViewById(R.id.tranTo);
                        String tt = t.getText().toString();

                        EditText a = (EditText) dialog.findViewById(R.id.amount);
                        String aa = a.getText().toString();

                        RestAdapter restAdapter = new RestAdapter.Builder()
                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                .setEndpoint("http://89.221.90.189:9837/boomyaghut/rest/tosanBoomIB/deposit/autoTransfer")
                                .setRequestInterceptor(new RequestInterceptor() {
                                    @Override
                                    public void intercept(RequestFacade request) {
                                        request.addHeader("Cookie", SessionCookie.getInstance().getSession());
                                    }
                                })
                                .build();
                        AutoTransferApi activationApi = restAdapter.create(AutoTransferApi.class);


                        AutoTransferDto autoTransferDto = new AutoTransferDto(new BigDecimal(aa),tt,ff,new Date(),new Short("1"), AutoTransferDto.TermType.DAILY,new Short("1"));
                        AutoTransferRequestDto autoTransferRequestDto = new AutoTransferRequestDto(autoTransferDto);
                        activationApi.RetrieveActivation(autoTransferDto, new Callback<JSONObject>() {
                            @Override
                            public void success(JSONObject stringResponse, Response response) {
                                Log.e("success", "success login");
                                Log.e("response",stringResponse.toString());
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                Log.e("", "failure");
                                Log.e("failure in autotransfer", retrofitError.getKind().toString());

                            }
                        });
                    }
                });

                dialog.findViewById(R.id.canBTN).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
