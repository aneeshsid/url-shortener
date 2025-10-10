package org.aneeshsid.urlshortener.api.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aneeshsid.urlshortener.api.model.Link;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LinkRepo{
    @PersistenceContext
    EntityManager em;
    public void createLink(Link link){
        em.persist(link);
    }

    public Link getLinkByAlias(String alias){
        return em.createQuery(
                        "SELECT l FROM Link l WHERE l.alias = :alias", Link.class)
                .setParameter("alias", alias)
                .getResultStream()  // stream to avoid exceptions if none
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public void updateClickCount(String alias, long newCount) {
        em.createQuery("UPDATE Link l SET l.clickCount = :count WHERE l.alias = :alias")
                .setParameter("count", newCount)
                .setParameter("alias", alias)
                .executeUpdate();
    }

    @Transactional
    public Link getLinkByURL(String url,String clientID){
        return em.createQuery("SELECT l FROM Link l where l.targetURL =: url AND l.clientID =: clientID",Link.class)
                .setParameter("url",url)
                .setParameter("clientID",clientID)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }
}
