package com.demo.airline.repositories;

import com.demo.airline.models.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CustomRepository implements ICustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Session> customQuerySessions() {
        return entityManager.createNativeQuery("SELECT s.* FROM Sessions s").getResultList();
    }

    @Override
    public String customHelloWord() {
        return "Hello Java World";
    }
}
