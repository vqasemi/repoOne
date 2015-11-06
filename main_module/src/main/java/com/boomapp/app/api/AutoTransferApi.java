package com.boomapp.app.api;

import com.boomapp.app.dto.AutoTransferDto;
import com.boomapp.app.dto.AutoTransferRequestDto;
import org.json.JSONObject;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * @author mohsen
 * @since 11/5/2015.
 */
public interface AutoTransferApi {
    @POST("/")
    public void RetrieveActivation(@Body AutoTransferDto requestText, Callback<JSONObject> response);
}