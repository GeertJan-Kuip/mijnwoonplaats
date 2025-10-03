package com.geertjankuip.mijnwoonplaats;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiClient;
import com.geertjankuip.mijnwoonplaats.apiclient.ApiUrls;
import com.geertjankuip.mijnwoonplaats.apiclient.UriCreation;
import com.geertjankuip.mijnwoonplaats.datatransferobjects.WoonplaatsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ActiveProfiles("test")
public class ApiClientTest {

    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;
    private ApiUrls apiUrls;
    private UriCreation uriCreation;


    private ApiClient apiClient;

    @BeforeEach
    void setUp(){
        restTemplate = Mockito.mock(RestTemplate.class);
        objectMapper = Mockito.mock(ObjectMapper.class);
        apiUrls = Mockito.mock(ApiUrls.class);
        uriCreation = Mockito.mock(UriCreation.class);

        apiClient = new ApiClient(restTemplate, objectMapper, apiUrls, uriCreation);
    }

    @Test
    void fetchWoonplaatsen_returnsEmptyList_whenRestTemplateThrows() {
        Mockito.when(restTemplate.getForEntity(anyString(), eq(JsonNode.class)))
                .thenThrow(new RuntimeException("API failure"));

        List<WoonplaatsDTO> result = apiClient.fetchWoonplaatsen(10, 0);

        assertThat(result).isEmpty();
    }

}
