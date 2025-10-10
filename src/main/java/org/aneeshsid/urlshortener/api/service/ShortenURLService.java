package org.aneeshsid.urlshortener.api.service;

import org.aneeshsid.urlshortener.api.dto.ShortenResponse;
import org.aneeshsid.urlshortener.api.model.Link;
import org.aneeshsid.urlshortener.api.repository.LinkRepo;
import org.aneeshsid.urlshortener.utils.HashUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class ShortenURLService {
    @Autowired
    public final LinkRepo linkRepo;

    private static final String APP_SALT = "A9vP3zLmT7qWx2N8rYbGhK4cJsE5fQ1R";


    public ShortenURLService(LinkRepo linkRepo) {
        this.linkRepo = linkRepo;
    }

    @Transactional
    public ShortenResponse shortenURL(String url,String clientId,String baseURL) {
        double salt = Math.ceil(Math.random()*100)*23*7;
        String alias =  HashUtils.deterministicAlias(url+":"+clientId+":"+APP_SALT,6);
        ShortenResponse shortenResponse = new ShortenResponse();
        shortenResponse.setAlias(alias);
        shortenResponse.setShortenedURL(baseURL +"/"+ alias);
        saveLink(alias,url,clientId,shortenResponse);
        return shortenResponse;
    }

    @Transactional
    public void saveLink(String alias,String targetURL,String clientId,ShortenResponse shortenResponse) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime expiration = now.plusDays(1);
        Link link = new Link();
        link.setId(UUID.randomUUID());
        link.setAlias(alias);
        link.setTargetURL(targetURL);
        link.setClientID(clientId);
        link.setCreatedAt(now);
        link.setExpiresAt(expiration);

        shortenResponse.setCreatedAt(now);
        shortenResponse.setExpiresAt(expiration);
        linkRepo.createLink(link);
    }


    public Link fetchLinkByURL(String url,String clientId) {
        return linkRepo.getLinkByURL(url,clientId );
    }

    public ShortenResponse getShortenedURL(Link link,String baseURL) {
        ShortenResponse shortenResponse = new ShortenResponse();
        shortenResponse.setAlias(link.getAlias());
        shortenResponse.setShortenedURL(baseURL +"/"+ link.getAlias());
        shortenResponse.setCreatedAt(link.getCreatedAt());
        shortenResponse.setExpiresAt(link.getExpiresAt());
        return shortenResponse;
    }
}
