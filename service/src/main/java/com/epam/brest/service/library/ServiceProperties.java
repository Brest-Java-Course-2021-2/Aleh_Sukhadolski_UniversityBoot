package com.epam.brest.service.library;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="service")
public class ServiceProperties {

    /**
     * A message for the service.
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
