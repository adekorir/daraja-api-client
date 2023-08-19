package brad.tech.api.safaricom.daraja.v1.auth;

import brad.tech.api.safaricom.daraja.DarajaClientBase;
import brad.tech.api.safaricom.daraja.DarajaException;
import brad.tech.api.safaricom.daraja.SandboxURLs;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

public class OAuthAPIClient extends DarajaClientBase {

    private String consumerKey, consumerSecret;

    /**
     * Creates an OAuthAPIClient object for authorizing client requests.
     *
     * @param url            the OAuthAPIClient URL
     * @param consumerKey    the app key to authenticate against
     * @param consumerSecret the app secret that will be used to authorize
     */
    public OAuthAPIClient(String url, String consumerKey, String consumerSecret) {
        super(url);

        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
    }

    /**
     * Creates a basic OAUthAPI Client using the sandbox URL.
     *
     * @param consumerKey    the app key
     * @param consumerSecret the app secret
     */
    public OAuthAPIClient(String consumerKey, String consumerSecret) {
        this(SandboxURLs.OAUTH_URL, consumerKey, consumerSecret);
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public void setConsumerSecret(String consumerSecret) {
        this.consumerSecret = consumerSecret;
    }

    /**
     * Generate an OAuth Response from the OAuth API.
     * <p>
     * This makes a GET request to the api service and returns an authorization
     * object.
     *
     * @return an {@link OAuthResponse} object or null.
     * @see IOException
     */
    public OAuthResponse authenticate() throws DarajaException {
        OAuthResponse oAuthResponse = null;

        // create the auth password
        final String appKey = this.getConsumerKey();
        final String appSecret = this.getConsumerSecret();

        final byte[] bytes = String.format("%s:%s", appKey, appSecret)
                .getBytes(StandardCharsets.ISO_8859_1);
        final String auth = Base64.getEncoder().encodeToString(bytes);

        final HttpGet request = new HttpGet(this.url);
        request.addHeader("Authorization", "Basic " + auth);
        request.addHeader("Cache-Control", "no-cache");

        try {
            HashMap map = getJsonPayload(request).getJsonMap();
            if (map != null) {
                String accessToken = (String) map.get("access_token");
                long expiresIn;
                try {
                    expiresIn = Long.parseLong((String) map.get("expires_in"));
                } catch (NumberFormatException ex) {
                    expiresIn = 3599L;
                }

                oAuthResponse = new OAuthResponse(accessToken, expiresIn);
            }
        } catch (IOException ex) {
            throw new DarajaException("Error establishing link to MPesa's OAuth API Service", ex);
        }

        return oAuthResponse;
    }

}
