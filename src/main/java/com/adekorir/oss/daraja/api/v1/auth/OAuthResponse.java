package com.adekorir.oss.daraja.api.v1.auth;

import com.adekorir.oss.daraja.api.KeyValuePair;

import java.io.Serial;
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
public record OAuthResponse(String access_token, long expires_in) implements KeyValuePair {

    @Override
    public Map<String, String> getKeyValuePair() {
        return new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 7609424242039285289L;

            {
                put("access_token", access_token());
                put("expires_in", "" + expires_in());
            }
        };
    }
}
