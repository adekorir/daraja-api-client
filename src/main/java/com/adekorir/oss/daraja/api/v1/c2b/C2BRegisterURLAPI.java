package brad.tech.api.safaricom.daraja.v1.c2b;

import brad.tech.api.safaricom.daraja.DarajaException;
import brad.tech.api.safaricom.daraja.DarajaStandardResponse;
import org.apache.http.client.methods.HttpPost;

public class C2BRegisterURLAPI extends C2BAPIClientBase {

    public C2BRegisterURLAPI(String url) {
        super(url);
    }

    public DarajaStandardResponse register(C2BRegisterURLRequest request) throws DarajaException {
        final HttpPost httpPost = createBasicMPesaPostRequest();
        request.getKeyValuePair().forEach(httpPost::setHeader);

        return executeC2BRequest(httpPost);
    }
}
