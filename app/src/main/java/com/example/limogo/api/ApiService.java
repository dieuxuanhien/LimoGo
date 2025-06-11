package com.example.limogo.api;



import com.example.limogo.model.LoginRequest;
import com.example.limogo.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/auth/login")
    Call<LoginResponse> login(@Body LoginRequest body);

    @POST("/auth/send-verification-code")
    Call<Void> sendVerification(@Header("Authorization") String bearerToken);
}
