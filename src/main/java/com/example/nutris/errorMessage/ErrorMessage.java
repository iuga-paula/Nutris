package com.example.nutris.errorMessage;

import org.json.JSONObject;

public class ErrorMessage {
    private final String message;

    public ErrorMessage(String message) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", message);
        this.message = jsonObject.toString();
    }

    public String getMessage() {
        return message;
    }
}
