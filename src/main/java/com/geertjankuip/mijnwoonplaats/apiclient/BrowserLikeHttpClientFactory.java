package com.geertjankuip.mijnwoonplaats.apiclient;

import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.http.HttpHeaders;

public class BrowserLikeHttpClientFactory {

    public static CloseableHttpClient create() {
        BasicCookieStore cookieStore = new BasicCookieStore();

        return HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) " +
                        "Chrome/115.0.0.0 Safari/537.36")
                .addRequestInterceptorLast((request, entity, context) -> {
                    request.addHeader(HttpHeaders.ACCEPT, "application/json, text/plain, */*");
                    request.addHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-US,en;q=0.9");
                    request.addHeader(HttpHeaders.ACCEPT_ENCODING, "gzip, deflate, br");
                })
                .disableAutomaticRetries()
                .build();
    }
}
