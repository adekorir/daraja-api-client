package brad.tech.api.safaricom.daraja;

public interface SandboxURLs {

    String BASE = "https://sandbox.safaricom.co.ke";

    // auth
    String OAUTH_URL = String.format("%s/%s", BASE, "oauth/v1/generate?grant_type=client_credentials");

    // b2b
    String B2C_URL = String.format("%s/mpesa/%s", BASE,"b2c/v1/paymentrequest");
    String B2B_URL = String.format("%s/mpesa/%s", BASE,"b2b/v1/paymentrequest");

    // c2b
    String C2B_REGISTER_URL_URL = String.format("%s/mpesa/%s", BASE,"c2b/v1/registerurl");
    String C2B_SIMULATE_TRANSACTION_URL = String.format("%s/mpesa/%s", BASE,"c2b/v1/simulate");

    // etc
    String ACCOUNT_BALANCE_URL = String.format("%s/mpesa/%s", BASE,"accountbalance/v1/query");
    String TRANSACTION_STATUS_URL = String.format("%s/mpesa/%s", BASE,"transactionstatus/v1/query");
    String REVERSAL_URL = String.format("%s/mpesa/%s", BASE,"reversal/v1/request");

    // lipa na mpesa
    String LIPA_NA_MPESA_STK_PUSH_URL = String.format("%s/mpesa/%s", BASE,"stkpush/v1/processrequest");
    String LIPA_NA_MPESA_QUERY_URL = String.format("%s/mpesa/%s", BASE,"stkpushquery/v1/query");
}
