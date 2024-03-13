package com.adekorir.oss.daraja.api.v1.lnm;

import java.util.Map;

public class LipaNaMpesaQueryRequest extends LipaNaMPesaOnlineRequestBase {

    private String checkoutRequestID;

    public String getCheckoutRequestID() {
        return checkoutRequestID;
    }

    public void setCheckoutRequestID(String checkoutRequestID) {
        this.checkoutRequestID = checkoutRequestID;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        final Map<String, String> map = super.getKeyValuePair();
        map.put(CHECKOUT_REQUEST_ID, checkoutRequestID);
        return map;
    }
}
