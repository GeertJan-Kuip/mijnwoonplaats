package com.geertjankuip.mijnwoonplaats.apiclient;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class UriCreation {

    ApiUrls apiUrls;

    public UriCreation(ApiUrls apiUrls) {
        this.apiUrls = apiUrls;
    }

    public URI createUriWoonplaatsList(int rows, int start) {
        return UriComponentsBuilder
                .fromHttpUrl(apiUrls.getWoonplaatsen())
                .queryParam("q", apiUrls.getWoonplaatsenrequestparamq())
                .queryParam("rows", rows)
                .queryParam("start", start)
                .build()
                .toUri();
    }

    public URI createUriBuurt(int top, int skip) {
        return UriComponentsBuilder
                .fromHttpUrl(apiUrls.getBuurten())
                .queryParam("$select", apiUrls.getBuurtenrequestparamselect())
                .queryParam("$filter", apiUrls.getBuurtenrequestparamfilter())
                .queryParam("$top", top)
                .queryParam("$skip", skip)
                .build()
                .toUri();
    }

    public URI createUriPostcode(int start, String woonplaatscode) {
        return UriComponentsBuilder
                .fromHttpUrl(apiUrls.getPostcodes())
                .queryParam("q", apiUrls.getPostcodesrequestparamq())
                .queryParam("fl", apiUrls.getPostcodesrequestparamfl())
                .queryParam("fq", apiUrls.getPostcodesrequestparamfq() + woonplaatscode)
                .queryParam("rows", apiUrls.getPostcodesrequestparamrows())
                .queryParam("start", start)
                .build()
                .toUri();
    }

    public URI createUri(String uriBase, String id) {
        return UriComponentsBuilder
                .fromHttpUrl(uriBase)
                .queryParam("id", id)
                .build()
                .toUri();
    }


}
