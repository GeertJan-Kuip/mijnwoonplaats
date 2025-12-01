package com.geertjankuip.mijnwoonplaats.apiclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.BuurtDTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.Postcode4DTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiClientTestingExternalEndpoints {

    RestTemplate template;
    ObjectMapper objectMapper;
    ApiUrls apiUrls;
    UriCreation uriCreation;

    public ApiClientTestingExternalEndpoints(RestTemplate template, ObjectMapper objectMapper, ApiUrls apiUrls,
                                              UriCreation uriCreation) {
        this.template = template;
        this.objectMapper = objectMapper;
        this.apiUrls = apiUrls;
        this.uriCreation = uriCreation;
    }

    public boolean testWoonplaatsEndpoint(){

        int numberFoundValue = -1;
        WoonplaatsDTO woonplaatsDTO = null;
        boolean statuscode = false;

        try{
            ResponseEntity<JsonNode> root = template.getForEntity(uriCreation.createUriWoonplaatsList(1, 0), JsonNode.class);

            statuscode = root.getStatusCode().is2xxSuccessful();
            if (statuscode) {

                numberFoundValue = root.getBody().path("response").path("numFound").asInt();
                JsonNode docsNode = root.getBody().path("response").path("docs");
                woonplaatsDTO =  objectMapper.convertValue(docsNode.get(0), new TypeReference<WoonplaatsDTO>() {});
            }
        } catch(Exception e){ }

        return numberFoundValue>2400 && woonplaatsDTO!=null && statuscode==true;
    }

    public boolean testWoonplaatsDetailsEndpoint(){

        boolean statuscode = false;
        String woonplaatsNaam = null;
        WoonplaatsDTO woonplaatsDTO = null;

        String pdokCodeUtrecht = "wpl-d7676180b7f172bcb7356429b19563a5";
        URI uriTownDetailsUtrecht = uriCreation.createUri(apiUrls.getWoonplaatsdetails(), pdokCodeUtrecht);

        try {
            ResponseEntity<JsonNode> root = template.getForEntity(uriTownDetailsUtrecht, JsonNode.class);

            statuscode = root.getStatusCode().is2xxSuccessful();

            if (statuscode) {

                JsonNode docsNode = root.getBody().path("response").path("docs");

                if (docsNode.isArray() && !docsNode.isEmpty()) {
                    woonplaatsNaam = docsNode.get(0).path("woonplaatsnaam").asText();
                    woonplaatsDTO = objectMapper.convertValue(docsNode.get(0), new TypeReference<WoonplaatsDTO>() {
                    });
                }
            }
        } catch (Exception e) {}

        return woonplaatsNaam.equals("Utrecht") && woonplaatsDTO!=null && statuscode==true;
    }

    public boolean testBuurtEndpoint() {

        boolean statuscode = false;
        BuurtDTO buurtDTO = null;

        try {
            ResponseEntity<JsonNode> root = template.getForEntity(uriCreation.createUriBuurt(1, 0), JsonNode.class);

            statuscode = root.getStatusCode().is2xxSuccessful();
            if (statuscode) {
                JsonNode valueNode = root.getBody().path("value").get(0);
                buurtDTO =  objectMapper.convertValue(valueNode, new TypeReference<BuurtDTO>() {
                });
            }
        } catch (Exception e) {}

        return statuscode==true && buurtDTO!=null;
    }

    public boolean testPostcodeEndpoint(){

        boolean statuscode = false;
        List<Postcode4DTO> resultList = new ArrayList<>();

        try{
            URI myurl = uriCreation.createUriPostcode(0, "3560");
            ResponseEntity<JsonNode> root = template.getForEntity(myurl, JsonNode.class);
            statuscode = root.getStatusCode().is2xxSuccessful();

            JsonNode docsNode = root.getBody().path("response").path("docs");

            if (docsNode.isArray() && !docsNode.isEmpty()) {
                resultList.addAll(objectMapper.convertValue(docsNode, new TypeReference<List<Postcode4DTO>>() {
                }));
            }
        } catch (Exception e) {}

        return statuscode==true && !resultList.isEmpty();
    }
}
