package com.adekorir.oss.daraja.api.v1.lnm;

import brad.tech.api.safaricom.daraja.CommandID;

import java.util.HashMap;
import java.util.Map;

public class LipaNaMpesaSTKPushRequest extends LipaNaMPesaOnlineRequestBase {

    private Double amount;
    private String phoneNumber, callBackURL, accountReference, transactionDesc;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCallBackURL() {
        return callBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        this.callBackURL = callBackURL;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        Map<String, String> map = super.getKeyValuePair();
        map.putAll(
                // specific to this class
                new HashMap<String, String>() {
                    /**
                     *
                     */
                    private static final long serialVersionUID = -5517019648699315626L;

                    {
                        put(TRANSACTION_TYPE, CommandID.CUSTOMER_PAY_BILL_ONLINE.getCommand());
                        put(AMOUNT, amount + "");
                        put(PARTY_A, getPhoneNumber());
                        put(PARTY_B, getBusinessShortCode());
                        put(PHONE_NUMBER, getPhoneNumber());
                        put(CALLBACK_URL, getCallBackURL());
                        put(ACCOUNT_REFERENCE, getAccountReference());
                        put(TRANSACTION_DESC, getTransactionDesc());
                    }
                }
        );
        return map;
    }
}
