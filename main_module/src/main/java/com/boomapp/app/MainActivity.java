package com.boomapp.app;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.boomapp.app.adapters.EventAdapter;
import com.boomapp.app.api.ActivationApi;
import com.boomapp.app.dto.LoginDto;
import com.boomapp.app.fragments.CalendarFragment;
import com.boomapp.app.fragments.MapFragmentINote;
import com.boomapp.app.utils.SharedPref;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.okhttp.OkHttpClient;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Response;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.SecureRandom;

public class MainActivity extends Activity {

    private Fragment mainFragment;
    private EventAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPref.getInstance(this).setEventSet(SharedPref.listToJson(SharedPref.fakeData()));

        if (SharedPref.getInstance(this).getFirstLaunch() == -1) {
            // todo vahid fragment ziri ro ba kelasi ke zadi por kon.
            mainFragment = new CalendarFragment();
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.login_layout);
            final ProgressDialog prodialog = new ProgressDialog(this);
            prodialog.setMessage("منتظر بمانید");
            prodialog.setTitle("ورود");

            final AlertDialog.Builder alart = new AlertDialog.Builder(this);
            ((Button)dialog.findViewById(R.id.login_id)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        prodialog.show();
                        OkHttpClient client = new OkHttpClient();
                        KeyStore keyStore = readKeyStore(); //your method to obtain KeyStore
                        SSLContext sslContext = SSLContext.getInstance("SSL");
                        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                        trustManagerFactory.init(keyStore);
                        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                        keyManagerFactory.init(keyStore, "123456".toCharArray());
                        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
                        client.setSslSocketFactory(sslContext.getSocketFactory());

                        RestAdapter restAdapter = new RestAdapter.Builder()
                                .setLogLevel(RestAdapter.LogLevel.FULL)
                                .setEndpoint("http://89.221.90.189:9837/boomyaghut/rest/tosanBoomIB/user/loginStatic")
                                .build();
                        ActivationApi activationApi = restAdapter.create(ActivationApi.class);

                        LoginDto loginDto = new LoginDto();
                        //todo hamid in ziriaro por kon
                        loginDto.setUsername(((EditText) dialog.findViewById(R.id.username)).getText().toString());
                        loginDto.setPassword(((EditText) dialog.findViewById(R.id.password)).getText().toString());
                        activationApi.RetrieveActivation(loginDto, new Callback<JSONObject>() {
                            @Override
                            public void success(JSONObject stringResponse, Response response) {
                                Log.e("success", "success login");
                                for (Header header : response.getHeaders()) {
                                    if (header.getName().equals("Set-Cookie")) {
                                        Log.e("value", header.getValue());
                                                String sessionID = header.getValue().substring(header.getValue().indexOf("J"),header.getValue().indexOf(";"));
                                                Log.e("value of sessionid",sessionID);
                                                SessionCookie.getInstance().setSession(sessionID);
                                    }
                                }
                                Log.e("response login", response.getHeaders().get(response.getHeaders().size() - 1).getValue());
                                prodialog.dismiss();
                                dialog.dismiss();
                            }

                            @Override
                            public void failure(RetrofitError retrofitError) {
                                Log.e("", "failure");
                                Log.e("failure", retrofitError.getKind().toString());

                                prodialog.dismiss();
                                dialog.dismiss();

                                alart.setMessage("");
                                alart.show();
                            }
                        });
                    } catch (Exception e) {

                    }
                }
            });
            dialog.findViewById(R.id.lagve).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        } else {
            // todo reza fragment ziri ro por kon.
            mainFragment = new Fragment();
        }
        eventAdapter = new EventAdapter(this);
        getFragmentManager().beginTransaction().replace(R.id.topFragment, mainFragment).commit();
        ListView list = ((ListView) findViewById(R.id.id_event_list_view));
        list.setAdapter(eventAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(list);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , AddEventActivity.class));
            }
        });
    }

    KeyStore readKeyStore() {
        KeyStore ks;
        try {

             ks = KeyStore.getInstance(KeyStore.getDefaultType());

            // get user password and file input stream
            char[] password = "123456".toCharArray();

            InputStream fis = null;
            try {
//                fis = this.getResources().openRawResource(R.raw.yaghut);
                Log.e("asdfaq1111111sdf","asdfasdfasfasdfasdf");
                fis = this.getResources().openRawResource(R.raw.yaghut) ;
                Log.e("as33333333333333333asdf","asdfasdfasfasdfasdf");
                ks.load(fis, password);
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        }catch (Exception e){
            Log.e("readkeahdklsfjalk","asdjfklajsdfajsdf");
            e.printStackTrace();
            return null;
        }
        return ks;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.map_settings) {

            if (mainFragment instanceof CalendarFragment) {
                mainFragment = new MapFragmentINote();
                item.setIcon(R.drawable.ic_calendar70);
            } else {
                mainFragment = new CalendarFragment();
                item.setIcon(android.R.drawable.ic_dialog_map);
            }
            getFragmentManager().beginTransaction().replace(R.id.topFragment, mainFragment).commit();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
