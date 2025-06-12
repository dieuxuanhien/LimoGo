package com.example.limogo.model;

public class SendVerificationCodeRequest {
    private String email;
    public SendVerificationCodeRequest(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
