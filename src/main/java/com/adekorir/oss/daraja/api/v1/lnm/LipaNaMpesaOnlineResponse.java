package com.adekorir.oss.daraja.api.v1.lnm;

import brad.tech.api.safaricom.daraja.KeyValuePair;

import java.util.HashMap;
import java.util.Map;

public class LipaNaMpesaOnlineResponse implements KeyValuePair, LipaNaMpesaOnlineResponseConstants {

    private String merchantRequestID, checkoutRequestID;
    private String responseCode, resultDesc, responseDescription, resultCode;

    public String getMerchantRequestID() {
        return merchantRequestID;
    }

    public void setMerchantRequestID(String merchantRequestID) {
        this.merchantRequestID = merchantRequestID;
    }

    public String getCheckoutRequestID() {
        return checkoutRequestID;
    }

    public void setCheckoutRequestID(String checkoutRequestID) {
        this.checkoutRequestID = checkoutRequestID;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        return new HashMap<String, String>() {
            /**
             *
             */
            private static final long serialVersionUID = 8431208163319371091L;

            {
                put(MERCHANT_REQUEST_ID, getMerchantRequestID());
                put(CHECKOUT_REQUEST_ID, getCheckoutRequestID());
                put(RESPONSE_CODE, getResponseCode());
                put(RESULT_DESC, getResultDesc());
                put(RESPONSE_DESCRIPTION, getResponseDescription());
                put(RESULT_CODE, getResultCode());
            }
        };
    }
}
