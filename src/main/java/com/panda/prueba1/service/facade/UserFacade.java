package com.panda.prueba1.service.facade;

import com.panda.prueba1.User;
import static java.util.Collections.singletonMap;
import java.util.Optional;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named("user")
public class UserFacade extends AbstractFacade<User, Long> {

    @PersistenceContext(unitName = "DEFAULT_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public Optional<User> findOneByLogin(String login) {
        return findSingleByNamedQuery("findUserByLogin", singletonMap("login", login), User.class);
    }

    public Optional<User> findOneByEmail(String email) {
        return findSingleByNamedQuery("findUserByEmail", singletonMap("email", email), User.class);
    }

    public Optional<User> findOneByResetKey(String resetKey) {
        return findSingleByNamedQuery("findUserByResetKey", singletonMap("resetKey", resetKey), User.class);
    }

    public Optional<User> findOneByActivationKey(String activationKey) {
        return findSingleByNamedQuery("findUserByActivationKey", singletonMap("activationKey", activationKey), User.class);
    }

    public Optional<User> findOneById(Long userId) {
        return findSingleByNamedQuery("findUserByUserId", singletonMap("id", userId), User.class);
    }

    public Optional<User> findOneWithAuthoritiesById(Long userId) {
        return findSingleByNamedQuery("findUserByUserId", "graph.user.authorities", singletonMap("id", userId), User.class);
    }

    public Optional<User> findOneWithAuthoritiesByLogin(String login) {
        return findSingleByNamedQuery("findUserByLogin", "graph.user.authorities", singletonMap("login", login), User.class);
    }

    public List<User> getUsersWithAuthorities(int startPosition, int size) {
        return findRange(startPosition, size, "graph.user.authorities");
    }

}
