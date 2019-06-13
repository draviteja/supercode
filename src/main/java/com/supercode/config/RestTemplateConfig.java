package com.supercode.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {

    private int timeout = 2000;

    @Bean
    public RestTemplate restTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create().build());
        clientHttpRequestFactory.setConnectTimeout(timeout);
        clientHttpRequestFactory.setReadTimeout(timeout);

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (interceptors == null) {
            interceptors = new ArrayList<ClientHttpRequestInterceptor>();
            restTemplate.setInterceptors(interceptors);
        }
        interceptors.add(new GzipAcceptHeaderRequestInterceptor());
        return restTemplate;
    }
    public static class GzipAcceptHeaderRequestInterceptor implements ClientHttpRequestInterceptor {
        @Override
        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
            request.getHeaders().set(HttpHeaders.ACCEPT_ENCODING, "gzip");
            return execution.execute(request, body);
        }
    }
}
