package com.example.limogo.api;



import com.example.limogo.model.GenericResponse;
import com.example.limogo.model.LoginRequest;
import com.example.limogo.model.LoginResponse;
import com.example.limogo.model.SendVerificationCodeRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    @PATCH("/auth/send-verification-code")
    Call<GenericResponse> sendVerification(@Body SendVerificationCodeRequest body);
}
