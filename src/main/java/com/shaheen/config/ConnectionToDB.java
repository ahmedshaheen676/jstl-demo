/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaheen.config;

import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author lts
 */
public class ConnectionToDB {

    private static ConnectionToDB instance;
    private final EntityManager entityManager;

    private ConnectionToDB() {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("persistence");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public static synchronized ConnectionToDB getInstance() {
        return Objects.requireNonNullElse(instance, new ConnectionToDB());
    }
}
