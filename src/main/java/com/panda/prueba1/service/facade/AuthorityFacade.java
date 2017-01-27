package com.panda.prueba1.service.facade;

import com.panda.prueba1.Authority;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("authority")
public class AuthorityFacade extends AbstractFacade<Authority, String> {

    @PersistenceContext(unitName = "DEFAULT_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorityFacade() {
        super(Authority.class);
    }
}
