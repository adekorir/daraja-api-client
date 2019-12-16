package brad.tech.api.safaricom.daraja;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MPesaAPIClientBase implements ResourceBundleConsumer {

    protected final String url;
    protected String accessToken;

    public MPesaAPIClientBase(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    // helper methods

    /**
     * This creates a Http Post Request with authorization baked in.
     * <p>
     * Appropriate headers are simply provided by the method. These are:
     * - Content-Type
     * - Authorization
     * The daraja api needs this format in order to return a valid response.
     *
     * @return a {@link HttpPost} object with appropriate headers.
     */
    protected HttpPost createBasicMPesaPostRequest() {
        final HttpPost httpPost = new HttpPost(this.url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + this.accessToken);

        return httpPost;
    }

    protected JsonResponsePayload getJsonPayload(HttpUriRequest request) throws IOException, MPesaException {
        final JsonResponsePayload payload = new JsonResponsePayload();

        try (final CloseableHttpClient client = HttpClients.createDefault()) {
            final HttpResponse response = client.execute(request);

            int statusCode = response.getStatusLine().getStatusCode();
            String statusDesc = response.getStatusLine().getReasonPhrase();
            payload.setStatusCode(statusCode);
            payload.setStatusMessage(statusDesc);

            final Map<String, String> headerMap = payload.getHeaderMap();
            Header[] allHeaders = response.getAllHeaders();
            for (Header header : allHeaders) {
                headerMap.put(header.getName(), header.getValue());
            }

            String json = EntityUtils.toString(response.getEntity());
            HashMap jsonMap = new ObjectMapper().readValue(json, HashMap.class);
            payload.setJsonMap(jsonMap);
        }

        // validate payload
        final int statusCode = payload.getStatusCode();
        final String statusMessage = payload.getStatusMessage();
        final String statusInfo = String.format("%d - %s", statusCode, statusMessage);

        // validate status code
        if (statusCode < 200 || statusCode >= 300) {
            throw new MPesaException("Server returned error response. INFO: " + statusInfo);
        }

        // validate response body
        if (payload.getJsonMap() == null) {
            throw new MPesaException("There was no response body. INFO: " + statusInfo);
        }

        // pass; continue
        return payload;
    }

}
