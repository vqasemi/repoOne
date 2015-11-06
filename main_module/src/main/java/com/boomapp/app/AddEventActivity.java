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
import com.boomapp.app.objects.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.internal.ob;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

import java.io.IOException;
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

        String[] types = new String [] {"انتقال وجه" , "پرداخت قسط","ارسال پیامک","سر رسید چک"};
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,types);
        type.setAdapter(aa);

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
                                Log.e("success", "success autotransfer");
                                byte[] temp = new byte[(int)response.getBody().length()];
                                try {
                                    response.getBody().in().read(temp,0,temp.length);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                String res = new String(temp);
                                Log.e("response",res);

                                class Peygiri{
                                    String result;
                                }
                                Peygiri pey = null;
                                ObjectMapper objectMapper = new ObjectMapper();
                                try {
                                    pey = objectMapper.readValue(res, Peygiri.class);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                dialog.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                Log.e("", "failure");
                                Log.e("failure in autotransfer", retrofitError.getKind().toString());

                                dialog.dismiss();
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
