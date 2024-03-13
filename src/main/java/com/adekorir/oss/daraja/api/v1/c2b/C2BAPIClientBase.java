package com.adekorir.oss.daraja.api.v1.c2b;

import com.adekorir.oss.daraja.api.DarajaClientBase;
import com.adekorir.oss.daraja.api.DarajaException;
import com.adekorir.oss.daraja.api.DarajaStandardResponse;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.util.HashMap;

public abstract class C2BAPIClientBase extends DarajaClientBase {

    /**
     * This cascades the url to the base class.
     *
     * @param url the C2B API url
     */
    public C2BAPIClientBase(String url) {
        super(url);
    }

    protected DarajaStandardResponse executeC2BRequest(HttpPost httpPost) throws DarajaException {
        DarajaStandardResponse response = null;

        try {
            HashMap<String, Object> map = getJsonPayload(httpPost).getJsonMap();
            if (map != null) {
                String conversationID = (String) map.get("ConversationID");
                String originatorConversationID = (String) map.get("OriginatorConversationID");
                String responseDesc = (String) map.get("ResponseDescription");

                response = new DarajaStandardResponse(conversationID, originatorConversationID, responseDesc);
            }
        } catch (IOException ex) {
            throw new DarajaException("Error executing C2B Request", ex);
        }

        return response;
    }
}
