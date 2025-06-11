package com.example.limogo.model;

public class LoginResponse {
    private boolean success;
    private String message;
    private Data data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public static class Data {
        private boolean verified;
        private String token;

        public boolean isVerified() {
            return verified;
        }

        public String getToken() {
            return token;
        }
    }

}