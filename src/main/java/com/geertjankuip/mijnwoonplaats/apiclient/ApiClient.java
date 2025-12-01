package com.geertjankuip.mijnwoonplaats.apiclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.BuurtDTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.Postcode4DTO;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.util.*;

@Component
public class ApiClient implements ApiClientMethodEnforcer{

    private static final Logger log = LoggerFactory.getLogger(ApiClient.class);

    RestTemplate template;
    ObjectMapper objectMapper;
    ApiUrls apiUrls;
    UriCreation uriCreation;

    public ApiClient(RestTemplate template, ObjectMapper objectMapper, ApiUrls apiUrls,
                     UriCreation uriCreation) {
        this.template = template;
        this.objectMapper = objectMapper;
        this.apiUrls = apiUrls;
        this.uriCreation = uriCreation;
    }

    public List<WoonplaatsDTO> getWoonplaatsen(int rows, int start) {

        List<WoonplaatsDTO> list = fetchWoonplaatsen(rows, start);
        fetchWoonplaatsDetail(list);
        return list;
    }

    public List<WoonplaatsDTO> fetchWoonplaatsen(int rows, int start)  {

        try{
            ResponseEntity<JsonNode> root = template.getForEntity(uriCreation.createUriWoonplaatsList(rows, start), JsonNode.class);
            if (root.getStatusCode().is2xxSuccessful()) {

                JsonNode docsNode = root.getBody().path("response").path("docs");
                return objectMapper.convertValue(docsNode, new TypeReference<List<WoonplaatsDTO>>() {});
            }
        } catch(Exception e){
            log.warn("The catch clause of the fetchWoonplaatsen() method was activated");
        }
        return Collections.emptyList();
    }

    public void fetchWoonplaatsDetail(List<WoonplaatsDTO> woonplaatsDTOList) {

        for (WoonplaatsDTO woonplaatsDTO : woonplaatsDTOList) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            URI uriTownDetailsFull = uriCreation.createUri(apiUrls.getWoonplaatsdetails(), woonplaatsDTO.getId());
            ResponseEntity<JsonNode> root = template.getForEntity(uriTownDetailsFull, JsonNode.class);

            JsonNode docsNode = root.getBody().path("response").path("docs");

            if (docsNode.isArray() && docsNode.size() > 0) {
                JsonNode woonplaatsDetails = docsNode.get(0);
                try {
                    objectMapper.readerForUpdating(woonplaatsDTO).readValue(woonplaatsDetails);
                } catch (IOException e) {
                    throw new UncheckedIOException("Failed to parse town details", e);
                }
            }
        }
    }

    public List<BuurtDTO> getBuurten(){

        List<BuurtDTO> listPart1 = fetchBuurten(9999,0);
        List<BuurtDTO> listPart2 = fetchBuurten(9999,9999);

        listPart1.addAll(listPart2);
        return listPart1;
    }

    public List<BuurtDTO> fetchBuurten(int top, int skip) {

        try{
            ResponseEntity<JsonNode> root = template.getForEntity(uriCreation.createUriBuurt(top, skip), JsonNode.class);

            if (root.getStatusCode().is2xxSuccessful()) {

                JsonNode valueNode = root.getBody().path("value");
                return objectMapper.convertValue(valueNode, new TypeReference<List<BuurtDTO>>() {
                });
            }
        } catch(Exception e){
            log.warn(e.getMessage());
            log.warn("The catch clause of the fetchBuurten() method was activated");
        }
        return Collections.emptyList();
    }

    public Set<Postcode4DTO> getPostcodes(Set<String> woonplaatsCodes) {

        Set<Postcode4DTO> totalSet = new HashSet<>();

        for (String woonplaatsCode: woonplaatsCodes) {

            List<Postcode4DTO> listEnkeleWoonplaats = fetchPostcodes(woonplaatsCode);

            if (listEnkeleWoonplaats.isEmpty()) {
                log.warn("Failed to get a result from fetchPostcodes() method for woonplaatscode {}.", woonplaatsCode);
                return Collections.emptySet();
            }
            totalSet.addAll(listEnkeleWoonplaats);
        }
        return totalSet;
    }

    public List<Postcode4DTO> fetchPostcodes(String woonplaatscode) {

        int batchSize = 100;
        List<Postcode4DTO> resultList = new ArrayList<>();

        URI myurl;
        int counter = 0;
        boolean proceed = true;

        try {

            while (proceed) {

                int startPoint = counter * batchSize;
                if (startPoint > (9999 - batchSize)) proceed = false;
                myurl = uriCreation.createUriPostcode(startPoint, woonplaatscode);

                ResponseEntity<JsonNode> response = template.getForEntity(myurl, JsonNode.class);

                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                JsonNode docsNode = response.getBody().path("response").path("docs");

                if (docsNode.isArray() && !docsNode.isEmpty()) {
                    resultList.addAll(objectMapper.convertValue(docsNode, new TypeReference<List<Postcode4DTO>>() {
                    }));
                } else {
                    proceed = false;
                }
                counter++;
            }
            return resultList;
        }catch (Exception e){

        }
        return Collections.emptyList();
    }
}
