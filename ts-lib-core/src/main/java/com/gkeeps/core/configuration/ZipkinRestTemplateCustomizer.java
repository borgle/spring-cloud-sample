package com.gkeeps.core.configuration;

import org.springframework.cloud.sleuth.zipkin2.DefaultZipkinRestTemplateCustomizer;
import org.springframework.cloud.sleuth.zipkin2.ZipkinProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

@Component
public class ZipkinRestTemplateCustomizer extends DefaultZipkinRestTemplateCustomizer {
    private final ZipkinProperties zipkinProperties;

    public ZipkinRestTemplateCustomizer(ZipkinProperties zipkinProperties) {
        super(zipkinProperties);
        this.zipkinProperties = zipkinProperties;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        URI uri = URI.create(this.zipkinProperties.getBaseUrl());
        String userInfo = uri.getUserInfo();
        if (userInfo != null) {
            restTemplate.getInterceptors().add(0, new BasicAuthorizationInterceptor(userInfo));
        }
    }

    private class BasicAuthorizationInterceptor implements ClientHttpRequestInterceptor {
        private final String base64Credentials;

        BasicAuthorizationInterceptor(String userInfo) {
            this.base64Credentials = Base64Utils.encodeToString(userInfo.getBytes(StandardCharsets.UTF_8));
        }

        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws
                IOException {
            request.getHeaders().add("Authorization", "Basic " + this.base64Credentials);
            return execution.execute(request, body);
        }
    }
}