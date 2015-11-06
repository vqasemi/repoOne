package com.boomapp.app.api;

import com.boomapp.app.dto.LoginDto;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * @author mohsen
 * @since 11/5/2015.
 */
public interface ActivationApi {
    @POST("/")
    public void RetrieveActivation(@Body LoginDto requestText, Callback<JSONObject> response);
}