package org.aneeshsid.urlshortener.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ShortenLinkDTO {
    private String clientId;

    @NotBlank(message = "Target URL cannot be blank")
    @Pattern(
            regexp = "^(https?://)([\\w.-]+)(:[0-9]+)?(/.*)?$",
            message = "URL must be valid and start with http:// or https://"
    )
    private String targetUrl;

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

}
