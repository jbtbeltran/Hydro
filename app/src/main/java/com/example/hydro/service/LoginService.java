package com.example.hydro.service;


import com.example.hydro.model.AquaMessageResponse;
import com.example.hydro.model.LoginParameter;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("login")
    Call<AquaMessageResponse> login(@Body LoginParameter loginParameter);
}
