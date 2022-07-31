[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.com/brad-tech/mpesa-api-java.svg?branch=master)](https://travis-ci.com/brad-tech/daraja-api-client)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/brad-tech/mpesa-api-java.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/brad-tech/daraja-api-client/alerts/)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/brad-tech/mpesa-api-java.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/brad-tech/daraja-api-client/context:java)

# Daraja API Client
The Java implementation of [Safaricom](https://www.safaricom.co.ke)'s [M-Pesa](https://www.safaricom.co.ke/personal/m-pesa) [Daraja APIs](https://developer.safaricom.co.ke/). The library uses apache http client, jackson json library and 
junit for unit testing. Documentation for the API can be found at https://developer.safaricom.co.ke/docs.

## Downloading and Installing
[![GitHub issues](https://img.shields.io/github/release/brad-tech/mpesa-api-java.svg)](https://github.com/brad-tech/daraja-api-client/releases/latest)
[![Maven Central](https://img.shields.io/maven-central/v/brad.tech/mpesa-api-java.svg)](http://mvnrepository.com/artifact/brad.tech/daraja-api-client)
1. Get the latest source files by:
   - Downloading from [Github](https://github.com/brad-tech/daraja-api-client) as a [zip file](https://github.com/brad-tech/daraja-api-client/archive/master.zip).
   - Cloning the project with git over 
     * https: `git clone https://github.com/brad-tech/daraja-api-client.git`
     * ssh: `git clone git@github.com:brad-tech/daraja-api-client.git`.
  
2. Navigate to the source directory `cd daraja-api-client`
3. Install it to your local maven repository: `mvn install`.

You can also add the latest compiled jar file located under [releases](https://github.com/brad-tech/mpesa-api-java/releases/download/v1.0/daraja-api-client-1.0-SNAPSHOT.jar).
Just remember to include Jackson and Apache-HttpClient to your classpath.

## Examples
:warning: All examples use Java-8 with the sandbox urls.

### Authentication
```java

import brad.tech.api.safaricom.daraja.DarajaException;
import brad.tech.api.safaricom.daraja.SandboxURLs;
import brad.tech.api.safaricom.daraja.v1.auth.OAuthAPIClient;
import brad.tech.api.safaricom.daraja.v1.auth.OAuthResponse;

class OAuthDemo {

    // supply the app key and app secret
    private static String appKey, appSecret;

    public static void main(String[] args) {
        // supply the url, you can also use the SandboxURLs constants
        final String url = SandboxURLs.OAUTH;

        // authenticate! 
        // get the access token object
        final OAuthAPIClient authClient = new OAuthAPIClient(this.url, appKey, appSecret);
        try {
            final OAuthResponse response = authClient.authenticate();
            // you'll have to check for null scenarios
            if (response != null) {
                final String accessToken = response.getAccessToken();
                final Long expiresIn = response.getExpiresIn();
    
                // print out the values
                System.out.printf("AccessToken: %s, ExpiresIn: %d%n", accessToken, expiresIn);
            }
        } catch (DarajaException ex) {
            System.err.printf("[%5s] Message: %s Cause: %s%n", "EXCEP", ex.getMessage(), ex.getCause().getMessage());
            ex.printStackTrace();
        }
    }
}

```

### C2B API
#### C2B Register URL API
```java

import brad.tech.api.safaricom.daraja.DarajaException;
import brad.tech.api.safaricom.daraja.SandboxURLs;
import brad.tech.api.safaricom.daraja.DarajaStandardResponse;
import brad.tech.api.safaricom.daraja.v1.auth.OAuthAPIClient;
import brad.tech.api.safaricom.daraja.v1.auth.OAuthResponse;
import brad.tech.api.safaricom.daraja.v1.c2b.C2BRegisterURLAPI;
import brad.tech.api.safaricom.daraja.v1.c2b.C2BRegisterURLRequest;

class C2BDemo {
 
    public static void main(String[] args) {
        // supply the variables below with the proper values
        final String 
                        validationURL   = "VALIDATION_URL", 
                        confirmationURL = "CONFIRMATION_URL", 
                        shortCode       = "SHORT_CODE";

        // build the request
        final C2BRegisterURLRequest request = new C2BRegisterURLRequest();
        request.setValidationURL(validationURL);
        request.setConfirmationURL(confirmationURL);
        request.setShortCode(shortCode);
        
        // call the api
        final C2BRegisterURLAPI api = new C2BRegisterURLAPI(SandboxURLs.C2B_REGISTER_URL_API);
        
        // authentication is required
        final OAuthAPI authClient = new OAuthAPIClient(SandboxURLs.OAUTH);
        try {
            final OAuthResponse authResponse = authResponse.authenticate();
            if (authResponse != null) {
                String accessToken = authResponse.getAccessToken();
                api.setAccessToken(accessToken);
                
                DarajaStandardResponse response = api.register(request);
                System.out.printf(
                    "ConversationID: %s, OriginatorConversationID: %s, ResponseDescription: %s %n",
                        response.getConversationID(),
                        response.getOriginatorConversationID(),
                        response.getResponseDescription()
                );
            }
        } catch (DarajaException ex) {
            System.err.printf("[%5s] Message: %s Cause: %s%n", "EXCEP", ex.getMessage(), ex.getCause().getMessage());
            ex.printStackTrace();
        }
    }
}

```

### Lipa Na Mpesa Transaction 
#### STK PUSH
```java

import brad.tech.api.safaricom.daraja.DarajaException;
import brad.tech.api.safaricom.daraja.SandboxURLs;
import brad.tech.api.safaricom.daraja.v1.auth.OAuthAPIClient;
import brad.tech.api.safaricom.daraja.v1.auth.OAuthResponse;
import brad.tech.api.safaricom.daraja.v1.lnm.*;

import java.time.Instant;
import java.util.Date;

public class STKPush {

    public static void main(String[] args){
        // to perform an stk push request
        // determine the request url or use the sandbox url
        final String url = SandboxURLs.LIPA_NA_MPESA_STK_PUSH_URL;

        // these values will be passed on to the request object
        // You can furnish the values from properties file, db or configuration... or anywhere. 
        final double    amount          = 1.0d;                 // the amount to be deducted from the customer's float
        final String    phoneNo         = "PHONE_NUMBER",       // Phone No. has to be an acceptable format for MPesa
                        shortCode       = "SHORT_CODE",         // The short code is provided by MPesa
                        passKey         = "PASS_KEY",           // The pass key is provided by MPesa
                        callbackURL     = "CALLBACK_URL",       // this callback url will handle all responses by Mpesa
                        accountRef      = "ACCOUNT_REF",        // supply your own account reference
                        transactionDesc = "TRANSACTION_DESC";   // this depends on your transaction.
        
        // build the request
        final LipaNaMpesaSTKPushRequest request = new LipaNaMpesaSTKPushRequest();
        // important
        request.setBusinessShortCode(shortCode);
        request.setPassKey(passKey);
        // required
        request.setCallBackURL(callBackURL);
        request.setTimestamp(Date.from(Instant.now()));
        request.setAmount(amount);
        request.setPhoneNumber(phoneNo);

        // other
        request.setAccountReference("Account Reference");
        request.setTransactionDesc("Transaction Description");

        // authenticate
        final String    authURL    = SandboxURLs.OAUTH_URL, // you can use the snadbox url
                        appKey     = "APP_KEY",             // supply the app key
                        appSecret  = "APP_SECRET";          // supply a valid app secret

        final OAuthAPIClient authClient = new OAuthAPIClient(authURL, appKey, appSecret);
        try {    
            final OAuthResponse oAuthResponse = oAuthAPIClient.authenticate();
            if (oAuthResponse != null) {
                final String accessToken = oAuthResponse.getAccessToken();
    
                // furnish the api details
                final STKPushClient client = new STKPushClient(url);
                client.setAccessToken(accessToken);

                // execute STK Push and parse the response
                final LipaNaMpesaOnlineResponse response = client.execute(request);
                // view response values if response is not null
                if (response != null) {
                    response.getKeyValuePair().forEach(STKPush::printValues);
                } else {
                    System.err.println("Error: No response object returned");
                    
                }
            } // else {
//                  // implement your own error handling if the auth response is null
              // }
        } catch (DarajaException ex) {
            System.err.printf("[%5s] Message: %s Cause: %s%n", "EXCEP", ex.getMessage(), ex.getCause().getMessage());
            ex.printStackTrace();
        }
    }
    
    private static void printValues(String key, String val) {
        System.out.printf("%s - %s%n", key, val);
    }
}
```
## Update (31.08.2022)

The code will be updated to utilize java-9+. Backward compatibility with java 8 will no longer work.

Having trouble with your build or found a bug? [Create an issue!](https://github.com/brad-tech/daraja-api-client/issues)
