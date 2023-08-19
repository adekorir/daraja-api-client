package com.adekorir.oss.daraja.api;

import java.util.HashMap;
import java.util.Map;

public class DarajaStandardResponse implements KeyValuePair {

    private String conversationID, originatorConversationID, responseDescription;

    public DarajaStandardResponse() {
    }

    public DarajaStandardResponse(String conversationID, String originatorConversationID, String responseDescription) {
        this.conversationID = conversationID;
        this.originatorConversationID = originatorConversationID;
        this.responseDescription = responseDescription;
    }

    public String getConversationID() {
        return conversationID;
    }

    public void setConversationID(String conversationID) {
        this.conversationID = conversationID;
    }

    public String getOriginatorConversationID() {
        return originatorConversationID;
    }

    public void setOriginatorConversationID(String originatorConversationID) {
        this.originatorConversationID = originatorConversationID;
    }

    public String getResponseDescription() {
        return responseDescription;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    @Override
    public Map<String, String> getKeyValuePair() {
        return new HashMap<String, String>() {
            /**
             *
             */
            private static final long serialVersionUID = -2909706858545349395L;

            {
                put("ConversationID", conversationID);
                put("OriginatorConversationID", originatorConversationID);
                put("ResponseDescription", responseDescription);
            }
        };
    }
}
