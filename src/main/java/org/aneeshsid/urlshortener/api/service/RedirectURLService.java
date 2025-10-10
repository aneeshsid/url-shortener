package org.aneeshsid.urlshortener.api.service;

import org.aneeshsid.urlshortener.api.model.Link;
import org.aneeshsid.urlshortener.api.repository.LinkRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedirectURLService {
    @Autowired
    public LinkRepo linkRepo;
    public Link redirect(String alias) {
        return linkRepo.getLinkByAlias(alias);
    }

    public void updateClickCount(Long clickCount, String alias) {
        if(clickCount==null) {
            clickCount = 0L;
        }
        linkRepo.updateClickCount(alias, clickCount + 1);
    }
}
