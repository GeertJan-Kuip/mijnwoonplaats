package com.geertjankuip.mijnwoonplaats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.geertjankuip.mijnwoonplaats.apiclient.*;
import com.geertjankuip.mijnwoonplaats.repositories.EndpointsTestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

@ActiveProfiles("test")
public class MockitoTest {

    private ApiScheduler apiScheduler;

    private ApiClientTestingExternalEndpoints apiClientTestingExternalEndpoints;
    private EndpointsTestRepository endpointsTestRepository;
    private ApiService apiService;

    private RestTemplate template;
    private ObjectMapper objectMapper;
    private ApiUrls apiUrls;
    private UriCreation uriCreation;

    @BeforeEach
    void setUp(){

    }

    @Test
    public void retrieveOnceADay_test(){

        apiClientTestingExternalEndpoints = Mockito.mock(ApiClientTestingExternalEndpoints.class);
        endpointsTestRepository = Mockito.mock(EndpointsTestRepository.class);
        apiService = Mockito.mock(ApiService.class);
        apiScheduler = new ApiScheduler(apiClientTestingExternalEndpoints, endpointsTestRepository, apiService);

        Mockito.when(apiClientTestingExternalEndpoints.testWoonplaatsEndpoint()).thenReturn(true);
        Mockito.when(apiClientTestingExternalEndpoints.testWoonplaatsDetailsEndpoint()).thenReturn(false);
        Mockito.when(apiClientTestingExternalEndpoints.testBuurtEndpoint()).thenReturn(true);
        Mockito.when(apiClientTestingExternalEndpoints.testPostcodeEndpoint()).thenReturn(true);

        apiScheduler.retrieveOnceADay();
    }

}
