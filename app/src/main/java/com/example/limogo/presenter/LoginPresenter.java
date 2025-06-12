package com.example.limogo.presenter;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.limogo.model.GenericResponse;
import com.example.limogo.model.LoginRequest;
import com.example.limogo.model.LoginResponse;
import com.example.limogo.api.ApiClient;
import com.example.limogo.model.SendVerificationCodeRequest;
import com.example.limogo.utils.TokenManager;
import com.example.limogo.view.LoginView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private final LoginView view;
    private final Context context;

    public LoginPresenter(LoginView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);
        ApiClient.getService(context).login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    String token = response.body().getData().getToken();
                    TokenManager.saveToken(context, token);
                    boolean verified = response.body().getData().isVerified();
                    Toast.makeText(context, TokenManager.getToken(context) , Toast.LENGTH_SHORT).show();
                    if (!verified) {
                        SendVerificationCodeRequest emailRequest = new SendVerificationCodeRequest(email);
                        ApiClient.getService(context).sendVerification( emailRequest ).enqueue(new Callback<GenericResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<GenericResponse> call, @NonNull Response<GenericResponse> response) {
                                if (response.body() != null && response.body().isSuccess()) {
                                    Toast.makeText(context, "Verification code sent to " + email, Toast.LENGTH_LONG).show();
                                } else {
                                    String errorMsg = "Failed to send verification code";

                                    view.onLoginError(errorMsg);
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<GenericResponse> call, @NonNull Throwable t) {
                                view.onLoginError("Network error: " + t.getMessage());
                            }


                        });
                    }

                    view.onLoginSuccess();
                } else {
                    view.onLoginError("Invalid credentials or server error");
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                view.onLoginError("Network error: " + t.getMessage());
            }
        });
    }
}