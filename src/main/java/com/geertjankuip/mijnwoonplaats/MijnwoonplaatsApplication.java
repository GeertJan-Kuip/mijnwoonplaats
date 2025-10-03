package com.geertjankuip.mijnwoonplaats;

import com.geertjankuip.mijnwoonplaats.apiclient.BrowserLikeHttpClientFactory;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableRetry
public class MijnwoonplaatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MijnwoonplaatsApplication.class, args);
	}

	@Bean
	public CloseableHttpClient advancedClient() {
		return BrowserLikeHttpClientFactory.create();
	}

	@Bean
	public RestTemplate restTemplate(CloseableHttpClient advancedClient){
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(advancedClient));
	}

}
