package brad.tech.api.safaricom.daraja.v1.c2b;

import brad.tech.api.safaricom.daraja.DarajaException;
import brad.tech.api.safaricom.daraja.DarajaStandardResponse;
import org.apache.http.client.methods.HttpPost;

public class C2BSimulateTransactionAPIClient extends C2BAPIClientBase {

    public C2BSimulateTransactionAPIClient(String url) {
        super(url);
    }

    public DarajaStandardResponse simulateTransaction(C2BSimulateTransactionRequest request) throws DarajaException {
        final HttpPost httpPost = new HttpPost(this.url);
        request.getKeyValuePair().forEach(httpPost::setHeader);

        return executeC2BRequest(httpPost);
    }
}
