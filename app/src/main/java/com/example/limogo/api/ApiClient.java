package com.example.limogo.api;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.limogo.utils.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:3000/";
    private static Retrofit retrofit;

    // Private static inner AuthInterceptor class
    private static class AuthInterceptor implements Interceptor {
        private final Context context;

        AuthInterceptor(Context context) {
            this.context = context.getApplicationContext();
        }

        @NonNull
        @Override
        public Response intercept(Chain chain) throws IOException {
            String token = TokenManager.getToken(context);
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            if (!token.isEmpty()) {
                builder.header("Authorization", "Bearer "+ token);
            }
            return chain.proceed(builder.build());
        }
    }

    public static ApiService getService(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(context))
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiService.class);
    }
}