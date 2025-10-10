package org.aneeshsid.urlshortener.api.dto;

import java.time.OffsetDateTime;

public class ShortenResponse {
    private String alias;
    private String shortenedURL;
    private OffsetDateTime createdAt;
    private OffsetDateTime expiresAt;

    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public String getShortenedURL() {
        return shortenedURL;
    }
    public void setShortenedURL(String shortenedURL) {
        this.shortenedURL = shortenedURL;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(OffsetDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
