package com.adekorir.oss.daraja.api.v1.lnm;

import brad.tech.api.safaricom.daraja.KeyValuePair;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class LipaNaMPesaOnlineRequestBase implements KeyValuePair, LipaNaMpesaOnlineRequestConstants {

    protected String businessShortCode, passKey;
    protected Date timestamp;

    public String getBusinessShortCode() {
        return businessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        this.businessShortCode = businessShortCode;
    }

    public String getPassKey() {
        return passKey;
    }

    public void setPassKey(String passKey) {
        this.passKey = passKey;
    }

    protected String generatePassword() {
        final String encodeString = String.format("%s%s%s", this.businessShortCode, this.passKey, this.timestamp);
        return Base64.getEncoder().encodeToString(encodeString.getBytes());
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        final String formattedDateTime = new SimpleDateFormat("yyyyMMddhhmmss").format(getTimestamp());
        return new HashMap<String, String>() {
            /**
             *
             */
            private static final long serialVersionUID = -7655444180581093444L;

            {
                put(BUSINESS_SHORT_CODE, getBusinessShortCode());
                put(PASSWORD, generatePassword());
                put(TIMESTAMP, formattedDateTime);
            }
        };
    }
}
