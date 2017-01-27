package com.panda.prueba1.service.facade;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.panda.prueba1.Persona;

@Stateless
@Named("persona")
public class PersonaFacade extends AbstractFacade<Persona, Long> {

    @PersistenceContext(unitName = "DEFAULT_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

}
