package com.adekorir.oss.daraja.api.v1.c2b;

import brad.tech.api.safaricom.daraja.KeyValuePair;

import java.util.HashMap;
import java.util.Map;

public class C2BRegisterURLRequest implements KeyValuePair {

    private String validationURL, confirmationURL;
    private String responseType = "Completed", shortCode;

    public C2BRegisterURLRequest() {
    }

    public String getValidationURL() {
        return validationURL;
    }

    public void setValidationURL(String validationURL) {
        this.validationURL = validationURL;
    }

    public String getConfirmationURL() {
        return confirmationURL;
    }

    public void setConfirmationURL(String confirmationURL) {
        this.confirmationURL = confirmationURL;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        return new HashMap<String, String>() {
            /**
             *
             */
            private static final long serialVersionUID = -3735812033895902029L;

            {
                put("ValidationURL", validationURL);
                put("ConfirmationURL", confirmationURL);
                put("ResponseType", responseType);
                put("ShortCode", shortCode);
            }
        };
    }
}
