package brad.tech.api.safaricom.daraja.v1.auth;

import brad.tech.api.safaricom.daraja.KeyValuePair;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents an authentication response object.
 * <p>
 * It has two properties: the access token and time to expire.
 *
 * @see <a href="https://developer.safaricom.co.ke/docs#authentication">Authentication Docs</a>
 * @see KeyValuePair
 */
public class OAuthResponse implements KeyValuePair {

    private String accessToken;
    private Long expiresIn;

    public OAuthResponse() {
    }

    /**
     * A convenience constructor.
     *
     * @param accessToken the access token representation
     * @param expiresIn   the time the token expires in.
     */
    public OAuthResponse(String accessToken, long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccess_token(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpires_in(Long expires_in) {
        this.expiresIn = expires_in;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        return new HashMap<String, String>() {
            /**
             *
             */
            private static final long serialVersionUID = 7609424242039285289L;

            {
                put("access_token", accessToken);
                put("expires_in", "" + expiresIn);
            }
        };
    }
}
