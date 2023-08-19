package com.adekorir.oss.daraja.api;

import java.util.HashMap;
import java.util.Map;

public class JsonResponsePayload {

    private final Map<String, String> headerMap;
    private int statusCode;
    private String statusMessage;
    private HashMap<String, Object> jsonMap;

    public JsonResponsePayload() {
        this.headerMap = new HashMap<>();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public HashMap<String, Object> getJsonMap() {
        return jsonMap;
    }

    public void setJsonMap(HashMap<String, Object> jsonMap) {
        this.jsonMap = jsonMap;
    }
}
