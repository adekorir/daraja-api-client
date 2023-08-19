package brad.tech.api.safaricom.daraja.v1.c2b;

import brad.tech.api.safaricom.daraja.CommandID;
import brad.tech.api.safaricom.daraja.KeyValuePair;

import java.util.HashMap;
import java.util.Map;

public class C2BSimulateTransactionRequest implements KeyValuePair {

    private CommandID commandID;
    private Double amount;
    private String MSISDN, billRefNumber, shortCode;

    public CommandID getCommandID() {
        return commandID;
    }

    public void setCommandID(CommandID commandID) {
        this.commandID = commandID;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getBillRefNumber() {
        return billRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        this.billRefNumber = billRefNumber;
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
            private static final long serialVersionUID = -5244202236183513846L;

            {
                put("CommandID", commandID.getCommand());
                put("Amount", amount + "");
                put("MSISDN", MSISDN);
                put("BillRefNumber", billRefNumber);
                put("ShortCode", shortCode);
            }
        };
    }
}
