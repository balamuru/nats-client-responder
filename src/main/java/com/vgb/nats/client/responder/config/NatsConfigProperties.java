package com.vgb.nats.client.responder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "nats")
public class NatsConfigProperties {

    private String uri;
    private Map<String, String> subjectsAndResponses;

    public NatsConfigProperties() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getSubjectsAndResponses() {
        return subjectsAndResponses;
    }

    public void setSubjectsAndResponses(Map<String, String> subjectsAndResponses) {
        this.subjectsAndResponses = subjectsAndResponses;
    }
}