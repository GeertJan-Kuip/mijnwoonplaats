package com.geertjankuip.mijnwoonplaats;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geertjankuip.mijnwoonplaats.apiclient.*;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import com.geertjankuip.mijnwoonplaats.domainobjects.EndpointsTest;
import com.geertjankuip.mijnwoonplaats.repositories.EndpointsTestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;

@ActiveProfiles("test")
public class MockitoTests {

    private ApiScheduler apiScheduler;
    private ApiClientTestingExternalEndpoints apiClientTestingExternalEndpoints;
    private EndpointsTestRepository endpointsTestRepository;
    private ApiService apiService;

    @BeforeEach
    void setUp(){

        apiClientTestingExternalEndpoints = Mockito.mock(ApiClientTestingExternalEndpoints.class);
        endpointsTestRepository = Mockito.mock(EndpointsTestRepository.class);
        apiService = Mockito.mock(ApiService.class);
        apiScheduler = new ApiScheduler(apiClientTestingExternalEndpoints, endpointsTestRepository, apiService);
    }

    @Test
    void apiClientInternalsTest(){

        RestTemplate template = Mockito.mock(RestTemplate.class);
        ObjectMapper objectMapper = Mockito.mock(ObjectMapper.class);
        ApiUrls apiUrls = Mockito.mock(ApiUrls.class);
        UriCreation uriCreation = Mockito.mock(UriCreation.class);
        ResponseEntity responseEntity = Mockito.mock(ResponseEntity.class);

        ApiClient apiClient = new ApiClient(template, objectMapper, apiUrls, uriCreation);

        Mockito.when(uriCreation.createUriWoonplaatsList(anyInt(),anyInt())).thenReturn(URI.create("justastring"));
        Mockito.when(template.getForEntity(any(URI.class), eq(JsonNode.class))).thenReturn(responseEntity);

        Mockito.when(responseEntity.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        List<WoonplaatsDTO> result = apiClient.fetchWoonplaatsen(10,0);
        assertThat(result).isEmpty();
    }

    @Test
    public void retrieveOnceADayTest(){

        Mockito.when(apiClientTestingExternalEndpoints.testWoonplaatsEndpoint()).thenReturn(true);
        Mockito.when(apiClientTestingExternalEndpoints.testWoonplaatsDetailsEndpoint()).thenReturn(false);
        Mockito.when(apiClientTestingExternalEndpoints.testBuurtEndpoint()).thenReturn(true);
        Mockito.when(apiClientTestingExternalEndpoints.testPostcodeEndpoint()).thenReturn(true);

        EndpointsTest endpointsTest = apiScheduler.retrieveOnceADayImpl();
        assertThat(endpointsTest.getWoonplaatsendpoint()).isTrue();
        assertThat(endpointsTest.getWoonplaatsdetailendpoint()).isFalse();
        assertThat(endpointsTest.getBuurtendpoint()).isTrue();
        assertThat(endpointsTest.getPostcodeendpoint()).isTrue();
    }

    @Test
    public void fullUpdateWoonplaatsenTest(){

        Mockito.when(apiService.fetchWoonplaatsen(anyInt(), anyInt())).thenReturn(Collections.emptyList());

        Integer listSizeWoonplaatsen = apiScheduler.fullUpdateWoonplaatsen();
        assertThat(listSizeWoonplaatsen).isEqualTo(0);
    }

    @Test
    public void fullUpdateBuurtenTest(){

        Mockito.when(apiService.fetchBuurten()).thenReturn(Collections.emptyList());

        Integer listSizeBuurten = apiScheduler.fullUpdateBuurten();
        assertThat(listSizeBuurten).isEqualTo(0);
    }

    @Test
    public void fullUpdatePostcodesTest(){

        Mockito.when(apiService.fetchPostcodes()).thenReturn(Collections.emptySet());

        Integer setSizePostcodes = apiScheduler.fullUpdatePostcodes();
        assertThat(setSizePostcodes).isEqualTo(0);
    }

}
